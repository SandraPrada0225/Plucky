package com.example.plucky.Clases;


//Esta clase hace referencia a la entidad amigo del jugador
public class   Amigos {
    String Amigo;
    String Tiempo;

    public Amigos(String amigo, String tiempo) {
        Amigo = amigo;
        Tiempo = tiempo;
    }

    public Amigos() {

    }

    public String getAmigo() {
        return Amigo;
    }

    public void setAmigo(String amigo) {
        Amigo = amigo;
    }

    public String getTiempo() {
        return Tiempo;
    }

    public void setTiempo(String tiempo) {
        Tiempo = tiempo;
    }
}