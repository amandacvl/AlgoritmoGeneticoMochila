package model;

import java.util.List;

public class Populacao {
    private List<Objeto> objetos;

    public Populacao(List<Objeto> objetos) {
        this.objetos = objetos;
    }

    public List<Objeto> getObjetos() {
        return objetos;
    }

    public void setObjetos(List<Objeto> objetos) {
        this.objetos = objetos;
    }
}
