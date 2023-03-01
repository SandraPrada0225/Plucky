package com.example.plucky.GameObjects;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.plucky.R;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class AnimationHilo extends SurfaceView {

    SurfaceHolder surfaceHolder;
    int xpos=50;
    Handler handler;

    public AnimationHilo(Context context) {
        super(context);
        surfaceHolder=getHolder();




        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {

             Canvas c=surfaceHolder.lockCanvas();
             MyDraw(c);
             surfaceHolder.unlockCanvasAndPost(c);

                handler= new Handler() {
                    @Override
                    public void publish(LogRecord record) {


                    }

                    @Override
                    public void flush() {

                    }

                    @Override
                    public void close() throws SecurityException {

                    }
                };

            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });
    }

    public void MyDraw(Canvas c){

        Paint p= new Paint();
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.FILL);

        c.drawCircle(150,150,50,p);
        c.drawRect(150,200,350,400,p);
        c.drawText("Milena la mejor ",150,500,p);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.avatar2);
        Bitmap nuevo=Bitmap.createScaledBitmap(bitmap,100,100,false);
        c.drawBitmap(nuevo, ++xpos,550,null);



    }


}
