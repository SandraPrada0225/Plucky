package com.example.plucky.ViewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Amigos;
import com.example.plucky.Clases.Solicitudes;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.Repositorio.UsuarioRepositorio;

import java.util.List;

public class ViewModelSolicitud extends ViewModel {
    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<List<Solicitudes>> resultado;
    MutableLiveData<Usuario> perfil;


    public ViewModelSolicitud() {
        resultado=new MutableLiveData<>();

    }
    public void  Perfil(){
        usuarioRepositorio.Perfil();
    }


    public void solicitud(){
        usuarioRepositorio.ConsultarSolicitudes();
    }

    public void agregarAmigo(Amigos usuario, Amigos amigos, String uid1, String uid2){
        usuarioRepositorio.agregarAmigos(usuario, amigos, uid1,uid2);
    }

    public void eliminarSolicitud(String eliminar){
        usuarioRepositorio.eliminarSolicitud(eliminar);
    }
    public void Editar(String campo, String value, Context context){
        usuarioRepositorio.Editar(campo,value, context);
    }

    public MutableLiveData<List<Solicitudes>> getSolicitudesMutableLiveData() {
        resultado= usuarioRepositorio.getSolicitudesMutableLiveData();
        return resultado;
    }

    public MutableLiveData<Usuario> getPerfil() {
        perfil= usuarioRepositorio.getUsuarioMutableLiveData();
        return perfil;
    }
}
