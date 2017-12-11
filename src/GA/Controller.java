package GA;

import model.Mochila;
import model.Objeto;
import model.Populacao;
import view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller {

    private Populacao populacao;
    private Mochila melhorMochila;
    public static final Integer QUANT_OBJETOS = 8;
    public final double TAXA_MUTACAO = 0.03;
    private final Integer TAMANHO_POPULACAO = 20;
    private final Integer NUM_MAX_GERACOES = 2000;
    private final Integer MELHOR_BENEFICIO = 16;
    public static List<Objeto> objetos = new ArrayList<>();
    private int numGeracoes = 0;

    /**
     * Inicializa objetos do algoritmo
     */
    public void inicializar(){
        objetos.addAll(inicializarListaObjetos()) ;
        List<Mochila> mochilas = new ArrayList<>();
        for(int i = 0; i < TAMANHO_POPULACAO; i++){
            List<Integer> cromossomo = GeradorCromossomo.gerarCromossomo(QUANT_OBJETOS);
            Mochila mochila = new Mochila(cromossomo);
            mochilas.add(mochila);
        }
        verificarMochilas(mochilas);
        populacao = new Populacao(mochilas);

        View.iniciar();
        iniciarGA();
    }

    private void iniciarGA(){
        boolean buscarSolucao = true;

        while(buscarSolucao) {
            List<Mochila> mochilas = populacao.getMochilas();

            Mochila mochila1 = Evolucao.selecionarPorRoleta(this.populacao);
            Mochila mochila2 = Evolucao.selecionarPorRoleta(this.populacao);

            // tratar quando duas mochilas selecionadas são iguais
            while(mochilas.indexOf(mochila1) == mochilas.indexOf(mochila2)){
                mochila2 = Evolucao.selecionarPorRoleta(this.populacao);
            }

            Evolucao.efetuarCrossOver(mochila1, mochila2);
            double probabilidadeMutacao = Math.random()* 1;

            if(probabilidadeMutacao <= TAXA_MUTACAO){
                Evolucao.efetuarMutacao(mochila1);
            }

            //calcula peso, beneficio e aplica penalização
            verificarMochilas(mochilas);

            AtomicBoolean mudouMelhorMochila = new AtomicBoolean(false);
            AtomicInteger indiceMelhorMochila = new AtomicInteger(0);

            // verifica dentro de cada mochila, se seu beneficio satisfaz a solução
            boolean pararGA = mochilas.stream().anyMatch(mochila -> {
                if(mochila.getBeneficioTotal() >= MELHOR_BENEFICIO){
                    indiceMelhorMochila.set(mochilas.indexOf(mochila));
                }
                return mochila.getBeneficioTotal() >= MELHOR_BENEFICIO;
            });

            numGeracoes++;

            if(pararGA){
                this.melhorMochila = mochilas.get(indiceMelhorMochila.get());
                View.mostrarMelhorMochila(melhorMochila);
                buscarSolucao = false;
                View.mostrarEstatisticas(numGeracoes);
            }


//            mochilas.forEach(mochila -> {
//                boolean isMelhorMochila = Objects.isNull(melhorMochila) ? true: mochila.getBeneficioTotal() > melhorMochila.getBeneficioTotal();
//
//                if(isMelhorMochila){
//                    mudouMelhorMochila.set(true);
//                    indiceMelhorMochila.set(mochilas.indexOf(mochila));
//                }
//            });




//            if(numGeracoes == NUM_MAX_GERACOES){
//                View.mostrarMelhorMochila(melhorMochila);
//                buscarSolucao = false;
//                View.mostrarEstatisticas(numGeracoes);
//            }

        }

    }

    private void verificarMochilas(List<Mochila> mochilas){
        mochilas.forEach(mochila -> {
            int peso = Avaliacao.calcularPeso(mochila.getCromossomo());
            int beneficio = Avaliacao.calcularBeneficio(mochila.getCromossomo());

            mochila.setBeneficioTotal(beneficio);
            mochila.setPesoTotal(peso);

            Avaliacao.verificarMochilasInvalidas(mochila);
        });
    }


    private List<Objeto> inicializarListaObjetos (){
        List<Objeto> objetos = new ArrayList<>();

        objetos.add(new Objeto(3, 5));
        objetos.add(new Objeto(3, 4));
        objetos.add(new Objeto(2, 7));
        objetos.add(new Objeto(4, 8));
        objetos.add(new Objeto(2, 4));
        objetos.add(new Objeto(3, 4));
        objetos.add(new Objeto(5, 6));
        objetos.add(new Objeto(2, 8));

        return objetos;
    }

}
