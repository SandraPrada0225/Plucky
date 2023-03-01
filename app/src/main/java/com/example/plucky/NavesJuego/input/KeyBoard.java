package com.example.plucky.NavesJuego.input;

import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;

import com.example.plucky.Clases.Bloque;

import java.util.List;


public class KeyBoard {

	public static boolean arriba, abajo, izquierda, derecha;
	public static List<Bloque> ejecutables;
	public static int movimiento;
	public int i;

	public KeyBoard() {
		arriba=false;
		abajo=false;
		izquierda=false;
		derecha= false;
		 i=0;
	}
	
	public void update() {

		
	}
	public void Porfis(List<Bloque> ejecutablesLista){
		ejecutables=ejecutablesLista;
	}





}
