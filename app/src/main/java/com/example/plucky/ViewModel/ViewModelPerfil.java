package com.example.plucky.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.AlCuadradoUseCase;
import com.example.plucky.Clases.Clan;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.Repositorio.UsuarioRepositorio;

public class ViewModelPerfil extends ViewModel {

    MutableLiveData<Usuario> usuarioPerfil;
    MutableLiveData<String> correo;
    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<Clan> resultado;




    public void Clan(String nombre){
        usuarioRepositorio.MostrarClan(nombre);
    }



    public ViewModelPerfil() {
        usuarioPerfil=new MutableLiveData<>();
        correo=new MutableLiveData<>();
        resultado=new MutableLiveData<>();

    }

    public void Perfil(){
        usuarioRepositorio.Perfil();
    }

    public void Correo(){
        usuarioRepositorio.Correo();
    }



    public LiveData<Usuario> getResultado(){
        usuarioPerfil= usuarioRepositorio.getUsuarioMutableLiveData();
        return usuarioPerfil;
    }

    public LiveData<String> getCorreo(){
        correo=usuarioRepositorio.getCorreoMutableLiveData();
        return correo;
    }

    public MutableLiveData<Clan> getMutableLiveData() {
        resultado= usuarioRepositorio.getClanMutableLiveData();
        return resultado;
    }



}
