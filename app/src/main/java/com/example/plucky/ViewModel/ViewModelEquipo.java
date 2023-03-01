package com.example.plucky.ViewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Avatar;
import com.example.plucky.Clases.Habilidad;
import com.example.plucky.Clases.Utilidades;
import com.example.plucky.Repositorio.UsuarioRepositorio;

import java.util.List;

public class ViewModelEquipo extends ViewModel {
    Utilidades utilidades= new Utilidades();
    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<List<Habilidad>> habilidades;
    MutableLiveData<String[]> resultado;


    public ViewModelEquipo() {
        habilidades=new MutableLiveData<>();
    }


    public void ConsultarHabilidades(){
        utilidades.ObtenerHabilidades();
    }

    public void ConsultarImagenHabilidades(){
        usuarioRepositorio.consultarHabilidades();
    }



    public MutableLiveData<List<Habilidad>> getHabilidades() {
        habilidades= utilidades.getHabilidadMutableLiveData();
        return habilidades;
    }

    public MutableLiveData<String[]> getDatosHabilidadesMutableLiveData() {
        resultado=  usuarioRepositorio.getMundoMutableLiveData();
        return resultado;
    }

}
