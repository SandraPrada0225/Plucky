package com.example.plucky.NavesJuego.states;

import android.graphics.Canvas;


public abstract class Estado {

	private static Estado estadoActual= null;

	public abstract void update();
	public abstract void draw(Canvas g);
	
	public static void cambiarEstado(Estado estado) {
		estadoActual= estado;
	}
	public static Estado getEstadoActual() {
		return estadoActual;
	}
	
	

}
