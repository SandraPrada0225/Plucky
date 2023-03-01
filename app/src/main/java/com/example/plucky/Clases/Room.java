package com.example.plucky.Clases;

import com.example.plucky.NavesJuego.gameObjects.Player;

import java.util.List;

public class Room {

  String id;
  List<Jugador> jugadores;

    public Room(String id, List<Jugador> jugadores) {
        this.id = id;
        this.jugadores = jugadores;
    }
    public Room(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
