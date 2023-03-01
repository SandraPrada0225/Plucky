package com.example.plucky.ViewModel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plucky.Clases.Bloque;
import com.example.plucky.Clases.Utilidades;
import com.example.plucky.EscenarioOnline;

import java.util.List;

public class ViewModelJuego extends ViewModel {

    Utilidades utilidades= new Utilidades();
    MutableLiveData<List<Bloque>> resultado;

    public ViewModelJuego() {
        resultado= new MutableLiveData<>();
    }





}
