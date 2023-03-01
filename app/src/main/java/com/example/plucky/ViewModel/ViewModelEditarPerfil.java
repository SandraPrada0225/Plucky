package com.example.plucky.ViewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Avatar;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.Clases.Utilidades;
import com.example.plucky.Repositorio.UsuarioRepositorio;

import java.lang.reflect.Array;
import java.util.List;

public class ViewModelEditarPerfil extends ViewModel {
    Utilidades utilidades= new Utilidades();
    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<List<Avatar>> Avatars;
    MutableLiveData<String[]> datosMostrarEditar;


    public ViewModelEditarPerfil() {
        Avatars=new MutableLiveData<>();
        datosMostrarEditar= new MutableLiveData<>();

    }


    public void ConsultarAvatars(){
        utilidades.Obteneravatar();
    }

    public void ConsultarMostrarDatosEditar(){
        usuarioRepositorio.DatosMostrarEditar();
    }

    public void Editar(String campo, String value, Context context){
        usuarioRepositorio.Editar(campo,value, context);
    }


    public MutableLiveData<List<Avatar>> getAvatars() {
        Avatars= utilidades.getAvatarMutableLiveData();
        return Avatars;
    }

    public MutableLiveData<String[]> getDatosMostrarEditar() {
        datosMostrarEditar=usuarioRepositorio.getMostrarEditarMutableLiveData();
        return datosMostrarEditar;
    }


}
