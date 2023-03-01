package com.example.plucky.Clases;

//Esta clase hace referencia a la habilidad que jugador seleccionará para Plucky: Escudo, arma, mascota
public class Habilidad {
    int idHabilidad;
    int tipo;
    String descripcion;
    int imagen;
    String nombre;
    int imagenseleccion;

    public Habilidad(int idHabilidad,int tipo,String nombre, String descripcion, int imagen, int imagenseleccion) {
        this.idHabilidad = idHabilidad;
        this.tipo=tipo;
        this.descripcion = descripcion;
        this.imagen=imagen;
        this.nombre=nombre;
        this.imagenseleccion=imagenseleccion;
    }

    public int getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(int idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagenseleccion() {
        return imagenseleccion;
    }

    public void setImagenseleccion(int imagenseleccion) {
        this.imagenseleccion = imagenseleccion;
    }
}
