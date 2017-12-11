package GA;

import model.Mochila;
import model.Populacao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Evolucao {
    private final int PONTO_DIVISAO = 3;

    public static void efetuarCrossOver(Mochila mochila1, Mochila mochila2){
        List<Integer> cromossomoFilho1 = new ArrayList<>();
        List<Integer> cromossomoFilho2 = new ArrayList<>();

        List<Integer> cromossomoMochila1 = mochila1.getCromossomo();
        List<Integer> cromossomoMochila2 = mochila2.getCromossomo();

        AtomicInteger contadorMochila = new AtomicInteger(0);

        List<Integer> parte1Mochila1 = cromossomoMochila1.stream()
                .filter(bin -> {
                    //contador incrementado, i++
                    contadorMochila.getAndIncrement();

                    //filtra objetos que atendem a condição abaixo
                    return contadorMochila.get() <= 4;
                })
                .collect(Collectors.toList());

        contadorMochila.set(0);

        List<Integer> parte2Mochila1 = cromossomoMochila1.stream()
                .filter(bin -> {
                    contadorMochila.getAndIncrement();
                    return contadorMochila.get() > 4;
                })
                .collect(Collectors.toList());

        contadorMochila.set(0);

        List<Integer> parte1Mochila2 = cromossomoMochila2.stream()
                .filter(bin -> {
                    contadorMochila.getAndIncrement();
                    return contadorMochila.get() <= 4;
                })
                .collect(Collectors.toList());

        contadorMochila.set(0);

        List<Integer> parte2Mochila2 = cromossomoMochila2.stream()
                .filter(bin -> {
                    contadorMochila.getAndIncrement();
                    return contadorMochila.get() > 4;
                })
                .collect(Collectors.toList());

        cromossomoFilho1.addAll(parte1Mochila1);
        cromossomoFilho1.addAll(parte2Mochila2);
        mochila1.setCromossomo(cromossomoFilho1);

        cromossomoFilho2.addAll(parte2Mochila1);
        cromossomoFilho2.addAll(parte1Mochila2);
        mochila2.setCromossomo(cromossomoFilho2);
    }

    public static void efetuarMutacao (Mochila mochila){
        List<Integer> cromossomo = mochila.getCromossomo();

        int mutacao = (int)(Math.random()*cromossomo.size());
        int binario  =  (int) Math.round(Math.random()*1);

        cromossomo.set(mutacao, binario);
        mochila.setCromossomo(cromossomo);
    }

    public static Mochila selecionarPorRoleta(Populacao populacao){
       List<Mochila> mochilas = populacao.getMochilas();

       AtomicInteger aptidao = new AtomicInteger(0);
       mochilas.forEach(mochila -> aptidao.set(aptidao.get() + mochila.getBeneficioTotal()));

       int aptidaoPopulacao = aptidao.get();

       int roleta = (int)(Math.random() * aptidaoPopulacao);

       aptidao.set(0);

       AtomicInteger indiceInviduoEscolhido = new AtomicInteger(0);

        final boolean[] naoEscolheuIndividuo = {true};

       mochilas.forEach(mochila -> {
           if(naoEscolheuIndividuo[0]){

               if(aptidao.get() < roleta){
                   aptidao.set(aptidao.get() + mochila.getBeneficioTotal());
               } else {
                   indiceInviduoEscolhido.set(mochilas.indexOf(mochila));
                   naoEscolheuIndividuo[0] = false;
               }
           }
       });

       return mochilas.get(indiceInviduoEscolhido.get());
    }
}
