package com.example.plucky.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Clan;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.Repositorio.UsuarioRepositorio;

public class ViewModelPlaceholderFragment extends ViewModel {
    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<String> Clan;


    public ViewModelPlaceholderFragment() {
        Clan = new MutableLiveData<>();
    }

    ////////////////////////////

    public void Verificarclan(){
        usuarioRepositorio.VerificarClan();
    }

    public MutableLiveData<String> getClan() {
        Clan= usuarioRepositorio.getVerfificarClanMutableLiveData();
        return Clan;
    }
}
