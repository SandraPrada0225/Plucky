package com.example.plucky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.plucky.Clases.Amigos;
import com.example.plucky.Clases.Notificacion;
import com.example.plucky.Clases.Solicitudes;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.ViewModel.ViewModelRegistro;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Registro extends AppCompatActivity {
    EditText Txtcorreo,Txtclave,Txtnombre,Txtuniversidad,Txtsemestre;
    Button Bregistrar;
    TextView TxtVfecha;
    ViewModelRegistro ViewModel;
    boolean valor=true;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Bregistrar=findViewById(R.id.registrar);
        Txtcorreo=findViewById(R.id.correo);
        Txtclave=findViewById(R.id.clave);
        Txtnombre=findViewById(R.id.nombre);
        TxtVfecha=findViewById(R.id.fecha);
        Txtuniversidad=findViewById(R.id.universidad);
        Txtsemestre=findViewById(R.id.semestre);

        ViewModel = new ViewModelProvider(this).get(ViewModelRegistro.class);


        Date date= new Date();
        SimpleDateFormat fecha= new SimpleDateFormat("d MMMM yyyy");
        String Sfecha=fecha.format(date);
        TxtVfecha.setText(Sfecha);

        Bregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= Txtcorreo.getText().toString();
                String clave= Txtclave.getText().toString();
                String nombreString= Txtnombre.getText().toString();
                String universidadString= Txtuniversidad.getText().toString();
                String semestreString= Txtsemestre.getText().toString();
                String fechaString =TxtVfecha.getText().toString();

                //validacion
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Txtcorreo.setError("Coreo no valido");
                    Txtcorreo.setFocusable(true);
                }else if (clave.length()<6){
                    Txtclave.setError("la contraseña debe ser mayor a 6");
                    Txtclave.setFocusable(true);
                }else{
                    List<Amigos> amigos;
                    amigos= new ArrayList<Amigos>();
                    List<Notificacion> notificacions;
                    notificacions= new ArrayList<Notificacion>();
                    List<Solicitudes> solicitudes;
                    solicitudes= new ArrayList<Solicitudes>();
                    Usuario usuario=new Usuario("",email, clave, nombreString, universidadString, semestreString, fechaString, 0,0,0,"","0", amigos,notificacions,solicitudes);
                    ViewModel.RegistrarJugador(usuario);


                }
            }
        });
        final Observer<FirebaseUser> observer= new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {

                if(firebaseUser!=null){
                    Intent intent=new Intent (Registro.this, Menu.class);
                    startActivity(intent);
                }
            }
        };

        ViewModel.getUserMutableLiveData().observe(this,observer);
    }






}
