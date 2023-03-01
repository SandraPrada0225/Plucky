package com.example.plucky.Clases;

//Esta clase hace referencia a las solicitudes de amistad
public class Solicitudes {

    String idSolicitud;
    String usuarioRecibir;
    Usuario usuarioEnviar;
    String tiempo;

    public Solicitudes(String idSolicitud, Usuario usuarioEnviar, String usuarioRecibir, String tiempo) {
        this.idSolicitud = idSolicitud;
        this.usuarioRecibir = usuarioRecibir;
        this.usuarioEnviar= usuarioEnviar;
        this.tiempo = tiempo;
    }

    public Solicitudes(){

    }
    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }


    public String getUsuarioRecibir() {
        return usuarioRecibir;
    }

    public void setUsuarioRecibir(String usuarioRecibir) {
        this.usuarioRecibir = usuarioRecibir;
    }

    public Usuario getUsuarioEnviar() {
        return usuarioEnviar;
    }

    public void setUsuarioEnviar(Usuario usuarioEnviar) {
        this.usuarioEnviar = usuarioEnviar;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}
