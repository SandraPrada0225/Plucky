package com.example.plucky.Clases;

import android.graphics.Point;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

//Esta clase hace referencia al bloque que el jugador podrá elegir para programar a Plucky
public class Bloque {

    int idbloque;
    int concepto;
    String nombre;
    int position;
    int x;
    int y;
    int width;
    int height;
    int distancia;
    List<Bloque> bloques;;

    public Bloque(int idbloque, int concepto, String nombre, int position, int x, int y, int width, int heigh, int distancia, List<Bloque> bloques) {
        this.idbloque = idbloque;
        this.concepto = concepto;
        this.nombre = nombre;
        this.position = position;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.distancia=distancia;
        this.bloques= bloques;

    }



    public Bloque() {
    }

    public int getIdbloque() {
        return idbloque;
    }

    public void setIdbloque(int idbloque) {
        this.idbloque = idbloque;
    }

    public int getConcepto() {
        return concepto;
    }

    public void setConcepto(int concepto) {
        this.concepto = concepto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public List<Bloque> getBloques() {
        return bloques;
    }

    public void setBloques(List<Bloque> bloques) {
        this.bloques = bloques;
    }
}

