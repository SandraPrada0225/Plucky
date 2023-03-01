package com.example.plucky.GameObjects;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Plucky extends Objetos{
    Bitmap avatar;
    static Bitmap nuevo;
    static Canvas canvasPlucky;

    public Plucky(int imagen, Vector2DPlucky position) {
        super(imagen, position);

    }

    public void update() {
        System.out.println("tato de hacer esto "+Movimiento.movimiento);

        if(Movimiento.movimiento==1){
            position.setX(position.getX()+10);
        }else if(Movimiento.movimiento==2){
            position.setX(position.getX()-10);
        }else if(Movimiento.movimiento==3){
            position.setY(position.getY()+10);
        }else if(Movimiento.movimiento==4){
            position.setY(position.getY()-10);
        }
    }

    public void draw(Canvas canvas, Resources res) {
        update();
        avatar= BitmapFactory.decodeResource(Resources.getSystem(),imagen);
        canvasPlucky=canvas;
        nuevo=Bitmap.createScaledBitmap(avatar,100,100,false);
        canvas.drawBitmap(nuevo,(int)position.getX(),(int)position.getY(),null);
        }


}
