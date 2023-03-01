package com.example.plucky.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Clan;
import com.example.plucky.Repositorio.UsuarioRepositorio;

import java.util.List;

public class ViewModelMostrarClan extends ViewModel {
    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<Clan> resultado;


    public ViewModelMostrarClan() {
        resultado=new MutableLiveData<>();

    }


    public void Clan(String nombre){
        usuarioRepositorio.MostrarClan(nombre);
    }

    public MutableLiveData<Clan> getMutableLiveData() {
        resultado= usuarioRepositorio.getClanMutableLiveData();
        return resultado;
    }
}
