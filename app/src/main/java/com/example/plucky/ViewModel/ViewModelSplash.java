package com.example.plucky.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Usuario;
import com.example.plucky.Repositorio.UsuarioRepositorio;
import com.google.firebase.auth.FirebaseUser;

public class ViewModelSplash extends ViewModel {

    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<FirebaseUser> userMutableLiveData;


    public ViewModelSplash() {
        userMutableLiveData = new MutableLiveData<>();

    }

    ////////////////////////////
    public void JugadorEnLinea() {
        usuarioRepositorio.UsuarioJugando();
    }


    public MutableLiveData<FirebaseUser>  getUserMutableLiveData() {
        userMutableLiveData=  usuarioRepositorio.getUserMutableLiveData();
        return userMutableLiveData;
    }


}
