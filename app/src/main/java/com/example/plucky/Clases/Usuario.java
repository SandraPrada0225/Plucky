package com.example.plucky.Clases;

import java.util.List;

//Este clase hace referecia a la Entidad Jugador
public class Usuario {
    String Nombre,Correo, Fecha,Imagen, Clave,Universidad,Uid,Semestre,Clan;
    int  Monedas, Gemas, Nivel;
    List<Amigos> Amigos;
    List<Notificacion> notificacions;
    List<Solicitudes> solicitudes;




    public Usuario(String uid, String correo, String clave, String nombre, String universidad, String semestre, String fecha, int monedas, int gemas, int nivel, String imagen, String clan, List<Amigos> amigos, List<Notificacion>notificaciones, List<Solicitudes> solicitudesamistad ) {
        Uid=uid;
        Nombre = nombre;
        Correo = correo;
        Fecha = fecha;
        Clave = clave;
        Universidad = universidad;
        Semestre = semestre;
        Monedas = monedas;
        Gemas = gemas;
        Nivel = nivel;
        Imagen=imagen;
        Clan=clan;
        Amigos=amigos;
        notificacions=notificaciones;
        solicitudes=solicitudesamistad;
    }

    public Usuario() {
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public String getUniversidad() {
        return Universidad;
    }

    public void setUniversidad(String universidad) {
        Universidad = universidad;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getSemestre() {
        return Semestre;
    }

    public void setSemestre(String semestre) {
        Semestre = semestre;
    }

    public int getMonedas() {
        return Monedas;
    }

    public void setMonedas(int monedas) {
        Monedas = monedas;
    }

    public int getGemas() {
        return Gemas;
    }

    public void setGemas(int gemas) {
        Gemas = gemas;
    }

    public int getNivel() {
        return Nivel;
    }

    public void setNivel(int nivel) {
        Nivel = nivel;
    }


    public String getClan() {
        return Clan;
    }

    public void setClan(String clan) {
        Clan = clan;
    }

    public List<Amigos> getAmigos() {
        return Amigos;
    }

    public void setAmigos(List<Amigos> amigos) {
        Amigos = amigos;
    }

    public List<Notificacion> getNotificacions() {
        return notificacions;
    }

    public void setNotificacions(List<Notificacion> notificacions) {
        this.notificacions = notificacions;
    }

    public List<Solicitudes> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<Solicitudes> solicitudes) {
        this.solicitudes = solicitudes;
    }


}
