package com.example.plucky.ui.Dialogos;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.plucky.Adaptadores.Adaptador;
import com.example.plucky.Adaptadores.AdaptadorClan;
import com.example.plucky.Clases.Clan;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.R;
import com.example.plucky.ViewModel.ViewModelBuscarAmigos;
import com.example.plucky.ViewModel.ViewModelBuscarClan;
import com.example.plucky.ui.online.DialogClan;
import com.example.plucky.ui.online.DialogCrearClan;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBuscarAmigos#newInstance} factory method to
 * create an instance of this fragment.
 * En este fragment se visualizaran todos lo usuarios que no son amigos del jugador
 */
public class FragmentBuscarAmigos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    LinearLayoutManager mLayautManager;
    RecyclerView recyclerViewClan;
    Adaptador adaptador;
    ViewModelBuscarAmigos ViewModel;
    CardView bCrearNuevoClan;
    CardView cardBuscarClan;
    EditText editTextBuscarClan;
    List<Usuario> usuariosList;
    Usuario usuarioSeleccionado;
    Bundle datosAEnviar = new Bundle();
    ImageView salir;

    public FragmentBuscarAmigos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBuscarAmigos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBuscarAmigos newInstance(String param1, String param2) {
        FragmentBuscarAmigos fragment = new FragmentBuscarAmigos();
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
        View view =inflater.inflate(R.layout.fragment_buscar_amigos, container, false);

        ViewModel = new ViewModelProvider(this).get(ViewModelBuscarAmigos.class);
        mLayautManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true);
        recyclerViewClan = view.findViewById(R.id.recyclerviewBuscarUsuario);
        editTextBuscarClan= view.findViewById(R.id.nombreBuscarUsuario);
        cardBuscarClan= view.findViewById(R.id.CardBuscarUsuario);

        cardBuscarClan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Observer<List<Usuario>> observer = new Observer<List<Usuario>>() {
                    @Override
                    public void onChanged(List<Usuario> lista) {
                        if (lista != null) {
                            usuariosList= new ArrayList<>();
                            System.out.println(lista.size());
                            for(int i=0;i<lista.size();i++){
                                if(!lista.get(i).getNombre().equals("")) {
                                    if(lista.get(i).getNombre().substring(0, editTextBuscarClan.getText().toString().length()).equals(editTextBuscarClan.getText().toString())){
                                        usuariosList.add(lista.get(i));
                                        System.out.println(lista.get(i).getNombre());
                                    }
                                }
                            }
                            adaptador = new Adaptador(getActivity(), lista);

                            adaptador.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    usuarioSeleccionado=usuariosList.get(recyclerViewClan.getChildAdapterPosition(v));
                                    datosAEnviar.putString("correo",usuarioSeleccionado.getCorreo());
                                    FragmentPerfilSocial dialogoPerfil = new FragmentPerfilSocial();
                                    dialogoPerfil.setArguments(datosAEnviar);
                                    dialogoPerfil.show(getActivity().getSupportFragmentManager(), "DialogoPerfil");
                                }
                            });
                            recyclerViewClan.setAdapter(adaptador);
                        }

                    }
                };
                ViewModel.getPuntajesMutableLiveData().observe(getActivity(), observer);
                mLayautManager.setReverseLayout(true);//Ordena la A -Z
                mLayautManager.setStackFromEnd(true);// Empieza desde arriba sin tener desliz
                recyclerViewClan.setLayoutManager(mLayautManager);
                ViewModel.buscarAmigos();
            }
        });



        return view;
    }
}