package com.example.plucky.Clases;

//Esta clase hace referencia  a los sticker dentro del chat de clan
public class Sticker {

    String idSticker;
    String nombre;
    String animacion;

    public Sticker(String idSticker, String nombre, String animacion) {
        this.idSticker = idSticker;
        this.nombre = nombre;
        this.animacion = animacion;
    }

    public String getIdSticker() {
        return idSticker;
    }

    public void setIdSticker(String idSticker) {
        this.idSticker = idSticker;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnimacion() {
        return animacion;
    }

    public void setAnimacion(String animacion) {
        this.animacion = animacion;
    }
}
