package com.example.plucky.GameObjects;

import android.content.res.Resources;
import android.graphics.Canvas;

import com.example.plucky.R;

public class EstadoJuego  {
    private Plucky plucky;

   public EstadoJuego() {
    plucky = new Plucky(R.drawable.avatar2,new Vector2DPlucky(100,500));
    }

    public void update(){
       plucky.update();
    }

    public void draw(Canvas canvas, Resources res) {
        plucky.draw(canvas, res);
    }

}
