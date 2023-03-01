package com.example.plucky.NavesJuego.states;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.fonts.Font;

import com.example.plucky.Clases.Jugador;
import com.example.plucky.NavesJuego.gameObjects.Constantes;
import com.example.plucky.NavesJuego.graphics.Assets;
import com.example.plucky.NavesJuego.graphics.Loader;
import com.example.plucky.NavesJuego.graphics.Text;
import com.example.plucky.NavesJuego.math.Vector2D;

import java.util.List;


public class CargarEstado extends Estado {
	
	private Thread loadThread;
	Context context;
	String jugador;
	List<Jugador>enemigos;
	
	private Font font;
	
	
	

	public CargarEstado(Thread loadThread, Context context, String jugador, List<Jugador> enemigos) {
		this.loadThread = loadThread;
		this.loadThread.start();
		this.context= context;
		this.jugador= jugador;
		this.enemigos= enemigos;

		//font= Loader.loadfont("/fonts/kenvector_future.ttf", 30);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(Assets.loaded) {
			Estado.cambiarEstado(new GameState(jugador, enemigos));
			try {
				loadThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void draw(Canvas g) {
		// TODO Auto-generated method stub
		Paint GP=new Paint();



		
		float porcentaje= Assets.count/Assets.MAXCOUNT;
		Paint pincel1 = new Paint();
	/*g.drawRect(Constantes.WIDTH/2-Constantes.LOADING_BAR_WIDTH/2,
				Constantes.HEIGHT/2-Constantes.LOADING_BAR_HEIGTH/2, (int)(Constantes.LOADING_BAR_WIDTH/2+ 1), Constantes.LOADING_BAR_HEIGTH/2,pincel1);
		
		g.drawRect(Constantes.WIDTH/2-Constantes.LOADING_BAR_WIDTH/2,
				Constantes.HEIGHT/2-Constantes.LOADING_BAR_HEIGTH/2, Constantes.LOADING_BAR_WIDTH/2, Constantes.LOADING_BAR_HEIGTH/2,pincel1);
		
		Text.drawText(g, "Naves Espaciales", new Vector2D(Constantes.WIDTH/2-100,Constantes.HEIGHT/2-50), true, Color.WHITE, font);
		Text.drawText(g, "Cargando...", new Vector2D(Constantes.WIDTH/2-100,Constantes.HEIGHT/2+15), true, Color.WHITE, font);*/
	}

}
