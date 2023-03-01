package com.example.plucky.NavesJuego.gameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import com.example.plucky.Clases.Bloque;
import com.example.plucky.GameObjects.AffineTransform;
import com.example.plucky.NavesJuego.graphics.Assets;
import com.example.plucky.NavesJuego.input.KeyBoard;
import com.example.plucky.NavesJuego.math.Vector2D;
import com.example.plucky.NavesJuego.states.GameState;

import java.util.ArrayList;
import java.util.List;

public class Enemigo extends MovingObject{

    private Vector2D heading;
    private Vector2D aceleracion;
    private final double ACC=0.04;
    private final double DeltaAngule= 0.1;
    private boolean acelerando= false;
    private Cronometro cronometro;

    private boolean spawing, visible;

    private Cronometro parpadeoTiempo, aparecerTiempo;

    private MediaPlayer disparar,gameOver, finalizar;

    private boolean arriba, abajo, izquierda, derecha;
    public int i;
    private List<Bloque> bloques;


    public Enemigo(Vector2D position, Vector2D velocity, double maxVel, Bitmap texture, GameState gameState, List<Bloque> bloques, String arma, String escudo, String mascota) {
        super(position, velocity, maxVel, texture, gameState);
        heading= new Vector2D(0,1);
        aceleracion= new Vector2D();
        cronometro= new Cronometro();
        aparecerTiempo= new Cronometro();
        parpadeoTiempo= new Cronometro();
        disparar= Assets.laserPlayerSonido;
        gameOver= Assets.perderSonido;
        finalizar= Assets.gameOverSonido;
        arriba=false;
        abajo=false;
        izquierda=false;
        derecha= false;
        i=0;
        this.bloques=bloques;

        // TODO Auto-generated constructor stub
    }



    @Override
    public void update() {

        if(bloques==null){
            System.out.println("/////////////////////////////////////pinche syso");
        }else {
            switch (bloques.get(i).getIdbloque()) {

                case 1:
                    arriba = true;
                    izquierda=false;
                    derecha=false;
                    abajo=false;
                    break;
                case 2:
                    abajo = true;
                    arriba = false;
                    derecha=false;
                    izquierda=false;
                    break;
                case 3:
                    izquierda = true;
                    abajo = false;
                    arriba = false;
                    derecha=false;
                    break;
                case 4:
                    derecha = true;
                    izquierda = false;
                    abajo = false;
                    arriba = false;
                    break;
            }
            i++;
            if(i==bloques.size()){
                i=0;
            }
        }






        if(!aparecerTiempo.isRunning()){
            spawing=false;
            visible= true;
        }

        if(spawing) {
            if(!parpadeoTiempo.isRunning()) {
                parpadeoTiempo.run(Constantes.PARPADEO_TIME);
                visible=!visible;
            }
        }

        if(abajo && !cronometro.isRunning() && !spawing) {
            gameState.getMovingObjects().add(0,new Laser(getCenter().add( heading.scale(width)),
                    heading, 10, angle,Assets.laserrojo,gameState));
            cronometro.run(Constantes.FIRERATE);

            disparar.start();
        }

	/*	if(disparar.getFrameProsition()>8500) {
			disparar.stop();
		}
		*/

        if(derecha) {
            angle+=Math.PI/20;
        }if(izquierda) {
            angle-=DeltaAngule;

        }
        if(arriba) {
            aceleracion=heading.scale(ACC);
            acelerando=true;
        }else {
            if(velocity.getMagnitude()!=0) {
                aceleracion= (velocity.scale(-1).normalizar()).scale(ACC/2);
                acelerando=false;
            }
        }

        velocity= velocity.add(aceleracion);
        velocity=velocity.limit(maxVel);

        heading= heading.setDirection(angle-Math.PI/2);//siempre se trabaja en radianes y no en grados

        position=position.add(velocity);
        if(position.getX()>Constantes.WIDTH) {
            position.setX(0);
        }
        if(position.getY()>Constantes.HEIGHT) {
            position.setY(0);
        }

        if(position.getX()<0) {
            position.setX(Constantes.WIDTH);
        }
        if(position.getY()<0) {
            position.setY(Constantes.HEIGHT);
        }


        cronometro.update();
        aparecerTiempo.update();
        parpadeoTiempo.update();

        colicion();
    }

    @Override
    protected void Destroy() {
        // TODO Auto-generated method stub
        spawing= true;
        aparecerTiempo.run(Constantes.APARECER_TIME);
        gameOver.start();

        if(!gameState.substractLife()) {
            finalizar.start();
            gameState.gameOver();
            super.Destroy();
        }
        resetValues();
    }

    private void resetValues() {
        angle=0;
        velocity= new Vector2D();
        position= new Vector2D(Constantes.WIDTH/2-Assets.player.getWidth()/2,Constantes.WIDTH/2-Assets.player.getHeight()/2);
    }

    @Override
    public void draw(Canvas g) {
        // TODO Auto-generated method stub

        if(!visible)
            return;


        AffineTransform at1= AffineTransform.getTranslateInstance(position.getX()+width/2+5, position.getY()+height/2+10);
        AffineTransform at2= AffineTransform.getTranslateInstance(position.getX()+5, position.getY()+height/2+10);
        at1.rotate(angle, -5, -10);
        at2.rotate(angle, width/2-5, -10);

        if(acelerando) {
            g.drawBitmap(Assets.speed,at1.tomarMatrix(),null);
            g.drawBitmap(Assets.speed,at2.tomarMatrix(),null);
        }

        at= AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle,width/2,height/2);

        g.drawBitmap(texture,at.tomarMatrix(),null);

    }

    public boolean isSpawing() {
        return spawing;
    }
}
