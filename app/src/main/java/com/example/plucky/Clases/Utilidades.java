package com.example.plucky.Clases;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.plucky.Juego;
import com.example.plucky.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Este repositorio almacenara todos los recursos que no cambian dentro del programa como: avatar, stickers, escudos, bloques, habilidades
public class Utilidades {

    public  ArrayList<Sticker> listaSticker=null;
    public  ArrayList<Avatar> listaAvatar=null;
    public  ArrayList<Bloque> listaBloque=null;
    public  ArrayList<Bloque> listaBloques=null;
    public ArrayList<Bloque> lisBloquesIncorporados=null;
    public  ArrayList<EscudoClan> listaEscudo= null;
    public  ArrayList<Habilidad> listaHabilidades= null;
    private MutableLiveData<List<Avatar>> AvatarMutableLiveData;
    private MutableLiveData<List<Sticker>> StickerMutableLiveData;
    private MutableLiveData<List<Habilidad>> HabilidadMutableLiveData;
    private MutableLiveData<List<EscudoClan>> EscudosMutableLiveData;
    private MutableLiveData<List<Bloque>> BloquesMutableLiveData;
    private MutableLiveData<String> tamaño;
    private MutableLiveData<Bloque> BloqueMutableLiveData;
    private MutableLiveData<Bloque> BloqueNuevoMutableLiveData;
    private MutableLiveData<Habilidad> habilidadUsarMutableLiveData;

    public Utilidades() {
        StickerMutableLiveData= new MutableLiveData<>();
        BloqueMutableLiveData= new MutableLiveData<>();
        BloqueNuevoMutableLiveData= new MutableLiveData<>() ;
        habilidadUsarMutableLiveData= new MutableLiveData<>();
        BloquesMutableLiveData= new MutableLiveData<>();
        AvatarMutableLiveData= new MutableLiveData<>();
        HabilidadMutableLiveData= new MutableLiveData<>();
        EscudosMutableLiveData= new MutableLiveData<>();
        tamaño= new MutableLiveData<>();
    }

    public void Obteneravatar(){
        listaAvatar= new ArrayList<Avatar>();
        listaAvatar.add( new Avatar(1, R.drawable.avatr1,"Avatar 1"));
        listaAvatar.add( new Avatar(2, R.drawable.avatar2,"Avatar 2"));
        listaAvatar.add( new Avatar(3, R.drawable.avatar3,"Avatar 3"));
        listaAvatar.add( new Avatar(4, R.drawable.avatar4,"Avatar 4"));
        listaAvatar.add( new Avatar(5, R.drawable.robotica1,"Avatar 5"));
        listaAvatar.add( new Avatar(6, R.drawable.robotica2,"Avatar 6"));  
        listaAvatar.add( new Avatar(7, R.drawable.robotica3,"Avatar 7"));
        listaAvatar.add( new Avatar(8, R.drawable.robotica4,"Avatar 8"));
        listaAvatar.add( new Avatar(9, R.drawable.robotica5,"Avatar 9"));
        listaAvatar.add( new Avatar(10, R.drawable.robotica6,"Avatar 10"));

        AvatarMutableLiveData.postValue(listaAvatar);
    }

    public void ObtenerSticker(){
        listaSticker= new ArrayList<Sticker>();
        listaSticker.add( new Sticker("1","animacion1","sticker.json"));
        listaSticker.add( new Sticker("2", "animacion2","sticker1.json"));
        listaSticker.add( new Sticker("3", "animacion3","sticker2.json"));
        listaSticker.add( new Sticker("4", "animacion4","sticker3.json"));
        listaSticker.add( new Sticker("5", "animacion3","sticker4.json"));
        listaSticker.add( new Sticker("6", "animacion4","sticker5.json"));
        listaSticker.add( new Sticker("7", "animacion3","sticker6.json"));
        listaSticker.add( new Sticker("8", "animacion4","sticker7.json"));

        StickerMutableLiveData.postValue(listaSticker);
    }

    public void ObtenerEscudo(){
        listaEscudo= new ArrayList<EscudoClan>();
        listaEscudo.add( new EscudoClan(1, R.drawable.shield1));
        listaEscudo.add( new EscudoClan(2, R.drawable.shield2));
        listaEscudo.add( new EscudoClan(3, R.drawable.shield3));
        listaEscudo.add( new EscudoClan(4, R.drawable.shield4));
        listaEscudo.add( new EscudoClan(5, R.drawable.shield5));
        EscudosMutableLiveData.postValue(listaEscudo);
    }

    public void ObtenerBloques(){
        listaBloque= new ArrayList<Bloque>();
        listaBloque.add( new Bloque(1, 1," int ",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(2, 1," bollean ",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(3,1," string ",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(4, 1," float ",0,0,0,0,0,0,null));

        listaBloque.add( new Bloque(5, 2," if ",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(6,2,"Switch",0,0,0,0,0,0,null));


        listaBloque.add( new Bloque(7,3," while ",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(8, 3," do while ",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(9, 3," for ",0,0,0,0,0,0,null));

        listaBloque.add( new Bloque(10,4," Array ",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(11, 4," Matriz ",0,0,0,0,0,0,null));

        listaBloque.add( new Bloque(12,5," clases ",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(13, 5,"  objetos ",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(14, 5," metodos ",0,0,0,0,0,0,null));

        BloquesMutableLiveData.postValue(listaBloque);
    }

    public void ObtenerBloquesMover(){
        listaBloque= new ArrayList<Bloque>();
        listaBloque.add( new Bloque(1, 1,"mover 10 pasos",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(2, 1," girar a la derecha ",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(3,1," girar a la izquierda ",0,0,0,0,0,0,null));

        BloquesMutableLiveData.postValue(listaBloque);
    }

    public void ObtenerBloquesPercibir(){
        listaBloque= new ArrayList<Bloque>();
        listaBloque.add( new Bloque(1, 2," int ",0,0,0,0,0,0,null));

        BloquesMutableLiveData.postValue(listaBloque);
    }

    public void ObtenerBloquesControl(){
        listaBloque= new ArrayList<Bloque>();
        listaBloques= new ArrayList<Bloque>();
        lisBloquesIncorporados= new ArrayList<>();
        lisBloquesIncorporados.add(new Bloque(1, 1," int ",11,0,0,0,0,0,null));
        lisBloquesIncorporados.add(new Bloque(1, 1," int ",11,0,0,0,0,0,null));
        lisBloquesIncorporados.add(new Bloque(1, 1," int ",11,0,0,0,0,0,null));
        listaBloques.add(new Bloque(1, 3,"esperar",0,0,0,0,0,0,null));
        listaBloques.add(new Bloque(1, 1," int ",11,0,0,0,0,0,null));
        listaBloque.add( new Bloque(1, 3,"esperar",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(2, 3,"repetir",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(3,3,"por siempre",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(4, 3,"si entonces",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(5, 3,"esperar hasta que",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(6,3,"repetir hasta que",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(7,3," parar",0,0,0,0,0,0,null));

        BloquesMutableLiveData.postValue(listaBloque);
    }

    public void ObtenerBloquesEvento(){
        listaBloque= new ArrayList<Bloque>();
        listaBloque.add( new Bloque(1, 4," Inicio ",0,0,0,0,0,0,null));

        BloquesMutableLiveData.postValue(listaBloque);
    }

    public void ObtenerBloquesOperadores(){
        listaBloque= new ArrayList<Bloque>();
        listaBloque.add( new Bloque(1, 5,"suma",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(2, 5,"resta",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(3,5,"multiplicacion",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(4, 5,"division",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(5, 5," && ",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(6,5,"||",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(7,5,"not",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(8, 5,">",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(9, 5,"<",0,0,0,0,0,0,null));
        listaBloque.add( new Bloque(10,5,"=",0,0,0,0,0,0,null));

        BloquesMutableLiveData.postValue(listaBloque);
    }

    public void ObtenerHabilidades(){
        listaHabilidades= new ArrayList<Habilidad>();

        listaHabilidades.add(new Habilidad(1,1, "Cañon Multiple","Esta poderosa arma te brindara un ataque multiple",R.drawable.lasersin0,R.drawable.laser1));
        listaHabilidades.add(new Habilidad(2,1, "Hunter","Hunter cuenta con un poderoso rayo que atraviesa todo",R.drawable.lasersin1,R.drawable.laser2));
        listaHabilidades.add(new Habilidad(3,1, "Omega","Cuida tu espalda, esta arma dispara una potente bala que rebota",R.drawable.lasersin2,R.drawable.laser3));
        listaHabilidades.add(new Habilidad(4,2, "Tavera","Que lentos se mueven tus enemigos, gracias a la baba de Tavera",R.drawable.aliensin1,R.drawable.alien1));
        listaHabilidades.add(new Habilidad(5,2, "Resplandor","Miralo brillar mientras tus enemgos se enceguecen ",R.drawable.aliensin2,R.drawable.alien2));
        listaHabilidades.add(new Habilidad(6,2, "Boomer","Boomer hace lo imposible posible, teletransportacion a tu favor",R.drawable.aliensin3,R.drawable.alien3));
        listaHabilidades.add(new Habilidad(7,3, "Aspis","Te protege de los ataques leves",R.drawable.escudosin1,R.drawable.escudo1));
        listaHabilidades.add(new Habilidad(8,3,"Scutum","Ni siquiera notaras la mascota del enemigo",R.drawable.escudosin2,R.drawable.escudo2));
        listaHabilidades.add(new Habilidad(9,3, "Broquel","el gran Broquel te convierte en inmortal",R.drawable.escudosin3,R.drawable.escudo3));

        HabilidadMutableLiveData.postValue(listaHabilidades);
    }

    public void consultarHabilidad(int id){
        for (Habilidad habilidad: listaHabilidades) {
            if( habilidad.getIdHabilidad()==id){
                habilidadUsarMutableLiveData.postValue(habilidad);
            }
        }
    }

    public void ConsultarBloque(int position){
        listaBloque.get(position);
        BloqueMutableLiveData.postValue(new Bloque(listaBloque.get(position).getIdbloque(),listaBloque.get(position).getConcepto(),listaBloque.get(position).getNombre(),listaBloque.get(position).getPosition(),listaBloque.get(position).getX(),listaBloque.get(position).getY(),listaBloque.get(position).getWidth(),listaBloque.get(position).getHeight(),listaBloque.get(position).getDistancia(),null));
        }

    public void ConsultarBloqueNuevo(int position){
        System.out.println("Esta es la posicion desde utilidades "+ position);
        listaBloque.get(position);
        BloqueNuevoMutableLiveData.postValue(new Bloque(listaBloque.get(position).getIdbloque(),listaBloque.get(position).getConcepto(),listaBloque.get(position).getNombre(),listaBloque.get(position).getPosition(),listaBloque.get(position).getX(),listaBloque.get(position).getY(),listaBloque.get(position).getWidth(),listaBloque.get(position).getHeight(),listaBloque.get(position).getDistancia(),null));
        System.out.println("fabula ancestral "+listaBloque.get(position).nombre);
    }

    public MutableLiveData<List<Bloque>> getBloquesMutableLiveData() {
        return BloquesMutableLiveData;
    }

    public MutableLiveData<List<Avatar>> getAvatarMutableLiveData() {
        return AvatarMutableLiveData;
    }

    public MutableLiveData<Bloque> getBloqueMutableLiveData() {
        return BloqueMutableLiveData;
    }

    public MutableLiveData<List<Habilidad>> getHabilidadMutableLiveData() {
        return HabilidadMutableLiveData;
    }

    public MutableLiveData<List<EscudoClan>> getEscudosMutableLiveData() {
        return EscudosMutableLiveData;
    }

    public MutableLiveData<List<Sticker>> getStickerMutableLiveData() {
        return StickerMutableLiveData;
    }

    public MutableLiveData<Habilidad> getHabilidadUsarMutableLiveData() {
        return habilidadUsarMutableLiveData;
    }

    public MutableLiveData<Bloque> getBloqueNuevoMutableLiveData() {
        return BloqueNuevoMutableLiveData;
    }

}
