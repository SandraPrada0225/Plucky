package com.example.plucky.ui.MenuPerfilJugador;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.plucky.R;
import com.example.plucky.ui.Dialogos.FragmentBuscarAmigos;
import com.example.plucky.ui.Dialogos.FragmentPerfilSocial;
import com.example.plucky.ui.Dialogos.FragmentSocial;
import com.example.plucky.ui.Dialogos.FragmentSolicitudesAmistad;
import com.example.plucky.ui.online.ClanFragment;
import com.example.plucky.ui.online.OnlineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuSocialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuSocialFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView buttonAmigos,buttonBuscarAmigos,buttonNotificaciones;
    FragmentTransaction fragmentTransaction;

    public MenuSocialFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuSocialFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuSocialFragment newInstance(String param1, String param2) {
        MenuSocialFragment fragment = new MenuSocialFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_menu_social, container, false);
        buttonAmigos= view.findViewById(R.id.misAmigos);
        buttonBuscarAmigos= view.findViewById(R.id.buscarAmigos);
        buttonNotificaciones= view.findViewById(R.id.Notificaciones);

        FragmentTransaction transaction = getChildFragmentManager()

                .beginTransaction();
        /*
         * When this container fragment is created, we fill it with our first
         * "real" fragment
         */
        transaction.replace(R.id.menusocial_frame, new FragmentSocial());
        transaction.commit();

        buttonAmigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getChildFragmentManager()

                        .beginTransaction();
                /*
                 * When this container fragment is created, we fill it with our first
                 * "real" fragment
                 */
                transaction.replace(R.id.menusocial_frame, new FragmentSocial());
                transaction.commit();
            }
        });
        buttonBuscarAmigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getChildFragmentManager()

                        .beginTransaction();
                /*
                 * When this container fragment is created, we fill it with our first
                 * "real" fragment
                 */
                transaction.replace(R.id.menusocial_frame, new FragmentSolicitudesAmistad());
                transaction.commit();
            }
        });
        buttonNotificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getChildFragmentManager()

                        .beginTransaction();
                /*
                 * When this container fragment is created, we fill it with our first
                 * "real" fragment
                 */
                transaction.replace(R.id.menusocial_frame, new FragmentBuscarAmigos());
                transaction.commit();
            }
        });








        return view;
    }
}