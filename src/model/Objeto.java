package model;

public class Objeto {
    private Integer beneficio;
    private Integer peso;

    public Objeto(Integer beneficio, Integer peso) {
        this.beneficio = beneficio;
        this.peso = peso;
    }

    public Integer getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(Integer beneficio) {
        this.beneficio = beneficio;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }
}
