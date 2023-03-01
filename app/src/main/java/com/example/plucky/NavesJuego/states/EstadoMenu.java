package com.example.plucky.NavesJuego.states;

import android.graphics.Canvas;

import com.example.plucky.NavesJuego.gameObjects.Constantes;
import com.example.plucky.NavesJuego.graphics.Assets;
import com.example.plucky.NavesJuego.input.Action;
import com.example.plucky.NavesJuego.iu.Boton;


import java.util.ArrayList;



public class EstadoMenu extends Estado{
	
	private ArrayList<Boton> botones;
	
	

	public EstadoMenu() {
		botones= new ArrayList<Boton>();
		
		botones.add(new Boton(Assets.botonVerde, Assets.botonAzul,
				Constantes.WIDTH/2-Assets.botonVerde.getWidth()/2,
				Constantes.HEIGHT/2-Assets.botonVerde.getHeight()*2,
				Constantes.PLAY, new Action() {
					
					@Override
					public void doAction() {
						// TODO Auto-generated method stub
					//	Estado.cambiarEstado(new GameState());
						
					}
				}));
		
		

		botones.add(new Boton(Assets.botonVerde, Assets.botonAzul,
				Constantes.WIDTH/2-Assets.botonVerde.getWidth()/2,
				Constantes.HEIGHT/2+Assets.botonVerde.getHeight()*2,
				Constantes.EXIT, new Action() {
					
					@Override
					public void doAction() {
						// TODO Auto-generated method stub
						System.exit(0);
						
					}
				}));
		
		
		
		botones.add(new Boton(Assets.botonVerde, Assets.botonAzul,
				Constantes.WIDTH/2-Assets.botonVerde.getWidth()/2,
				Constantes.HEIGHT/2,
				Constantes.PUNTAJES_ALTOS, new Action() {
					
					@Override
					public void doAction() {
						// TODO Auto-generated method stub
						Estado.cambiarEstado(new ScoreEstado());
						
					}
				}));
		
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		for (Boton boton : botones) {
			boton.update();
		}
	}

	@Override
	public void draw(Canvas g) {
		// TODO Auto-generated method stub
		for (Boton boton : botones) {
			boton.draw(g);
		}
	}
	

}
