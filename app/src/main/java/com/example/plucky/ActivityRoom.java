package com.example.plucky;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.plucky.Adaptadores.AdaptadorAmigos;
import com.example.plucky.Adaptadores.AdaptadorRooms;
import com.example.plucky.Clases.Amigos;
import com.example.plucky.Clases.Jugador;
import com.example.plucky.Clases.Room;
import com.example.plucky.NavesJuego.gameObjects.Constantes;
import com.example.plucky.NavesJuego.gameObjects.Player;
import com.example.plucky.NavesJuego.graphics.Assets;
import com.example.plucky.NavesJuego.math.Vector2D;
import com.example.plucky.NavesJuego.states.GameState;
import com.example.plucky.ViewModel.ViewModelAjustes;
import com.example.plucky.ViewModel.ViewModelRoom;
import com.example.plucky.ui.Dialogos.FragmentPerfilSocial;
import com.example.plucky.ui.online.DialogClan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ActivityRoom extends AppCompatActivity {
    Button BCrearRoom;
    CardView BUnirsePartida;
    EditText idroom;
    ViewModelRoom viewModelRoom;
    Room room;
    String stringRoom;
    Bundle datosAEnviar = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        BCrearRoom=findViewById(R.id.crearRoom);
        BUnirsePartida= findViewById(R.id.unirsePartida);
        idroom=findViewById(R.id.idroom);

        viewModelRoom = new ViewModelProvider(this).get(ViewModelRoom.class);

        BUnirsePartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(idroom.getText().toString()==null)){
                    stringRoom=idroom.getText().toString();
                    viewModelRoom.consultarDatos();
                    final Observer<String[]> observer = new Observer<String[]>() {
                        @Override
                        public void onChanged(String[] datos) {
                            if (datos != null) {
                                viewModelRoom.agregarJugadorRoom(new Jugador(datos[0],String.valueOf(System.currentTimeMillis()),null,datos[1],datos[2],datos[3]),stringRoom);
                                Intent intent=new Intent (ActivityRoom.this,ActivityEsperar.class);
                                intent.putExtra("room",stringRoom);
                                startActivity(intent);
                            }

                        }
                    };
                    viewModelRoom.getDatos().observe(ActivityRoom.this, observer);
                }

            }
        });


        BCrearRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Jugador> jugadores =  new ArrayList<>();
               // jugadores.add(new Jugador(String.valueOf(System.currentTimeMillis()),String.valueOf(System.currentTimeMillis())));
                room=new Room(String.valueOf(System.currentTimeMillis()),jugadores);
                viewModelRoom.crearRoom(room);
                viewModelRoom.consultarDatos();
                final Observer<String[]> observer = new Observer<String[]>() {
                    @Override
                    public void onChanged(String[] datos) {
                        if (datos != null) {
                            viewModelRoom.agregarJugadorRoom(new Jugador(datos[0],String.valueOf(System.currentTimeMillis()),null,datos[1],datos[2],datos[3]),room.getId());
                            Intent intent=new Intent (ActivityRoom.this, ActivityEsperar.class);
                            intent.putExtra("room",room.getId());
                            startActivity(intent);
                        }

                    }
                };
                viewModelRoom.getDatos().observe(ActivityRoom.this, observer);

            }
        });
    }


}