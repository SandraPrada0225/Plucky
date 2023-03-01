package com.example.plucky.GameObjects;

import android.graphics.Canvas;

public class Hilo extends Thread{
    static final long FPS=10;
    private AnimationView animationView;
    private boolean running=false;

    public Hilo(AnimationView animationView) {
        this.animationView = animationView;
    }

    public void setRunning(boolean run) {
           running = run;
    }

   @Override
    public void run() {
        long ticksPs=1000/FPS;
        long startTime;
        long sleepTime;
        while(running){
            Canvas c= null;
            startTime=System.currentTimeMillis();
            try {
                c = animationView.getHolder().lockCanvas();
                synchronized (animationView.getHolder()){
                    animationView.Draw(c);
                }

            }finally {
                if(c!=null){
                    animationView.getHolder().unlockCanvasAndPost(c);
                }
            }

           sleepTime= ticksPs-(System.currentTimeMillis()-startTime);

                try {
                    if(sleepTime>0){
                    sleep(sleepTime);
                    }else{
                        sleep(10);}
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

