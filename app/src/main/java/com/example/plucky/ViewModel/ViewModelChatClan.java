package com.example.plucky.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.airbnb.lottie.LottieAnimationView;
import com.example.plucky.Clases.Clan;
import com.example.plucky.Clases.Mensaje;
import com.example.plucky.Clases.Sticker;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.Clases.Utilidades;
import com.example.plucky.Repositorio.UsuarioRepositorio;

import java.util.List;

public class ViewModelChatClan extends ViewModel {
    MutableLiveData<String[]> usuarioPerfil;
    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<String> idClan;
    MutableLiveData<List<Mensaje>> mensajesclan;
    Utilidades utilidades= new Utilidades();
    MutableLiveData<List<Sticker>> listaSticker;
    MutableLiveData<Clan> resultado;


    public void Perfil(){
        usuarioRepositorio.ConsultarUsuarioClan();
    }

    public ViewModelChatClan() {
        usuarioPerfil = new MutableLiveData<>();
        resultado=new MutableLiveData<>();
    }

    ////////////////////////////
    public void InsertarMensajeClan(Mensaje mensaje, String clan) {
        usuarioRepositorio.InsertarMensajesClan(mensaje, clan);
    }

    public void ConsultarStickers(){
        utilidades.ObtenerSticker();
    }

    public void MostrarMensajesClan(String clan){
        usuarioRepositorio.ConsultarMensajesClan(clan);
    }
    public void Clan(String nombre){
        usuarioRepositorio.MostrarClan(nombre);
    }

    public MutableLiveData<Clan> getMutableLiveData() {
        resultado= usuarioRepositorio.getClanMutableLiveData();
        return resultado;
    }

    public MutableLiveData<String[]> getResultado(){
        usuarioPerfil= usuarioRepositorio.getUsuarioClanMutableLiveData();
        return usuarioPerfil;
    }

    public MutableLiveData<String> getIdClan() {
        idClan= usuarioRepositorio.getIdClanMutableLiveData();
        return idClan;
    }

    public MutableLiveData<List<Mensaje>> getMensajesclan() {
        mensajesclan=usuarioRepositorio.getMensajesClanMutableLivedata();
        return mensajesclan;
    }

    public MutableLiveData<List<Sticker>> getListaSticker() {
        listaSticker= utilidades.getStickerMutableLiveData();
        return listaSticker;
    }
}
