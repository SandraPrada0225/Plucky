package com.example.plucky.GameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.plucky.GameObjects.Hilo;
import com.example.plucky.GameObjects.Sprite;
import com.example.plucky.NavesJuego.graphics.Assets;
import com.example.plucky.NavesJuego.states.Estado;
import com.example.plucky.R;

public class AnimationView extends SurfaceView {
    private Bitmap bmp,boton1,boton2,boton3,boton4;
    Bitmap nuevo;
    private SurfaceHolder surfaceHolder;
    private Hilo hilo;
    private Sprite sprite;
    private Estado estado;




    public AnimationView(Context context) {
        super(context);
        surfaceHolder= getHolder();
        hilo= new Hilo(this);
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
            hilo.setRunning(true);
            hilo.start();
            }
            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
            }
            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                boolean retry= true;
                hilo.setRunning(false);
                while(retry){
                    try {
                        hilo.join();
                        retry=false;
                    } catch (InterruptedException e) {
                       // e.printStackTrace();
                    }
                }
            }
        });
        bmp= BitmapFactory.decodeResource(getResources(), R.drawable.avatar2);
        boton1= BitmapFactory.decodeResource(getResources(), R.drawable.robotica1);
        boton2=BitmapFactory.decodeResource(getResources(), R.drawable.robotica2);
        boton3=BitmapFactory.decodeResource(getResources(), R.drawable.robotica3);
        boton4=BitmapFactory.decodeResource(getResources(), R.drawable.avatar4);
        boton1=Bitmap.createScaledBitmap(boton1,100,100,false);
        boton2=Bitmap.createScaledBitmap(boton2,100,100,false);
        boton3=Bitmap.createScaledBitmap(boton3,100,100,false);
        boton4=Bitmap.createScaledBitmap(boton4,100,100,false);
        nuevo=Bitmap.createScaledBitmap(bmp,100,100,false);
        sprite= new Sprite(this,nuevo);
        Assets.init(getResources(),context);

        }


    protected void Draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(boton1,10,10,null);
        canvas.drawBitmap(boton2,30,20,null);
        canvas.drawBitmap(boton3,30,30,null);
        canvas.drawBitmap(boton4,40,40,null);
        //hola

        estado.getEstadoActual().update();


     //   sprite.Draw(canvas);
    }
}




