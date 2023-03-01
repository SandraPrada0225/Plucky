package com.example.plucky.Clases;

import java.util.List;

public class Jugador {

    String id;
    String tiempo;
    List<Bloque> Bloques;
    String arma;
    String mascota;
    String escudo;

    public Jugador(String id, String tiempo, List<Bloque> bloques, String arma, String mascota, String escudo) {
        this.id = id;
        this.tiempo = tiempo;
        Bloques = bloques;
        this.arma = arma;
        this.mascota = mascota;
        this.escudo = escudo;
    }

    public Jugador() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public List<Bloque> getBloques() {
        return Bloques;
    }

    public void setBloques(List<Bloque> bloques) {
        Bloques = bloques;
    }

    public String getArma() {
        return arma;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }

    public String getMascota() {
        return mascota;
    }

    public void setMascota(String mascota) {
        this.mascota = mascota;
    }

    public String getEscudo() {
        return escudo;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }
}

