package view;

import model.Mochila;

import java.sql.SQLOutput;

public class View {
    public static void iniciar() {
        System.out.println("Algoritmo Genético - Problema da Mochila");
    }

    public static void mostrarEstatisticas(int numGeracoes) {
        System.out.println("Número de Gerações: "+ numGeracoes);
    }

    public static void mostrarMelhorMochila(Mochila mochila){

        System.out.println("--------------------MELHOR MOCHILA-------------------");
        System.out.println("Cromossomo: " + mochila.getCromossomo().toString());
        System.out.println("Benefício: " + mochila.getBeneficioTotal());
        System.out.println("Peso: "+ mochila.getPesoTotal());

    }

}
