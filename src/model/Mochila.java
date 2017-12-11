package model;

import java.util.List;

public class Mochila {
    public static int PESO_MAX = 25;
    private List<Integer> cromossomo;
    private int pesoTotal;
    private int beneficioTotal;

    public Mochila(List<Integer> cromossomo) {
        this.cromossomo = cromossomo;
    }

    public static int getPesoMax() {
        return PESO_MAX;
    }

    public static void setPesoMax(int pesoMax) {
        PESO_MAX = pesoMax;
    }

    public List<Integer> getCromossomo() {
        return cromossomo;
    }

    public void setCromossomo(List<Integer> cromossomo) {
        this.cromossomo = cromossomo;
    }

    public int getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(int peso) {
        this.pesoTotal = peso;
    }

    public int getBeneficioTotal() {
        return beneficioTotal;
    }

    public void setBeneficioTotal(int beneficio) {
        this.beneficioTotal = beneficio;
    }
}
