package com.example.plucky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.plucky.Adaptadores.AdaptadorBloques;
import com.example.plucky.Adaptadores.AdaptadorJugador;
import com.example.plucky.Clases.Bloque;
import com.example.plucky.Clases.Jugador;
import com.example.plucky.ViewModel.ViewModelEsperar;
import com.example.plucky.ViewModel.ViewModelRoom;

import java.util.List;

public class ActivityEsperar extends AppCompatActivity {

    ViewModelEsperar viewModelEsperar;
    Button BiniciarPartida;
    LinearLayoutManager mLayautManager;
    RecyclerView recyclerviewJugadores;
    AdaptadorJugador adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esperar);
        viewModelEsperar = new ViewModelProvider(this).get(ViewModelEsperar.class);
        BiniciarPartida= findViewById(R.id.iniciarPartida);
        mLayautManager= new LinearLayoutManager(ActivityEsperar.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerviewJugadores= findViewById(R.id.recyclerviewJugadores);
        recyclerviewJugadores.setLayoutManager(mLayautManager);


        System.out.println("juemadre, funcione  carajo"+getIntent().getExtras().getString("room"));
        viewModelEsperar.consultarJugadores(getIntent().getExtras().getString("room"));
        final Observer<List<Jugador>> observer= new Observer<List<Jugador>>() {
            @Override
            public void onChanged(List<Jugador> list) {
                if(list!=null){
                    System.out.println("holiwis pinguis"+ list.size());
                    adaptador=new AdaptadorJugador(ActivityEsperar.this,list);
                    recyclerviewJugadores.setAdapter(adaptador);
                }else{
                    System.out.println("maldicion pinguis");
                    Toast.makeText(ActivityEsperar.this, "No Hay elementos en la lista", Toast.LENGTH_SHORT).show();
                }
            }

        };
        viewModelEsperar.getJugadoresMutableLiveData().observe(this,observer);
        mLayautManager.setReverseLayout(true);//Ordena la A -Z
        mLayautManager.setStackFromEnd(true);// Empieza desde arriba sin tener desliz
        recyclerviewJugadores.setLayoutManager(mLayautManager);


        BiniciarPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (ActivityEsperar.this,EscenarioOnline.class);
                intent.putExtra("room",getIntent().getExtras().getString("room"));
                startActivity(intent);
            }
        });
    }
}