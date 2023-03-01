package com.example.plucky.ui.online;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plucky.Adaptadores.Adaptador;
import com.example.plucky.Adaptadores.AdaptadorClan;
import com.example.plucky.Ajustes;
import com.example.plucky.Clases.Clan;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.R;
import com.example.plucky.ViewModel.ViewModelBuscarClan;
import com.example.plucky.ViewModel.ViewModelMostrarClan;
import com.example.plucky.ui.Dialogos.FragmentPerfilSocial;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogClan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogClan extends AppCompatDialogFragment {


    TextView Txtnombre,TxtDescripcion,TxtEstado;
    ImageView imagenClan;
    ViewModelMostrarClan viewModelMostrarClan;
    RecyclerView recyclerviewIntegrantesClan;
    LinearLayoutManager mLayautManager;
    Adaptador adaptador;
    Usuario usuarioSeleccionado;
    Bundle datosAEnviar = new Bundle();
    ImageView salir;

    public DialogClan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DialogClan.
     */
    // TODO: Rename and change types and number of parameters
    public static DialogClan newInstance(String param1, String param2) {
        DialogClan fragment = new DialogClan();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_dialog_clan, container, false);
        imagenClan= vista.findViewById(R.id.imagenClan);
        Txtnombre= vista.findViewById(R.id.nombreClan);
        TxtDescripcion= vista.findViewById(R.id.descripcionClan);
        TxtEstado= vista.findViewById(R.id.estadoClan);
        viewModelMostrarClan = new ViewModelProvider(this).get(ViewModelMostrarClan.class);
        recyclerviewIntegrantesClan = vista.findViewById(R.id.recyclerviewIntegrantesClan);
        mLayautManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true);
        salir= vista.findViewById(R.id.salirMostrarClan);
        Bundle datosRecuperados = getArguments();
        if (datosRecuperados != null) {
            System.out.println("hola mundoooo "+datosRecuperados.getString("Nombre"));
            viewModelMostrarClan.Clan(datosRecuperados.getString("Nombre"));
            final Observer<Clan> observe= new Observer<Clan>() {
                @Override
                public void onChanged(Clan clan) {
                    Txtnombre.setText(clan.getNombre());
                    TxtDescripcion.setText(clan.getDescripcion());
                    TxtEstado.setText(clan.getEstado());
                    imagenClan.setImageResource(clan.getImagen());
                    System.out.println("que si hay, vea "+clan.getIntegrantes().size());
                    adaptador = new Adaptador(getActivity(), clan.getIntegrantes());

                    adaptador.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            usuarioSeleccionado=clan.getIntegrantes().get(recyclerviewIntegrantesClan.getChildAdapterPosition(v));
                            datosAEnviar.putString("nombre",usuarioSeleccionado.getNombre());
                            datosAEnviar.putString("correo",usuarioSeleccionado.getCorreo());
                            datosAEnviar.putString("semestre",usuarioSeleccionado.getSemestre());
                            datosAEnviar.putString("universidad",usuarioSeleccionado.getUniversidad());
                            datosAEnviar.putString("imagen",usuarioSeleccionado.getImagen());
                            FragmentPerfilSocial dialogoPerfil = new FragmentPerfilSocial();
                            dialogoPerfil.setArguments(datosAEnviar );
                            dialogoPerfil.show(getActivity().getSupportFragmentManager(), "DialogoPerfil");
                        }
                    });
                    recyclerviewIntegrantesClan.setAdapter(adaptador);
                    mLayautManager.setReverseLayout(true);//Ordena la A -Z
                    mLayautManager.setStackFromEnd(true);// Empieza desde arriba sin tener desliz
                    recyclerviewIntegrantesClan.setLayoutManager(mLayautManager);

                    }
                };
            viewModelMostrarClan.getMutableLiveData().observe(getActivity(),observe);
            }

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return vista;

    }
}