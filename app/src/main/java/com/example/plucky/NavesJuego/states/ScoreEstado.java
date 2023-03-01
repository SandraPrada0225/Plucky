package com.example.plucky.NavesJuego.states;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.plucky.NavesJuego.IO.JsonParser;
import com.example.plucky.NavesJuego.IO.ScoreDate;
import com.example.plucky.NavesJuego.gameObjects.Constantes;
import com.example.plucky.NavesJuego.graphics.Assets;
import com.example.plucky.NavesJuego.graphics.Text;
import com.example.plucky.NavesJuego.input.Action;
import com.example.plucky.NavesJuego.iu.Boton;
import com.example.plucky.NavesJuego.math.Vector2D;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


public class ScoreEstado extends Estado{
	
	
	private Boton returnBoton;
	
	private PriorityQueue<ScoreDate> scores;
	
	private Comparator<ScoreDate> scoreComparator;
	
	private ScoreDate[] datesArray;
	
	
	
	

	public ScoreEstado() {
	returnBoton= new Boton(Assets.botonAzul,Assets.botonVerde, Assets.botonAzul.getHeight(), Constantes.HEIGHT-Assets.botonAzul.getHeight()*2, Constantes.RETURN, new Action() {
		
		@Override
		public void doAction() {
			// TODO Auto-generated method stub
			Estado.cambiarEstado(new EstadoMenu());
		}
	});
	
	scoreComparator= new Comparator<ScoreDate>() {
		
		@Override
		public int compare(ScoreDate o1, ScoreDate o2) {
			// TODO Auto-generated method stub
			return o1.getScore()<o2.getScore()?-1:o1.getScore()>o2.getScore()?1:0;
		}
	};
	
	scores= new PriorityQueue<ScoreDate>(10, scoreComparator);
	
	try {
	//	ArrayList<ScoreDate> dataList = JsonParser.readfile();
	/*	for (ScoreDate scoreDate : dataList) {
			scores.add(scoreDate);
		}*/
		while (scores.size()>10) {
			scores.poll();
			
		}
	}finally {

	}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		returnBoton.update();
	}

	@Override
	public void draw(Canvas g) {
		// TODO Auto-generated method stub
		returnBoton.draw(g);
		datesArray= scores.toArray(new ScoreDate[scores.size()]);
		
		Arrays.sort(datesArray,scoreComparator);
		
		
		Vector2D scorePos = new Vector2D(Constantes.WIDTH/2-300,100);
		Vector2D datePos = new Vector2D(Constantes.WIDTH/2+100,100);
		
		Text.drawText(g, Constantes.PUNTAJE, scorePos, true, Color.BLUE, Assets.font);
		Text.drawText(g, Constantes.FECHA, datePos, true, Color.BLUE, Assets.font);
		
		scorePos.setY(scorePos.getY()+40);
		datePos.setY(datePos.getY()+40);
		
		for (int i = datesArray.length-1; i >-1; i--) {
			ScoreDate d = datesArray[i];
			Text.drawText(g, Integer.toString(d.getScore() ), scorePos, true, Color.WHITE, Assets.fontMed);
			Text.drawText(g, d.getDate(), datePos, true, Color.WHITE, Assets.fontMed);
			
			scorePos.setY(scorePos.getY()+40);
			datePos.setY(datePos.getY()+40);
			
		}
		
	}

}
