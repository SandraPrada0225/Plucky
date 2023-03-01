package com.example.plucky.Clases;

import java.util.List;

//Esta clase hace referecia a la entidad clan del jugador
public class Clan {


    String Nombre, descripcion,estado;
    int imagen;
    List<Usuario> integrantes;
    List<Mensaje> mensajes;

    public Clan( String nombre, int imagen, String descripcion, String estado, List<Usuario> integrantes, List<Mensaje> mensajes) {
        Nombre = nombre;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.estado = estado;
        this.integrantes = integrantes;
        this.mensajes= mensajes;
    }
    public Clan() {
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int  getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Usuario> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<Usuario> integrantes) {
        this.integrantes = integrantes;
    }


    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
}
