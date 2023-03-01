package com.example.plucky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.plucky.Clases.Jugador;
import com.example.plucky.GameObjects.AffineTransform;
import com.example.plucky.GameObjects.AnimationHilo;
import com.example.plucky.GameObjects.AnimationView;
import com.example.plucky.GameObjects.GameView;
import com.example.plucky.ViewModel.ViewModelGame;

import java.util.ArrayList;
import java.util.List;

public class Juego extends AppCompatActivity   {

    ViewModelGame viewModelGame;
    List<Jugador> enemigos;
    Jugador jugadorP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        viewModelGame = new ViewModelProvider( Juego.this).get(ViewModelGame.class);
        jugadorP= new Jugador();
        enemigos= new ArrayList<>();


        viewModelGame.consultarJugadores(getIntent().getExtras().getString("room"));
        final Observer<List<Jugador>> observer = new Observer<List<Jugador>>() {
            @Override
            public void onChanged(List<Jugador> jugadors) {
                if (jugadors != null) {
                    for (Jugador jugador:jugadors) {
                        enemigos.add(jugador);
                    }
                }
            }
        };
        viewModelGame.getJugadoresMutableLiveData().observe(Juego.this, observer);
        setContentView(new com.example.plucky.NavesJuego.main1.Window(this,getIntent().getExtras().getString("idJugador"), enemigos));

    }



}