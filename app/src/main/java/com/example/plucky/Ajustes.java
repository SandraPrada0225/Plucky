package com.example.plucky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.plucky.ViewModel.ViewModelAjustes;
import com.example.plucky.ViewModel.ViewModelRegistro;

public class Ajustes extends AppCompatActivity {

    Button Bsalir;
    ViewModelAjustes ViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        ViewModel = new ViewModelProvider(this).get(ViewModelAjustes.class);

        Bsalir=findViewById(R.id.salirJuego);

        Bsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewModel.SalirJuego();
                Intent intent=new Intent (Ajustes.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }
}