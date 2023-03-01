package com.example.plucky.ViewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Amigos;
import com.example.plucky.Clases.Jugador;
import com.example.plucky.Clases.Room;
import com.example.plucky.Clases.Solicitudes;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.NavesJuego.gameObjects.Player;
import com.example.plucky.Repositorio.UsuarioRepositorio;

import java.util.List;

public class ViewModelRoom  extends ViewModel {
    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<List<Room>> resultado;
    MutableLiveData<String> idUsuario;
    MutableLiveData<String[]> datos;


    public ViewModelRoom() {
        resultado=new MutableLiveData<>();
        datos= new MutableLiveData<>();
    }
    public void  buscarRoom(){
        usuarioRepositorio.BuscarRoom();
    }

    public void agregarJugadorRoom(Jugador player, String room){
        usuarioRepositorio.agregarJugadorRoom(player, room);
    }

    public void consultarId(){
        usuarioRepositorio.consultarId();
    }
    public void consultarDatos(){usuarioRepositorio.consultarHabilidadesid();}

    public void crearRoom(Room room){
        usuarioRepositorio.CrearRoom(room);
    }


    public MutableLiveData<List<Room>> getRoomMutableLiveData() {
        resultado= usuarioRepositorio.getListaRoomMutableLiveData();
        return resultado;
    }

    public MutableLiveData<String> getIdUsuario() {
        idUsuario= usuarioRepositorio.getIdUsuarioMutabeLiveData();
        return idUsuario;
    }

    public MutableLiveData<String[]> getDatos() {
        datos=usuarioRepositorio.getMundoMutableLiveData();
        return datos;
    }
}
