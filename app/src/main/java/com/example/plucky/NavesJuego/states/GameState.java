package com.example.plucky.NavesJuego.states;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.airbnb.lottie.L;
import com.example.plucky.ActivityRoom;
import com.example.plucky.Adaptadores.AdaptadorBloques;
import com.example.plucky.Clases.Bloque;
import com.example.plucky.Clases.Jugador;
import com.example.plucky.EscenarioOnline;
import com.example.plucky.Juego;
import com.example.plucky.NavesJuego.IO.JsonParser;
import com.example.plucky.NavesJuego.IO.ScoreDate;
import com.example.plucky.NavesJuego.gameObjects.Constantes;
import com.example.plucky.NavesJuego.gameObjects.Cronometro;
import com.example.plucky.NavesJuego.gameObjects.Enemigo;
import com.example.plucky.NavesJuego.gameObjects.Mensaje;
import com.example.plucky.NavesJuego.gameObjects.Meteoro;
import com.example.plucky.NavesJuego.gameObjects.MovingObject;
import com.example.plucky.NavesJuego.gameObjects.Player;
import com.example.plucky.NavesJuego.gameObjects.Size;
import com.example.plucky.NavesJuego.gameObjects.UFO;
import com.example.plucky.NavesJuego.graphics.Animacion;
import com.example.plucky.NavesJuego.graphics.Assets;
import com.example.plucky.NavesJuego.graphics.Sound;
import com.example.plucky.NavesJuego.main1.Window;
import com.example.plucky.NavesJuego.math.Vector2D;
import com.example.plucky.R;
import com.example.plucky.ViewModel.ViewModelGame;
import com.example.plucky.ViewModel.ViewModelRoom;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;



public class GameState extends Estado {
	
	
	public static final Vector2D PLAYER_START_POSITION= new Vector2D(Constantes.WIDTH/2-Assets.player.getWidth()/2,Constantes.HEIGHT/2-Assets.player.getHeight()/2);
	
	private Player player;
	private ArrayList<MovingObject> movingObjects= new ArrayList<MovingObject>();
	private ArrayList<Animacion> animacions= new ArrayList<Animacion>();
	
	private int score=0;
	
	private int meteoros;
	private int lives=3;
	
	private int waves=1;
	
	private ArrayList<Mensaje> mensajes= new ArrayList<Mensaje>();
	
	private MediaPlayer musicaFondo;
	
	private Cronometro gameOverTimer;
	private boolean gameOver;
	
	private Cronometro ufoSpawaner;
	private List<Bloque> bloques;

	
	
	
	
	public GameState(String jugador, List<Jugador> enemigos) {

		gameOverTimer= new Cronometro();
		gameOver= false;
		

		
		meteoros=1;
		
		startWave();
		musicaFondo= Assets.sonidoFondo;
		musicaFondo.start();
		//musicaFondo= new Sound(Assets.musicafondo);
		//musicaFondo.loop();
		//musicaFondo.changeVolume(-20.0f);
		
		ufoSpawaner= new Cronometro();
		ufoSpawaner.run(Constantes.UFO_SPAWN_RATE);
		int x=600;
		int y=200;
		if(enemigos!=null){
			for (Jugador jugador1:enemigos) {
				if(jugador1.getId().equals(jugador)){
					player= new Player(new  Vector2D(400,300),new Vector2D(),7,Assets.player, this, jugador1.getBloques(),jugador1.getArma(), jugador1.getEscudo(),jugador1.getMascota());
					movingObjects.add(player);
				}else{
					movingObjects.add(new Enemigo(new Vector2D(x,y),new Vector2D(),7,Assets.enemigo, this,jugador1.getBloques(),jugador1.getArma(), jugador1.getEscudo(),jugador1.getMascota()));
					x=x+100;
					y=y+100;
				}

			}

		}



	}
	
	
	public void addAcore(int value, Vector2D position) {
		score+=value;
		mensajes.add(new Mensaje(position,true,"+ "+value+" score",Color.WHITE,false ,Assets.fontMed));
		
		
	}
	public void diveMeteroros(Meteoro meteoro) {
		Size size= meteoro.getSize();
		Bitmap[] textures= size.texture;
		
		Size newsize =null;
		
		switch (size) {
		case BIG:
			newsize= Size.MED;
			break;
		case MED:
			newsize= Size.SMALL;
			break;
		case SMALL:
			newsize= Size.TINY;
			break;
		default:
			return;
		}
		
		for (int i = 0; i < size.quanity; i++) {
			movingObjects.add(new Meteoro(meteoro.getPosition(),
					new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
					Constantes.METEORO_VEL*Math.random()+1, 
					textures[(int)(Math.random()*textures.length)],
					this,
					newsize));
		}
	}
	
	private void startWave() {
		
		mensajes.add(new Mensaje(new Vector2D(Constantes.WIDTH/2, Constantes.HEIGHT/2),
				true,"Oleada "+waves,Color.WHITE,true ,Assets.font));
		
		double x,y;
		
		for (int i = 0; i < meteoros; i++) {
			x=i%2==0? Math.random()*Constantes.WIDTH:0;
			y=i%2==0? 0: Math.random()*Constantes.HEIGHT;
			
			Bitmap texture= Assets.bigs[(int)(Math.random()*Assets.bigs.length) ];
			
			movingObjects.add(new Meteoro(new Vector2D(x,y),
					new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
					Constantes.METEORO_VEL*Math.random()+1, 
					texture, this, Size.BIG));
		}
	meteoros++;
	waves++;
	spawanUfo();
		
	}
	
	
	public void playAnimacionExplosion(Vector2D position) {
		animacions.add(new Animacion(
				Assets.explosion,
				50,
				position.subtract(new Vector2D(Assets.explosion[0].getWidth()/2,Assets.explosion[0].getHeight()/2))));
	}
	
	public void update() {
		
		for (int i = 0; i < movingObjects.size(); i++) {
			MovingObject mo=movingObjects.get(i);
			mo.update();
			if(mo.idDEad()){
				movingObjects.remove(i);
				i--;
			}
			
			
		}
		
		for (int i = 0; i < animacions.size(); i++) {
			Animacion anim= animacions.get(i);
			anim.update();
			if(!anim.isRunning()) {
				animacions.remove(i);
			}
		}
		
		
		
		if(gameOver && ! gameOverTimer.isRunning()){
			musicaFondo.stop();
			System.out.println("acabamos de morir:'v");
			try {
				/*ArrayList<ScoreDate> dataList= JsonParser.readfile();
				dataList.add(new ScoreDate(score));
				JsonParser.writeFile(dataList);*/

			} finally {

			}
			Estado.cambiarEstado(new EstadoMenu());
		}
		
		
			
		if(!ufoSpawaner.isRunning()) {
			ufoSpawaner.run(Constantes.UFO_SPAWN_RATE);
			spawanUfo();
		}
		gameOverTimer.update();
		ufoSpawaner.update();
		
		for (int i = 0; i < movingObjects.size(); i++) 
			if(movingObjects.get(i) instanceof Meteoro) 
				return;
				
			startWave(); 
			
			
		
		
	}
	public void draw(Canvas g) {

		for (int i = 0; i < mensajes.size(); i++) {
			mensajes.get(i).draw(g);
			if(mensajes.get(i).isDEad()) {
				mensajes.remove(i);
			}
		}
		for (int i = 0; i < movingObjects.size(); i++) {
			movingObjects.get(i).draw(g);
		}
		
		for (int i = 0; i < animacions.size(); i++) {
			Animacion anim= animacions.get(i);
			g.drawBitmap(anim.getCurrentFrames(),(int) anim.getPosition().getX(),(int) anim.getPosition().getY(),null);
		}
		drawScore(g);
		drawLives(g);
		
		
	}
	
	private void spawanUfo() {
		double x,y;
		int rand=(int)(Math.random()*2);
		x=rand==0? Math.random()*Constantes.WIDTH:0;
		y=rand==0? 0: Math.random()*Constantes.HEIGHT;
		ArrayList<Vector2D> path= new ArrayList<Vector2D>();
		
		double posX, posY;
		
		posX= Math.random()*Constantes.WIDTH/2;
		posY= Math.random()*Constantes.HEIGHT/2;
		path.add(new Vector2D(posX, posY));
		
		posX= Math.random()*(Constantes.WIDTH/2)+Constantes.WIDTH/2;
		posY= Math.random()*Constantes.HEIGHT/2;
		path.add(new Vector2D(posX, posY));
		
		posX= Math.random()*Constantes.WIDTH/2;
		posY= Math.random()*(Constantes.HEIGHT/2)+Constantes.HEIGHT/2;
		path.add(new Vector2D(posX, posY));
		
		posX= Math.random()*(Constantes.WIDTH/2)+Constantes.WIDTH/2;
		posY= Math.random()*(Constantes.HEIGHT/2)+Constantes.HEIGHT/2;
		path.add(new Vector2D(posX, posY));
		
		movingObjects.add(new UFO(
				new Vector2D(x,y),
				new Vector2D(),
				Constantes.UFO_MAX_VEL,
				Assets.ufo,
				path,
				this
				));
		
		
		
	}
	
	private void drawScore(Canvas g) {
		Vector2D pos=new  Vector2D(600, 25);
		String scoreToString= Integer.toString(score);
		
		for (int i = 0; i < scoreToString.length(); i++) {
			g.drawBitmap(Assets.puntaje[Integer.parseInt(scoreToString.substring(i,i+1))], (int) pos.getX(), (int)pos.getY(), null);
			pos.setX(pos.getX()+50);
		}
	}
	
	private void drawLives(Canvas g) {
		
		if(lives<1)
			return;
		
		Vector2D livePosition=new  Vector2D(25, 25);
		g.drawBitmap(Assets.vida,(int) livePosition.getX(),(int) livePosition.getY(),null);
		g.drawBitmap(Assets.puntaje[10],(int)livePosition.getX()+60, (int)livePosition.getY()+5, null);
		
		String livesToString= Integer.toString(lives);
		Vector2D pos=new  Vector2D(livePosition.getX(), livePosition.getY());
		
		for (int i = 0; i < livesToString.length(); i++) {
			int number= Integer.parseInt(livesToString.substring(i,i+1));
			
			if(number<0) 
				break;

			g.drawBitmap(Assets.puntaje[number],
					(int) pos.getX()+60,
					(int) pos.getY()+5, null);

			pos.setX(pos.getX()+20);
			
		}
		
	}
	
	
	public ArrayList<MovingObject> getMovingObjects() {
		return movingObjects;
	}

	public Player getPlayer() {
		return player;
	}
	
	
	public boolean substractLife() {
		lives--;
		return lives>0;
		
	}


	public ArrayList<Mensaje> getMensajes() {
		return mensajes;
	}
	
	public void gameOver() {
		Mensaje gameOverM= new Mensaje(PLAYER_START_POSITION, true, "GAME OVER", Color.WHITE, true, Assets.font);
		this.mensajes.add(gameOverM);
		gameOverTimer.run(Constantes.GAME_OVER_TIME);
		gameOver= true;
	}
	
	

}
