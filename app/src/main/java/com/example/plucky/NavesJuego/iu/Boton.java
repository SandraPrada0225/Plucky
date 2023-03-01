package com.example.plucky.NavesJuego.iu;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.widget.ImageView;

import androidx.constraintlayout.solver.widgets.Rectangle;

import com.example.plucky.NavesJuego.graphics.Assets;
import com.example.plucky.NavesJuego.graphics.Text;
import com.example.plucky.NavesJuego.input.Action;
import com.example.plucky.NavesJuego.input.MouseInput;
import com.example.plucky.NavesJuego.math.Vector2D;
import com.example.plucky.R;


public class Boton {

	private Bitmap mouseOutImg,mouseInImg;
	private boolean mouseIn;
	private Rectangle box;
	private String text;
	private Action action;
	
	
	public Boton(Bitmap mouseOutImg, Bitmap mouseInImg, int x, int y, String text, Action action) {
		super();
		this.mouseOutImg = mouseOutImg;
		this.mouseInImg = mouseInImg;
		this.mouseIn = mouseIn;
		this.text= text;
		this.action = action;
		box= new Rectangle();
		//box= new Rectangle(x,y,mouseInImg.getWidth(),mouseInImg.getHeight(),null);

		
	}
	
	
	public void update() {
		if(box.contains(MouseInput.x, MouseInput.y)) {
			mouseIn= true;
		}else {
			mouseIn= false;
		}
		
		if(mouseIn==true && MouseInput.MLB) {
			action.doAction();
		}
	}
	
	public void draw(Canvas g) {
		
		if(mouseIn) {
			g.drawBitmap(Assets.botonAzul,box.x, box.y,null);
		}else {
			g.drawBitmap(Assets.botonVerde,box.x, box.y,null);

		}
		
		//Text.drawText(g, text, new Vector2D();
				//box.getX()+box.getWidth()/2,
				//box.getY()+box.getHeight()), true, Color.BLACK, Assets.fontMed);
	}	
	
	
	
}
