package com.example.plucky.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Repositorio.UsuarioRepositorio;

public class ViewModelClan extends ViewModel {
    MutableLiveData<String[]> usuarioPerfil;
    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();

    public ViewModelClan() {
        usuarioPerfil = new MutableLiveData<>();
    }

    public void Perfil(){
        usuarioRepositorio.ConsultarUsuarioClan();
    }

    public MutableLiveData<String[]> getResultado(){
        usuarioPerfil= usuarioRepositorio.getUsuarioClanMutableLiveData();
        return usuarioPerfil;
    }
}
