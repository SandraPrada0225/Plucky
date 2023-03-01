package com.example.plucky.GameObjects;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.plucky.R;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView {

    private Bitmap bmp;
    private SurfaceHolder holder;
    private GameThread gameThread;
    private List<SpriteJuego> spriteJuegos= new ArrayList<SpriteJuego>();


    public GameView(Context context) {
        super(context);
        holder=getHolder();
        gameThread= new GameThread(this);

        holder.addCallback(new SurfaceHolder.Callback() {
            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
               gameThread.setRunning(true);
               gameThread.start();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                boolean retry = true;
                gameThread.setRunning(false);
                while(retry){
                    try{
                        gameThread.join();
                        retry= false;
                    }catch (InterruptedException e){

                    }
                }
            }
        });

        spriteJuegos.add(createSprite(R.drawable.avatar2));
        spriteJuegos.add(createSprite(R.drawable.avatar2));
        spriteJuegos.add(createSprite(R.drawable.avatar2));
        spriteJuegos.add(createSprite(R.drawable.avatar2));
        spriteJuegos.add(createSprite(R.drawable.avatar2));
        spriteJuegos.add(createSprite(R.drawable.avatar3));

    }

    //his method will return a single sprite
    private SpriteJuego createSprite(int resourse){
        Bitmap bmp= BitmapFactory.decodeResource(getResources(),resourse);
        bmp=Bitmap.createScaledBitmap(bmp,100,100,false);
        return new SpriteJuego(this,bmp);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(canvas!= null){
            canvas.drawColor(Color.BLACK);
          for (SpriteJuego spriteJuego: spriteJuegos){
              spriteJuego.onDraw(canvas);
          }
        }
    }

   protected void onUpdate(){
        for (int i=0;i<spriteJuegos.size();i++){
            spriteJuegos.get(i).update();
            for (int j=0;j<spriteJuegos.size();j++){

            }
        }
   }


}
