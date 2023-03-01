package com.example.plucky.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Clan;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.Repositorio.UsuarioRepositorio;

import java.util.List;

public class ViewModelBuscarClan extends ViewModel {
    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<List<Clan>> resultado;


    public ViewModelBuscarClan() {
        resultado=new MutableLiveData<>();
    }

    public void Clanes(){
        usuarioRepositorio.BuscarClan();
    }

    public MutableLiveData<List<Clan>> getMutableLiveData() {
        resultado= usuarioRepositorio.getListaClanMutableLiveData();
        return resultado;
    }
}
