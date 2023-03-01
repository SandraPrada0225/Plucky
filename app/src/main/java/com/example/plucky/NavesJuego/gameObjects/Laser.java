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


public class Laser extends MovingObject {

	public Laser(Vector2D position, Vector2D velocity, double maxVel, double angle, Bitmap texture, GameState gameState) {
		super(position, velocity, maxVel, texture, gameState);
		this.angle=angle;
		this.velocity=velocity.scale(maxVel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		position= position.add(velocity);
		
		if(position.getX()<0||position.getX()>Constantes.WIDTH|| position.getY()<0 || position.getY()> Constantes.HEIGHT) 
		{
			Destroy();
		}
		
		colicion();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Canvas g) {
		// TODO Auto-generated method stub

		at= AffineTransform.getTranslateInstance(position.getX()- width/2, position.getY());
		at.rotate(angle, width/2,0);
		g.drawBitmap(texture,at.tomarMatrix(),null);
		
	}
	@Override
	public Vector2D getCenter() {
		return new Vector2D(position.getX()+width/2, position.getY()+width/2);
	}

	

}
