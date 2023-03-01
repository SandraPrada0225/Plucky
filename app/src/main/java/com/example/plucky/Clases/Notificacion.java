package com.example.plucky.Clases;


//Esta clase hace refrencia a las notificaciones del clan
public class Notificacion {

    String idNotificacion;
    String usuario;
    String usuarioEmisor;
    String clan;
    String imagenClan;
    String imagenUsuario;
    String tiempo;

    public Notificacion(String idNotificacion, String usuario,String clan, String imagenClan,  String usuarioEmisor, String imagenUsuario, String tiempo) {
        this.idNotificacion = idNotificacion;
        this.usuario = usuario;
        this.usuarioEmisor = usuarioEmisor;
        this.clan = clan;
        this.imagenClan = imagenClan;
        this.imagenUsuario = imagenUsuario;
        this.tiempo = tiempo;
    }

    public Notificacion() {
    }

    public String getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(String idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuarioEmisor() {
        return usuarioEmisor;
    }

    public void setUsuarioEmisor(String usuarioEmisor) {
        this.usuarioEmisor = usuarioEmisor;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public String getImagenClan() {
        return imagenClan;
    }

    public void setImagenClan(String imagenClan) {
        this.imagenClan = imagenClan;
    }

    public String getImagenUsuario() {
        return imagenUsuario;
    }

    public void setImagenUsuario(String imagenUsuario) {
        this.imagenUsuario = imagenUsuario;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}
