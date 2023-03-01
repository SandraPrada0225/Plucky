package com.example.plucky.ViewModel;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.AlCuadradoUseCase;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.Repositorio.StateData;
import com.example.plucky.Repositorio.StateLiveData;
import com.example.plucky.Repositorio.UsuarioRepositorio;
import com.google.firebase.auth.FirebaseUser;

public class ViewModelRegistro extends ViewModel{

    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<FirebaseUser> userMutableLiveData;


    public ViewModelRegistro() {
            userMutableLiveData = new MutableLiveData<>();
    }

    ////////////////////////////
    public void RegistrarJugador(Usuario usuario) {
    usuarioRepositorio.RegistrarJugador(usuario);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        userMutableLiveData=  usuarioRepositorio.getUserMutableLiveData();
        return userMutableLiveData;
    }


}
