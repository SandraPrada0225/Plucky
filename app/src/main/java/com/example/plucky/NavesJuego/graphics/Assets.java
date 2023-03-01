package com.example.plucky.NavesJuego.graphics;

import android.content.ClipData;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.fonts.Font;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.widget.ImageView;

import com.example.plucky.Juego;
import com.example.plucky.R;



public class Assets {
	
	public static boolean loaded= false;
	public static float count=0;
	public static float MAXCOUNT=460;


	public static MediaPlayer explosionSonido;
	public static MediaPlayer gameOverSonido;
	public static MediaPlayer laserPlayerSonido;
	public static MediaPlayer laserUfoSonido;
	public static MediaPlayer perderSonido;
	public static MediaPlayer sonidoFondo;
	
	public static Bitmap asset;
	public static Bitmap player;
	public static Bitmap enemigo;

	//efectos
	public static Bitmap speed;
	
	//lasers
	public static Bitmap laserazul,laserverde,laserrojo;
	
	//meteoros
	public static Bitmap[] bigs= new Bitmap[4];
	public static Bitmap[] meds= new Bitmap[2];
	public static Bitmap[] smalls= new Bitmap[2];
	public static Bitmap[] tinies= new Bitmap[2];
	
	// explosion 
	public static Bitmap[] explosion= new Bitmap[9];
	
	//UFO
	public static Bitmap ufo;
	
	//Puntaje
	public static Bitmap[] puntaje= new Bitmap[11];
	
	//vida
	public static Bitmap vida;
	
	//fonts
	public static Font font;
	public static Font fontMed;
	

	
	//botones
	public static Bitmap botonAzul, botonVerde;
	
	
	public static void init(Resources resources, Context context) {
	asset= BitmapFactory.decodeResource(resources,R.drawable.nave2);
	player=Bitmap.createScaledBitmap(asset,100,100,false);
	asset= BitmapFactory.decodeResource(resources,R.drawable.jugadorenemigo);
	enemigo=Bitmap.createScaledBitmap(asset,100,100,false);



			asset= BitmapFactory.decodeResource(resources,R.drawable.explosion0);
			explosion[0]=Bitmap.createScaledBitmap(asset,100,100,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.explosion1);
		explosion[1]=Bitmap.createScaledBitmap(asset,100,100,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.explosion2);
		explosion[2]=Bitmap.createScaledBitmap(asset,100,100,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.explosion3);
		explosion[3]=Bitmap.createScaledBitmap(asset,100,100,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.explosion4);
		explosion[4]=Bitmap.createScaledBitmap(asset,100,100,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.explosion5);
		explosion[5]=Bitmap.createScaledBitmap(asset,100,100,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.explosion6);
		explosion[6]=Bitmap.createScaledBitmap(asset,100,100,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.explosion7);
		explosion[7]=Bitmap.createScaledBitmap(asset,100,100,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.explosion8);
		explosion[8]=Bitmap.createScaledBitmap(asset,100,100,false);



		asset= BitmapFactory.decodeResource(resources,R.drawable.ufoblue);
		ufo=Bitmap.createScaledBitmap(asset,100,100,false);

		asset= BitmapFactory.decodeResource(resources,R.drawable.fire08);
		speed=Bitmap.createScaledBitmap(asset,50,50,false);

		asset= BitmapFactory.decodeResource(resources,R.drawable.laserblue16);
		laserazul= Bitmap.createScaledBitmap(asset,50,50,false);

		asset= BitmapFactory.decodeResource(resources,R.drawable.lasergreen10);
		laserverde=  Bitmap.createScaledBitmap(asset,50,50,false);

		asset= BitmapFactory.decodeResource(resources,R.drawable.laserred16);
		laserrojo=  Bitmap.createScaledBitmap(asset,50,50,false);



		asset= BitmapFactory.decodeResource(resources,R.drawable.meteorbrown_big1);
		bigs[0]=  Bitmap.createScaledBitmap(asset,100,100,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.meteorbrown_big2);
		bigs[1]=  Bitmap.createScaledBitmap(asset,100,100,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.meteorbrown_big2);
		bigs[2]=  Bitmap.createScaledBitmap(asset,100,100,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.meteorbrown_big3);
		bigs[3]=  Bitmap.createScaledBitmap(asset,100,100,false);



		meds[0]= Bitmap.createScaledBitmap(asset,100,100,false);
		meds[1]= Bitmap.createScaledBitmap(asset,100,100,false);


		asset= BitmapFactory.decodeResource(resources,R.drawable.meteorbrown_small1);
		smalls[0]= Bitmap.createScaledBitmap(asset,100,100,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.meteorbrown_small2);
		smalls[1]= Bitmap.createScaledBitmap(asset,100,100,false);


		asset= BitmapFactory.decodeResource(resources,R.drawable.meteorbrown_tiny1);
		tinies[0]= Bitmap.createScaledBitmap(asset,100,100,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.meteorbrown_tiny2);
		tinies[1]= Bitmap.createScaledBitmap(asset,100,100,false);



		asset= BitmapFactory.decodeResource(resources,R.drawable.numeral0);
		puntaje[0]= Bitmap.createScaledBitmap(asset,50,50,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.numeral1);
		puntaje[1]= Bitmap.createScaledBitmap(asset,50,50,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.numeral2);
		puntaje[2]= Bitmap.createScaledBitmap(asset,50,50,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.numeral3);
		puntaje[3]= Bitmap.createScaledBitmap(asset,50,50,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.numeral4);
		puntaje[4]= Bitmap.createScaledBitmap(asset,50,50,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.numeral5);
		puntaje[5]= Bitmap.createScaledBitmap(asset,50,50,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.numeral6);
		puntaje[6]= Bitmap.createScaledBitmap(asset,50,50,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.numeral7);
		puntaje[7]= Bitmap.createScaledBitmap(asset,50,50,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.numeral8);
		puntaje[8]= Bitmap.createScaledBitmap(asset,50,50,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.numeral9);
		puntaje[9]= Bitmap.createScaledBitmap(asset,50,50,false);
		asset= BitmapFactory.decodeResource(resources,R.drawable.numeral10);
		puntaje[10]= Bitmap.createScaledBitmap(asset,50,50,false);


		asset= BitmapFactory.decodeResource(resources,R.drawable.playerlife2_green);
		vida=Bitmap.createScaledBitmap(asset,100,100,false);
	
	botonAzul=player;
	botonAzul=player;


	explosionSonido=MediaPlayer.create(context, R.raw.explosioncrunch_000);
	gameOverSonido=MediaPlayer.create(context, R.raw.game_over);
	laserPlayerSonido=MediaPlayer.create(context, R.raw.laserlarge_000);
	laserUfoSonido=MediaPlayer.create(context, R.raw.lasersmall_004);
	perderSonido=MediaPlayer.create(context, R.raw.sfx_lose);
	sonidoFondo=MediaPlayer.create(context, R.raw.cyber_implant_in_my_butt);
	
	
	loaded= true;/*
	}
	

	public static BufferedImage loadImage(String path) {
		count ++;
		return Loader.ImageLoader(path);	
	}
	public static Font loadFont(String path, int size) {
		count ++;
		return Loader.loadfont(path, size);	
	}
	public static Clip loadclip(String path) {
		count ++;
		return Loader.loadSound(path);	
	}
	*/
	}

}
