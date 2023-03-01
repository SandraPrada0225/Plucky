package com.example.plucky.ViewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Habilidad;
import com.example.plucky.Clases.Utilidades;
import com.example.plucky.Repositorio.UsuarioRepositorio;

import java.util.List;

public class ViewModelHabilidadDetalle extends ViewModel {

    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    Utilidades utilidades= new Utilidades();
    MutableLiveData<Habilidad> habilidad;

    public void Editar(String campo, Habilidad value, Context context){
        usuarioRepositorio.EditarHabilidad(campo,value, context);
    }
    public void consultarHabilidad(int posicion){
        utilidades.ObtenerHabilidades();
        utilidades.consultarHabilidad(posicion);
    }

    public MutableLiveData<Habilidad> getHabilidad() {
        habilidad= utilidades.getHabilidadUsarMutableLiveData();
        return habilidad;
    }
}
