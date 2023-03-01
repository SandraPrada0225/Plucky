package com.example.plucky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.plucky.ui.equipo.EquipoFragment;
import com.example.plucky.ui.mundo.MundoFragment;


public class MainActivity extends AppCompatActivity {
    Button Blogin,Bresgistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Blogin=findViewById(R.id.login);
        Bresgistro=findViewById(R.id.registro);

        Blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (MainActivity.this,Ingreso.class);
                startActivity(intent);
            }
        });

        Bresgistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (MainActivity.this,Registro.class);
                startActivity(intent);
            }
        });

    }
}