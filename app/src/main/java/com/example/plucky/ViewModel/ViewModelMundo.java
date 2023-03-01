package com.example.plucky.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.AlCuadradoUseCase;
import com.example.plucky.Repositorio.UsuarioRepositorio;
import com.google.firebase.auth.FirebaseUser;

public class ViewModelMundo extends ViewModel {

    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<String[]> resultado;



    public ViewModelMundo() {
        resultado=new MutableLiveData<>();
    }


    public void DatosMundo(){
       usuarioRepositorio.DatosMundo();
    }

    public MutableLiveData<String[]> getDatosMundoMutableLiveData() {
        resultado=  usuarioRepositorio.getMundoMutableLiveData();
        return resultado;
    }

}
