package model;

public class Objeto {
    private int beneficio;
    private int peso;
    private boolean status;

    public Objeto(int beneficio, int peso, boolean status) {
        this.beneficio = beneficio;
        this.peso = peso;
        this.status = status;
    }

    public int getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(int beneficio) {
        this.beneficio = beneficio;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
