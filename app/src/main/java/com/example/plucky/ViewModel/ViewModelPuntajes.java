package com.example.plucky.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Amigos;
import com.example.plucky.Repositorio.UsuarioRepositorio;

import java.util.List;

public class ViewModelPuntajes extends ViewModel {

    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<List<Amigos>> resultado;


    public ViewModelPuntajes() {
        resultado=new MutableLiveData<>();

    }

    public void Puntajes(){
        usuarioRepositorio.consultarAmigos();
    }



    public MutableLiveData<List<Amigos>> getPuntajesMutableLiveData() {
        resultado= usuarioRepositorio.getAmigosMutableLiveData();
        return resultado;
    }
}
