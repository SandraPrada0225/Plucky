package com.example.plucky.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Usuario;
import com.example.plucky.Repositorio.UsuarioRepositorio;

import java.util.List;

public class ViewModelBuscarAmigos extends ViewModel {

    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<List<Usuario>> resultado;


    public ViewModelBuscarAmigos() {
        resultado=new MutableLiveData<>();

    }


    public void buscarAmigos(){
        usuarioRepositorio.Puntajes();
    }

    public MutableLiveData<List<Usuario>> getPuntajesMutableLiveData() {
        resultado= usuarioRepositorio.getPuntajesMutableLiveData();
        return resultado;
    }
}
