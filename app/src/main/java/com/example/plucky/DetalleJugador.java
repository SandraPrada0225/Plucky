package com.example.plucky;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetalleJugador extends AppCompatActivity {

    ImageView imagenDetalle;
    TextView nombreDetalle,correoDetalle,nivelDetalle,universidadDetalle,semestreDetalle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_jugador);

        imagenDetalle=findViewById(R.id.imagenDetalle);
        nombreDetalle=findViewById(R.id.nombreDetalle);
        correoDetalle=findViewById(R.id.correoDetalle);
        nivelDetalle=findViewById(R.id.nivelDetalle);
        universidadDetalle=findViewById(R.id.universidadDetalle);
        semestreDetalle=findViewById(R.id.semestreDetalle);

        String imagen=getIntent().getStringExtra("Imagen");
        String nombre=getIntent().getStringExtra("Nombre");
        String correo=getIntent().getStringExtra("Correo");
        String nivel=getIntent().getStringExtra("Nivel");
        String universidad=getIntent().getStringExtra("Universidad");
        String semetre=getIntent().getStringExtra("Semestre");

        nombreDetalle.setText(nombre);
        correoDetalle.setText(correo);
        nivelDetalle.setText("Nivel: "+nivel);
        universidadDetalle.setText("Universidad: "+universidad);
        semestreDetalle.setText("Semetre: "+semetre);

        try {
            Picasso.get().load(imagen).into(imagenDetalle);

        }catch (Exception e){

            Picasso.get().load(R.drawable.perfil).into(imagenDetalle);
        }


    }
}