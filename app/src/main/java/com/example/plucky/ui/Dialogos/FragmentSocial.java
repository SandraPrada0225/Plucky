package com.example.plucky.ui.Dialogos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plucky.Adaptadores.AdaptadorAmigos;
import com.example.plucky.Clases.Amigos;
import com.example.plucky.R;
import com.example.plucky.ViewModel.ViewModelPuntajes;

import java.util.List;

//Este fragment se podrán visualizar todos los amigos del usuario
public class FragmentSocial extends Fragment {

    LinearLayoutManager mLayautManager;
    RecyclerView recyclerViewUsuarios;
    AdaptadorAmigos adaptador;
    ViewModelPuntajes ViewModel;
    Amigos usuarioSeleccionado;
    Bundle datosAEnviar = new Bundle();
    public FragmentSocial() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_social, null);
        ViewModel = new ViewModelProvider(this).get(ViewModelPuntajes.class);
        mLayautManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true);
        recyclerViewUsuarios = view.findViewById(R.id.recyclerviewUsuarios);

        final Observer<List<Amigos>> observer = new Observer<List<Amigos>>() {
            @Override
            public void onChanged(List<Amigos> puntajes) {
                if (puntajes != null) {
                    adaptador = new AdaptadorAmigos(getActivity(), puntajes);

                    adaptador.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            usuarioSeleccionado=puntajes.get(recyclerViewUsuarios.getChildAdapterPosition(v));
                            datosAEnviar.putString("correo",usuarioSeleccionado.getAmigo());
                            FragmentPerfilSocial dialogoPerfil = new FragmentPerfilSocial();
                            dialogoPerfil.setArguments(datosAEnviar);
                            dialogoPerfil.show(getActivity().getSupportFragmentManager(), "DialogoPerfil");
                        }
                    });

                    recyclerViewUsuarios.setAdapter(adaptador);
                }

            }
        };
        ViewModel.getPuntajesMutableLiveData().observe(getActivity(), observer);
        ViewModel.Puntajes();
        mLayautManager.setReverseLayout(true);//Ordena la A -Z
        mLayautManager.setStackFromEnd(true);// Empieza desde arriba sin tener desliz
        recyclerViewUsuarios.setHasFixedSize(true);
        recyclerViewUsuarios.setLayoutManager(mLayautManager);

        return view;
    }
}