package com.example.plucky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.plucky.ViewModel.ViewModelSplash;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends AppCompatActivity {

    ViewModelSplash ViewModel;
    String clanUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga);
        clanUser="";

        ViewModel = new ViewModelProvider(this).get(ViewModelSplash.class);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewModel.JugadorEnLinea();


            }
        },7000);
        ViewModel.getUserMutableLiveData().observe(this,observer);
    }

    final Observer<FirebaseUser> observer= new Observer<FirebaseUser>() {
        @Override
        public void onChanged(FirebaseUser firebaseUser) {

            if(firebaseUser==null){
              //  System.out.println("usuario"+firebaseUser.getEmail());
                Toast.makeText(Splash.this, "Usuario fuera de linea", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent (Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else{
               System.out.println("/////////////usuario"+firebaseUser.getEmail());
               Cargar();

            }
        }
    };

    public void Cargar(){
        Toast.makeText(Splash.this, "Usuario en linea", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(Splash.this,Menu.class);
        startActivity(intent);
    }

}