package com.example.plucky.ViewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.EscudoClan;
import com.example.plucky.Clases.Habilidad;
import com.example.plucky.Clases.Utilidades;
import com.example.plucky.Repositorio.UsuarioRepositorio;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class ViewModelEscudos extends ViewModel {

    Utilidades utilidades= new Utilidades();
    MutableLiveData<List<EscudoClan>> escudos;


    public ViewModelEscudos() {
        escudos=new MutableLiveData<>();
    }


    public void ConsultarEscudos(){
        utilidades.ObtenerEscudo();
    }


    public MutableLiveData<List<EscudoClan>> getEscudos() {
        escudos= utilidades.getEscudosMutableLiveData();
        return escudos;
    }
}
