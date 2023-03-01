package com.example.plucky.ViewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Notificacion;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.Repositorio.UsuarioRepositorio;

import java.util.List;

public class ViewModelMensajes extends ViewModel {
    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<List<Notificacion>> resultado;


    public ViewModelMensajes() {
        resultado=new MutableLiveData<>();

    }


    public void Notificaciones(){
        usuarioRepositorio.ConsultarNotificaciones();
    }

    public void EliminarNotificacion(String eliminar){
        usuarioRepositorio.EliminarNotificacion(eliminar);
    }
    public void Editar(String campo, String value, Context context){
        usuarioRepositorio.Editar(campo,value, context);
    }

    public MutableLiveData<List<Notificacion>> getNotificacionesMutableLiveData() {
        resultado= usuarioRepositorio.getNotificacionMutableLiveData();
        return resultado;
    }
}
