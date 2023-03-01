package com.example.plucky.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Clan;
import com.example.plucky.Clases.Notificacion;
import com.example.plucky.Clases.Solicitudes;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.Repositorio.UsuarioRepositorio;

public class ViewModelPerfilSocial extends ViewModel {
    MutableLiveData<Usuario> usuarioPerfil;
    MutableLiveData<String> clanUsuario;
    MutableLiveData<Usuario> perfil;
    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<Clan> resultado;


    public ViewModelPerfilSocial() {
        usuarioPerfil = new MutableLiveData<>();
        perfil=new MutableLiveData<>();
        resultado=new MutableLiveData<>();
    }

    public void ClanUsuario(){
        usuarioRepositorio.VerificarClan();
    }

    public void  Perfil(){
        usuarioRepositorio.Perfil();
    }

    public void enviarSolicitud(Solicitudes solicitudes){
        usuarioRepositorio.CrearSolicitud(solicitudes);
    }

    public void PerfilUsuario(String usuario){
        usuarioRepositorio.PerfilUsuarios(usuario);
    }

    public void EnviarNotificacion(Notificacion notificacion){
        usuarioRepositorio.CrearNotificacion(notificacion);
    }

    public void Clan(String nombre){
        usuarioRepositorio.MostrarClan(nombre);
    }

    public MutableLiveData<Clan> getMutableLiveData() {
        resultado= usuarioRepositorio.getClanMutableLiveData();
        return resultado;
    }


    public MutableLiveData<String> getResultadoPerfil(){
        clanUsuario= usuarioRepositorio.getVerfificarClanMutableLiveData();
        return clanUsuario;

    }
    public LiveData<Usuario> getResultado(){
        usuarioPerfil= usuarioRepositorio.getUsuariosPerfilMutableLiveData();
        return usuarioPerfil;
    }



    public MutableLiveData<Usuario> getPerfil() {
        perfil= usuarioRepositorio.getUsuarioMutableLiveData();
        return perfil;
    }
}
