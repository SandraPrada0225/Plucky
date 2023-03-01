package com.example.plucky.ui.deslizar;

import androidx.fragment.app.Fragment;

import com.example.plucky.ViewModel.ViewModelCrearClan;
import com.example.plucky.ui.equipo.EquipoFragment;
import com.example.plucky.ui.mundo.MundoFragment;
import com.example.plucky.ui.online.ContenedorFragment;
import com.example.plucky.ui.online.OnlineFragment;
import com.example.plucky.ui.tienda.TiendaFragment;
import com.example.plucky.ui.torneo.TorneoFragment;

/**
 * Este placeholder contendrá todos los fragments principales del juego: Tienda, Equipo, Mundo, Torneo
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    ViewModelCrearClan viewModel;


    public static Fragment newInstance(int index) {
        Fragment fragment = null;

        switch (index){
            case 1: fragment= new TiendaFragment();break;
            case 2: fragment= new EquipoFragment();break;
            case 3: fragment= new MundoFragment();break;
            case 4: fragment= new ContenedorFragment();break;
            case 5: fragment= new TorneoFragment();break;

        }
        return fragment;
    }






}