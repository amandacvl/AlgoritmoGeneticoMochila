package GA;

import model.Mochila;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Avaliacao {

    //Se o peso da mochila for maior do que o peso máximo, o seu benefício será penalizado para "-1".
    public static void verificarMochilasInvalidas(Mochila mochila){
        if(mochila.getPesoTotal() > Mochila.PESO_MAX){
            mochila.setBeneficioTotal(-1);
        }
    }
    //Dado um cromossomo, retorno o peso total.
    public static Integer calcularPeso(List<Integer> cromossomo){
        AtomicReference<Integer> pesoTotal = new AtomicReference<>(0);

        for(int i = 0; i < cromossomo.size(); i++){
            if(cromossomo.get(i).equals(1)){
                int indiceObjeto = i;
                pesoTotal.set(pesoTotal.get() + Controller.objetos.get(indiceObjeto).getPeso());
            }
        }

        return pesoTotal.get();
    }

    public static Integer calcularBeneficio (List<Integer> cromossomo){
        AtomicReference<Integer> beneficioTotal = new AtomicReference<>(0);

        for(int i = 0; i < cromossomo.size(); i++){
            if(cromossomo.get(i).equals(1)){
                int indiceObjeto = i;
                beneficioTotal.set(beneficioTotal.get() + Controller.objetos.get(indiceObjeto).getBeneficio());
            }
        }

        return beneficioTotal.get();
    }
}
