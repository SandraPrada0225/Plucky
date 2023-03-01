package com.example.plucky.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Clan;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.Repositorio.UsuarioRepositorio;
import com.google.firebase.auth.FirebaseUser;

public class ViewModelCrearClan extends ViewModel {
    MutableLiveData<Usuario> usuarioPerfil;
    MutableLiveData<String> correo;
    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<String> idClan;


    public void Perfil(){
        usuarioRepositorio.Perfil();
    }

    public ViewModelCrearClan() {
        usuarioPerfil = new MutableLiveData<>();
    }

    ////////////////////////////
    public void RegistrarClan(Clan clan) {
        usuarioRepositorio.CrearClan(clan);
    }

    public void EditarClan(String campo, String value, Context context){
        usuarioRepositorio.Editar(campo,value, context);
    }
    public void Correo(){
        usuarioRepositorio.Correo();
    }

    public LiveData<String> getCorreo(){
        correo=usuarioRepositorio.getCorreoMutableLiveData();
        return correo;
    }

    public LiveData<Usuario> getResultado(){
        usuarioPerfil= usuarioRepositorio.getUsuarioMutableLiveData();
        return usuarioPerfil;
    }

    public MutableLiveData<String> getIdClan() {
        idClan= usuarioRepositorio.getIdClanMutableLiveData();
        return idClan;
    }
}
