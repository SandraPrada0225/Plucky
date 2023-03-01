package com.example.plucky.Clases;

//Esta clase hace referencia al avatar o foto de perfil del jugador
public class Avatar {

     int id;
    private int avatarId;
    private String nombre;


    public Avatar(int id, int avatarId, String nomrbre) {
        this.id = id;
        this.avatarId = avatarId;
        this.nombre = nomrbre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nomrbre) {
        this.nombre = nombre;
    }
}
