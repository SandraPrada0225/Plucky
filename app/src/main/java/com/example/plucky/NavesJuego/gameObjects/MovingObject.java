package com.example.plucky.NavesJuego.gameObjects;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.example.plucky.GameObjects.AffineTransform;
import com.example.plucky.Juego;
import com.example.plucky.NavesJuego.graphics.Assets;
import com.example.plucky.NavesJuego.graphics.Sound;
import com.example.plucky.NavesJuego.math.Vector2D;
import com.example.plucky.NavesJuego.states.GameState;
import com.example.plucky.R;

import java.util.ArrayList;


public abstract class MovingObject extends GameObject{

	protected Vector2D velocity;
	protected AffineTransform at;
	protected double angle;
	protected double maxVel;
	protected int width,height;
	protected GameState gameState;
	private MediaPlayer explosion;

	
	protected boolean dead;
	
	
	public MovingObject(Vector2D position, Vector2D velocity, double maxVel, Bitmap texture, GameState gameState) {
		super(position, texture);
		this.velocity= velocity;
		this.maxVel= maxVel;
		this.gameState= gameState;
 		width= texture.getWidth();
		height= texture.getHeight();
		angle=0;
		dead= false;
		explosion= Assets.explosionSonido;
		// TODO Auto-generated constructor stub
	}

	public MovingObject(){

	}
	
	protected void colicion(){
		ArrayList<MovingObject> movingObjects= gameState.getMovingObjects();
	for (int i = 0; i < movingObjects.size(); i++) {
		MovingObject m= movingObjects.get(i);
		if(m.equals(this)) 
			continue;
		
		double distance= m.getCenter().subtract(getCenter()).getMagnitude();
		
		if(distance< m.width/2+ width/2 && movingObjects.contains(this)&& !m.dead && !dead) {
			ObjectColision(m, this);
		}
	}	
	}
	
	private void ObjectColision( MovingObject a, MovingObject b) {
		
		if(a instanceof Player && ((Player)a).isSpawing()) {
			return;
			
		}
		if(b instanceof Player && ((Player)b).isSpawing()) {
			return;
			
		}
		if(!(a instanceof Meteoro && b instanceof Meteoro)) {
			gameState.playAnimacionExplosion(getCenter());
			a.Destroy();
			b.Destroy();
		}
		
	}
	
	protected void Destroy() {
		dead=true;
		if(!(this instanceof Laser)) {
			explosion.start();
		}
	}
	
	protected Vector2D getCenter() {
			return new Vector2D(position.getX()+width/2, position.getY()+height/2);
	}
	
	public boolean idDEad() {
		return dead;
	}

}
