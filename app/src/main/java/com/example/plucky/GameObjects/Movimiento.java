package com.example.plucky.GameObjects;

import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;

import com.example.plucky.Clases.Bloque;

import java.util.List;

public class Movimiento  {
    private boolean keys[]= new boolean[256];
    public static boolean arriba, abajo, izquierda, derecha;
    public static List<Bloque> ejecutables;
    public static int movimiento;
    EstadoJuego estadoJuego;

    public Movimiento() {
       arriba=false;
       abajo=false;
       izquierda=false;
       derecha= false;
       estadoJuego= new EstadoJuego();
    }


    public void update(){
      if(ejecutables==null){
            System.out.println("/////////////////////////////////////pinche syso");
        }else {

            for (int i = 0; ejecutables.size() > i; i++) {
                movimiento=ejecutables.get(i).getIdbloque();
                System.out.println("este es el bloque "+ejecutables.get(i).getIdbloque());
                switch (ejecutables.get(i).getIdbloque()) {

                    case 1:
                        arriba = true;
                        izquierda=false;
                        derecha=false;
                        abajo=false;
                        break;
                    case 2:
                        abajo = true;
                        arriba = false;
                        derecha=false;
                        izquierda=false;
                        break;
                    case 3:
                        izquierda = true;
                        abajo = false;
                        arriba = false;
                        derecha=false;
                        break;
                    case 4:
                        derecha = true;
                        izquierda = false;
                        abajo = false;
                        arriba = false;
                        break;
                }
                estadoJuego.update();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("////////////////////////////////////////si estamos ejecutando");

            }
        }

    }

    public void Porfis(List<Bloque> ejecutablesLista){
        ejecutables=ejecutablesLista;
    }

}
