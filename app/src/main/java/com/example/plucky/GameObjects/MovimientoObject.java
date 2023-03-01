package com.example.plucky.GameObjects;

import android.content.res.Resources;
import android.graphics.Canvas;

public class MovimientoObject extends Objetos{

    protected Vector2DPlucky velocidad;
    protected double angulo;



    public MovimientoObject(int imagen, Vector2DPlucky position) {
        super(imagen, position);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas, Resources res) {

    }


}
