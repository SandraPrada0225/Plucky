package com.example.plucky.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Usuario;
import com.example.plucky.Repositorio.UsuarioRepositorio;
import com.google.firebase.auth.FirebaseUser;

public class ViewModelIngreso extends ViewModel {
    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<FirebaseUser> userMutableLiveData;
    MutableLiveData<FirebaseUser> recordarClaveMutableLiveData;


    public ViewModelIngreso() {
        recordarClaveMutableLiveData= new MutableLiveData<>();
        userMutableLiveData = new MutableLiveData<>();
    }

    public void IngresoJugador(String email, String clave) {
        usuarioRepositorio.VerificarJugador( email, clave);
    }
    public void IngresoJugadorGoogle(String token, Context context) {
        usuarioRepositorio.firebaseAuthWithGoogle(token,context);
    }
    public void RecuperarClave(String correo,Context context){
        usuarioRepositorio.IniciaRecuperarClave(correo,context);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        userMutableLiveData=  usuarioRepositorio.getUserMutableLiveData();
        return userMutableLiveData;
    }


}
