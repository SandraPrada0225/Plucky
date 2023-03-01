package com.example.plucky.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite {
    private int x = 0;
    private int y = 10;
    private int xSpeed = 5;
    private  int ySpeed=10;
    private AnimationView animationView;
    private Bitmap bmp;

    public Sprite(AnimationView animationView, Bitmap bmp) {
        this.animationView = animationView;
        this.bmp = bmp;
    }

    private void update() {
        if (x > animationView.getWidth() - bmp.getWidth() - xSpeed) {
            xSpeed = -5;
        }
        if (x + xSpeed < 0) {
            xSpeed = 5;
        }

        if(y>animationView.getHeight()-bmp.getHeight()-ySpeed){
            ySpeed= -ySpeed;
        }
        if (y+ySpeed<0){
            ySpeed=15;
        }
        y=y+ySpeed;
        x = x + xSpeed;
    }

    public void Draw(Canvas canvas) {

        if( Movimiento.ejecutables==null){
            x++;
        }else{
           for (int i = 0; Movimiento.ejecutables.size() > i; i++) {
                System.out.println("este es el bloque " + Movimiento.ejecutables.get(i).getIdbloque());
                switch (Movimiento.ejecutables.get(i).getIdbloque()) {
                    case 1:
                        if (x > animationView.getWidth() - bmp.getWidth() - xSpeed) {
                            xSpeed = -xSpeed;
                        }
                        if (x + xSpeed < 0) {
                            xSpeed = 5;
                        }
                        x = x + xSpeed;
                        break;
                    case 2:
                        if(y>animationView.getHeight()-bmp.getHeight()-ySpeed){
                            ySpeed= -ySpeed;
                        }
                        if (y+ySpeed<0){
                            ySpeed=15;
                        }
                        y=y+ySpeed;

                        break;
                }
        }
         //   update();
        }
        canvas.drawBitmap(bmp, x, y, null);
    }

}
