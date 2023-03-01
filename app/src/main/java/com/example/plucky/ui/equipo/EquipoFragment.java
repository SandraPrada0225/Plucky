package com.example.plucky.ui.equipo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plucky.Adaptadores.AdaptadorAvatar;
import com.example.plucky.Adaptadores.AdaptadorHabilidades;
import com.example.plucky.Ajustes;
import com.example.plucky.Clases.Avatar;
import com.example.plucky.Clases.Habilidad;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.R;
import com.example.plucky.Registro;
import com.example.plucky.ViewModel.ViewModelEditarPerfil;
import com.example.plucky.ViewModel.ViewModelEquipo;
import com.example.plucky.ViewModel.ViewModelRegistro;
import com.example.plucky.ui.Dialogos.FragmentEditarPerfil;
import com.example.plucky.ui.Dialogos.FragmentPerfilSocial;
import com.example.plucky.ui.Dialogos.Tab;
import com.example.plucky.ui.tienda.TiendaFragment;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class EquipoFragment extends Fragment {
    LinearLayoutManager mLayautManager;
    RecyclerView recyclerviewHabilidades;
    ViewModelEquipo viewModel;
    AdaptadorHabilidades adaptador;
    View vista;
    Habilidad habilidadSeleccionado;
    ImageView imagenhabilidadEscudo,imagenhabilidadLaser,imagenhabilidadMascota;
    Bundle datosAEnviar = new Bundle();

    public EquipoFragment() {

    }
    public static EquipoFragment newInstance(String param1, String param2) {
        EquipoFragment fragment = new EquipoFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vista=inflater.inflate(R.layout.fragment_equipo, container, false);
        recyclerviewHabilidades= vista.findViewById(R.id.recyclerviewHabilidades);
        imagenhabilidadEscudo= vista.findViewById(R.id.imagenHabilidadEscudo);
        imagenhabilidadLaser= vista.findViewById(R.id.imagenHabilidadLaser);
        imagenhabilidadMascota= vista.findViewById(R.id.imagenHabilidadMascota);

        recyclerviewHabilidades.setHasFixedSize(true);
        mLayautManager= new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerviewHabilidades.setLayoutManager(new GridLayoutManager(getContext(),3));

        viewModel = new ViewModelProvider(this).get(ViewModelEquipo.class);



        viewModel.ConsultarImagenHabilidades();
        final Observer<String[]> observe = new Observer<String[]>() {
            @Override
            public void onChanged(String[] habilidades) {
                if(habilidades!=null){
                    if(!habilidades[0].equals("null")){
                        imagenhabilidadLaser.setImageResource(Integer.parseInt(habilidades[0]));
                    }
                    if(!habilidades[1].equals("null")){
                        imagenhabilidadMascota.setImageResource(Integer.parseInt(habilidades[1]));
                    }
                    if(!habilidades[2].equals("null")){
                        imagenhabilidadEscudo.setImageResource(Integer.parseInt(habilidades[2]));
                    }

                }else{
                    Toast.makeText(getContext(), "No Hay elementos en la lista", Toast.LENGTH_SHORT).show();
                }
            }
        };
        viewModel.getDatosHabilidadesMutableLiveData().observe(getActivity(),observe);



        viewModel.ConsultarHabilidades();
        final Observer<List<Habilidad>> observer = new Observer<List<Habilidad>>() {
            @Override
            public void onChanged(List<Habilidad> list) {
                if(list!=null){
                    adaptador=new AdaptadorHabilidades(getContext(),list);
                    adaptador.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            habilidadSeleccionado=list.get(recyclerviewHabilidades.getChildAdapterPosition(v));
                            System.out.println("esta es la posicion de la habilidadadadaddadadda "+(recyclerviewHabilidades.getChildAdapterPosition(v)+1));
                            datosAEnviar.putInt("idHabilidad", habilidadSeleccionado.getIdHabilidad());
                            datosAEnviar.putString("descripcion",habilidadSeleccionado.getDescripcion());
                            datosAEnviar.putString("imagen", String.valueOf(habilidadSeleccionado.getImagen()));
                            datosAEnviar.putString("nombre",String.valueOf(habilidadSeleccionado.getNombre()));
                            DialogHabilidadDetalle dialogoPerfil = new DialogHabilidadDetalle();
                            dialogoPerfil.setArguments(datosAEnviar );
                            dialogoPerfil.show(getActivity().getSupportFragmentManager(), "DialogoPerfil");
                        }
                    });
                    recyclerviewHabilidades.setAdapter(adaptador);
                }else{
                    Toast.makeText(getContext(), "No Hay elementos en la lista", Toast.LENGTH_SHORT).show();
                }
            }
        };
        viewModel.getHabilidades().observe(getActivity(),observer);

        return vista;
    }


}