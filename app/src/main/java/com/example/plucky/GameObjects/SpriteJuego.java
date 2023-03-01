package com.example.plucky.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

import java.util.Random;

public class SpriteJuego {

    private int x=10;
    private int y=10;
    private int xSpeed=10;
    private  int ySpeed=10;

    private GameView gameView;
    private Bitmap bmp;

    public SpriteJuego(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
        Random rnd= new Random(System.currentTimeMillis());
        x= rnd.nextInt(200)+1;
        y= rnd.nextInt(300)+1;

        Random rnd1= new Random(System.currentTimeMillis());
        xSpeed= rnd.nextInt(60)+1;
        ySpeed= rnd.nextInt(70)+1;

    }

    public void update(){
        if(x>gameView.getWidth()-bmp.getWidth()-xSpeed){
            xSpeed= -xSpeed;
        }
        if (x+xSpeed<0){
            xSpeed=15;
        }
        x=x+xSpeed;

        if(y>gameView.getHeight()-bmp.getHeight()-ySpeed){
            ySpeed= -ySpeed;
        }
        if (y+ySpeed<0){
            ySpeed=15;
        }
        y=y+ySpeed;
    }

    public boolean isCollision(float x2 ,float y2){
        return false;
    }

    public void onDraw(Canvas canvas) {
      update();
      canvas.drawColor(Color.WHITE);
      canvas.drawBitmap(bmp,x,y,null);

    }
}
