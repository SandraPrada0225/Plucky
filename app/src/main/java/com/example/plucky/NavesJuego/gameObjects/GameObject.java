package com.example.plucky.NavesJuego.gameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.ImageView;

import com.example.plucky.NavesJuego.math.Vector2D;


public abstract class GameObject {
	
	protected Bitmap texture;
	protected Vector2D position;
	
	public GameObject(Vector2D position, Bitmap texture) {
		
		this.position=position;
		this.texture=texture;
	}

	public GameObject(){

	}

	public abstract void update();
	public abstract void draw(Canvas g);

	public Bitmap getTexture() {
		return texture;
	}

	public void setTexture(Bitmap texture) {
		this.texture = texture;
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	
}
