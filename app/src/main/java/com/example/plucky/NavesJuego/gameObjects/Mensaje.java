package com.example.plucky.NavesJuego.gameObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.fonts.Font;

import com.example.plucky.NavesJuego.graphics.Text;
import com.example.plucky.NavesJuego.math.Vector2D;



public class Mensaje {

	
	
	private float alpha;
	private String text;
	private Vector2D posicion;
	private int color;
	private boolean center;
	private boolean fade;
	private Font font;
	private final float deltaAlpha=0.01f;
	
	private boolean dead;
	
	public Mensaje( Vector2D posicion,boolean fade, String text, int color,boolean center,
			 Font font) {
		super();
		this.text = text;
		this.posicion = posicion;
		this.color = color;
		this.center = center;
		this.fade = fade;
		this.font = font;
		dead= false;
		if(fade)
			alpha=1;
		else
			alpha=0;
		
	}
	public void draw(Canvas g2d) {
		
		//g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		Text.drawText(g2d, text, posicion, center, color, font);
		//g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		
		posicion.setY(posicion.getY()-1);
		if(fade)
			alpha-= deltaAlpha;
		else
			alpha+= deltaAlpha;
		
		if(fade && alpha<0 || !fade && alpha>1) {
			dead= true;
		}
		
		
		
	}
	
	public boolean isDEad() {
		return dead;
	}
	
	

	
}
