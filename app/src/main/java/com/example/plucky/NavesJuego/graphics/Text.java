package com.example.plucky.NavesJuego.graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.fonts.Font;

import com.example.plucky.NavesJuego.math.Vector2D;



public class Text {
	public static void drawText(Canvas g, String text, Vector2D pos, boolean center, int color, Font font) {
		Paint pincelTexto = new Paint();
		Typeface tipoFuente;
		tipoFuente = Typeface.create(Typeface.SERIF,Typeface.NORMAL);
		pincelTexto.setTypeface(tipoFuente);
		pincelTexto.setTextSize(40);
		pincelTexto.setTextAlign(Paint.Align.CENTER);

		pincelTexto.setColor(Color.WHITE);
		Vector2D posicion= new Vector2D(pos.getX(), pos.getY());
		g.drawText(text, (int)posicion.getX(), (int)posicion.getY(), pincelTexto);

	}
}
