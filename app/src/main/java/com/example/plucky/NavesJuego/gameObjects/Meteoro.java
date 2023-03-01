package com.example.plucky.NavesJuego.gameObjects;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.widget.ImageView;

import com.example.plucky.GameObjects.AffineTransform;
import com.example.plucky.NavesJuego.math.Vector2D;
import com.example.plucky.NavesJuego.states.GameState;
import com.example.plucky.R;


public class Meteoro extends MovingObject{
	
	private Size size;

	public Meteoro(Vector2D position, Vector2D velocity, double maxVel, Bitmap texture, GameState gameState, Size size) {
		super(position, velocity, maxVel, texture, gameState);
		this.size= size;
		this.velocity= velocity.scale(maxVel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		position= position.add(velocity);
		if(position.getX()>Constantes.WIDTH) {
			position.setX(-width);	
		}
		if(position.getY()>Constantes.HEIGHT) {
			position.setY(-height);
		}
		
		if(position.getX()<-width) {
			position.setX(Constantes.WIDTH);
		}
		if(position.getY()<-height) {
			position.setY(Constantes.HEIGHT);
		}
		
		angle+= Constantes.DELTAANGLE/2;
	}
	
    @Override
	protected void Destroy() {
	gameState.diveMeteroros(this);
	gameState.addAcore(Constantes.METEORO_SCORE,position);
	super.Destroy();
		
	}
    
 

	@Override
	public void draw(Canvas g) {
		// TODO Auto-generated method stub

		at= AffineTransform.getTranslateInstance(position.getX(), position.getY());
		at.rotate(angle,width/2,height/2);
		g.drawBitmap(texture,at.tomarMatrix(),null);
	}

	public Size getSize() {
		return size;
	}

	
	
}
