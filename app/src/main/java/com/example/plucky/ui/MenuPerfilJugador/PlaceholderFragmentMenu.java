package com.example.plucky.ui.MenuPerfilJugador;

import androidx.fragment.app.Fragment;

import com.example.plucky.ui.Dialogos.FragmentPerfil;
import com.example.plucky.ui.Dialogos.FragmentSocial;
import com.example.plucky.ui.online.OnlineFragment;
import com.example.plucky.ui.tienda.TiendaFragment;
import com.example.plucky.ui.torneo.TorneoFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragmentMenu extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";



    public static Fragment newInstance(int index) {
        Fragment fragment = null;

        switch (index){
            case 1: fragment= new FragmentPerfil();break;
            case 2: fragment= new MensajesFragment();break;
            case 3: fragment= new MenuSocialFragment();break;
        }
        return fragment;
    }


}