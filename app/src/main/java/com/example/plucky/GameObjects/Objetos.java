package com.example.plucky.GameObjects;

import android.content.res.Resources;
import android.graphics.Canvas;

public abstract class Objetos {
    protected int imagen;
    protected Vector2DPlucky position;

    public Objetos(int imagen, Vector2DPlucky position) {
        this.imagen = imagen;
        this.position = position;
    }

    public abstract void update();
    public abstract void draw(Canvas canvas, Resources res);


    public Vector2DPlucky getPosition() {
        return position;
    }

    public void setPosition(Vector2DPlucky position) {
        this.position = position;
    }


}
