package com.example.plucky.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Usuario;
import com.example.plucky.Repositorio.UsuarioRepositorio;
import com.google.firebase.auth.FirebaseUser;

public class ViewModelAjustes extends ViewModel {

    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();


    public void SalirJuego() {
        usuarioRepositorio.CerrarSesion();
    }

}
