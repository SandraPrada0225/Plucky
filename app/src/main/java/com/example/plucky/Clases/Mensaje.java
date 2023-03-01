package com.example.plucky.Clases;

//Esta clase hace referencia  a los mensajes del chat de clan
public class Mensaje {

    String IdMensaje;
    String mensaje;
    String idemisor;
    String tiempo;
    String tipo;

    public Mensaje(String IdMensaje, String mensaje, String idemisor, String tiempo, String tipo) {
        this.IdMensaje = IdMensaje;
        this.mensaje = mensaje;
        this.idemisor = idemisor;
        this.tiempo = tiempo;
        this.tipo = tipo;
    }

    public Mensaje() {
    }

    public String getIdMensaje() {
        return IdMensaje;
    }

    public void setIdMensaje(String idMensaje) {
        IdMensaje = idMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getIdemisor() {
        return idemisor;
    }

    public void setIdemisor(String idemisor) {
        this.idemisor = idemisor;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
