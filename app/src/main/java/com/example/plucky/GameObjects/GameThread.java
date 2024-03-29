package com.example.plucky.GameObjects;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

import androidx.annotation.Nullable;

public class GameThread extends Thread {
   private GameView view;
   private boolean running= false;

   public GameThread (GameView view){
       this.view= view;
   }
   public void setRunning(boolean run){
       running=run;
   }

    @SuppressLint("WrongCall")
    @Override
    public void run() {
       while(running){
           Canvas c=null;
           try {
               c=view.getHolder().lockCanvas();
               synchronized (view.getHolder()){
                   view.onDraw(c);
               }
           }finally {
               if (c!=null){
                   view.getHolder().unlockCanvasAndPost(c);
               }
           }
       }
    }
}
