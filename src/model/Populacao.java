package model;

import java.util.List;

public class Populacao {
    private List<Mochila> mochilas;

    public Populacao(List<Mochila> mochilas) {
        this.mochilas = mochilas;
    }

    public List<Mochila> getMochilas() {
        return mochilas;
    }

    public void setMochilas(List<Mochila> mochilas) {
        this.mochilas = mochilas;
    }
}
