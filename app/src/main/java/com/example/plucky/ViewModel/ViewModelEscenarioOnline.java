package com.example.plucky.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Avatar;
import com.example.plucky.Clases.Bloque;
import com.example.plucky.Clases.Jugador;
import com.example.plucky.Clases.Room;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.Clases.Utilidades;
import com.example.plucky.NavesJuego.gameObjects.Player;
import com.example.plucky.Repositorio.UsuarioRepositorio;

import java.util.List;

public class ViewModelEscenarioOnline extends ViewModel {
    Utilidades utilidades= new Utilidades();
    UsuarioRepositorio usuarioRepositorio= new UsuarioRepositorio();
    MutableLiveData<List<Bloque>> resultado;
    MutableLiveData<List<Bloque>> bloques;
    MutableLiveData<Bloque> Bloque;
    MutableLiveData<Bloque> bloqueNuevo;
    MutableLiveData<String> idUsuario;


    public ViewModelEscenarioOnline() {
        resultado=new MutableLiveData<>();
        Bloque= new MutableLiveData<>();
        bloqueNuevo= new MutableLiveData<>();
    }


    public void Puntajes(){
        utilidades.ObtenerBloques();
    }
    public void ObtenerBloquesMover(){
        utilidades.ObtenerBloquesMover();
    }
    public void ObtenerBloquesEvento(){
        utilidades.ObtenerBloquesEvento();
    }
    public void ObtenerBloquesControl(){
        utilidades.ObtenerBloquesControl();
    }
    public void ObtenerBloquesClases(){
        utilidades.ObtenerBloques();
    }
    public void ObtenerBloquesOperadores(){
        utilidades.ObtenerBloquesOperadores();
    }
    public void ObtenerBloquesPercibir(){
        utilidades.ObtenerBloquesPercibir();
    }
    public void editarJugadorRoom(String id,List<Bloque> bloques, String room){usuarioRepositorio.EditarJugadorRoom(id,bloques,room);}
    public void consultarId(){
      usuarioRepositorio.consultarId();
    }

    public void Consultarbloque(int position){
        utilidades.ConsultarBloque(position);
    }
    public void Consultarbloquenuevo(int position){
        utilidades.ConsultarBloqueNuevo(position);
    }

    public void consultarBloques(String idroom, String idJugador){
        usuarioRepositorio.consultarBloques(idroom,idJugador);
    }



    public MutableLiveData<List<Bloque>> getBloquesMutableLiveData() {
        resultado= utilidades.getBloquesMutableLiveData();
        return resultado;
    }
    public MutableLiveData<Bloque> getBloque() {
        Bloque=utilidades.getBloqueMutableLiveData();
        return Bloque;
    }

    public MutableLiveData<Bloque> getBloqueNuevo() {
        bloqueNuevo= utilidades.getBloqueNuevoMutableLiveData();
        return bloqueNuevo;
    }


    public MutableLiveData<String> getIdUsuario() {
        idUsuario=usuarioRepositorio.getIdUsuarioMutabeLiveData();
        return idUsuario;
    }

}
