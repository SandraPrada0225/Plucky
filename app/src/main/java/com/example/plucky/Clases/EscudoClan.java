package com.example.plucky.Clases;

//Esta clase hace referencia al escudo de un clan
public class EscudoClan {

    int idEscudoclan, escudoclan ;

    public EscudoClan(int idEscudoclan, int escudoclan) {
        this.idEscudoclan = idEscudoclan;
        this.escudoclan = escudoclan;
    }

    public int getIdEscudoclan() {
        return idEscudoclan;
    }

    public void setIdEscudoclan(int idEscudoclan) {
        this.idEscudoclan = idEscudoclan;
    }

    public int getEscudoclan() {
        return escudoclan;
    }

    public void setEscudoclan(int escudoclan) {
        this.escudoclan = escudoclan;
    }
}
