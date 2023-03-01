package com.example.plucky.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Jugador;
import com.example.plucky.Clases.Room;
import com.example.plucky.Repositorio.UsuarioRepositorio;

import java.util.List;

public class ViewModelGame extends ViewModel {

    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<List<Jugador>> resultado;


    public ViewModelGame() {
        resultado=new MutableLiveData<>();
    }
    public void consultarJugadores( String room){
        usuarioRepositorio.consultarJugadores(room);
    }

    public MutableLiveData<List<Jugador>> getJugadoresMutableLiveData() {
        resultado= usuarioRepositorio.getJugadoresMutableLiveData();
        return resultado;
    }


}
