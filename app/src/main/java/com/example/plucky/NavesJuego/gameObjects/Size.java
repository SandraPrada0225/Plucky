package com.example.plucky.NavesJuego.gameObjects;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.plucky.NavesJuego.graphics.Assets;



public enum Size {

	BIG(2, Assets.meds), MED(2,Assets.smalls), SMALL(2,Assets.tinies), TINY(0,null);
	public int quanity;
	
	public Bitmap[] texture;

	private Size(int quanity, Bitmap[] texture) {
		this.quanity = quanity;
		this.texture = texture;
	}
	
	
}
