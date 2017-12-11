package GA;

import java.util.ArrayList;
import java.util.List;

public class GeradorCromossomo {

    public static List<Integer> gerarCromossomo(Integer tam){
        List<Integer> cromossomo = new ArrayList<>();

        for(int i = 0; i < tam; i++){
            int binario  =  (int) Math.round(Math.random()*1);
            cromossomo.add(binario);
        }

        return cromossomo;
    }
}
