package com.example.plucky.ui.online;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.plucky.Ajustes;
import com.example.plucky.Clases.Clan;
import com.example.plucky.Clases.Mensaje;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.Menu;
import com.example.plucky.R;
import com.example.plucky.ViewModel.ViewModelCrearClan;
import com.example.plucky.ui.tienda.TiendaFragment;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogCrearClan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogCrearClan extends AppCompatDialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public  List<Usuario> listaIntegrantes= null;
    private List<Mensaje> listaMensajes= null;
    EditText editTextNombre, editTextDescripcion;
    RadioButton radiobuttonPublico, radiobuttonPrivado;
    ImageView imageClan;
    ViewModelCrearClan viewModel;
    CardView buttonCrearClan;
    int Imagen=0;
    Bundle datosAEnviar = new Bundle();
    ImageView salir;
    Fragment onlineFrament,tiendaFragment;

    public DialogCrearClan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DialogCrearClan.
     */
    // TODO: Rename and change types and number of parameters
    public static DialogCrearClan newInstance(String param1, String param2) {
        DialogCrearClan fragment = new DialogCrearClan();
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
        View vista = inflater.inflate(R.layout.fragment_dialog_crear_clan, container, false);
        imageClan = vista.findViewById(R.id.imagenClanCrear);
        editTextNombre = vista.findViewById(R.id.nombreCrearClan);
        editTextDescripcion = vista.findViewById(R.id.descripcionHabilidadDetalle);
        radiobuttonPrivado = vista.findViewById(R.id.radioButtonPrivado);
        radiobuttonPublico = vista.findViewById(R.id.radioButtonPublico);
        buttonCrearClan= vista.findViewById(R.id.crearClan);
        salir= vista.findViewById(R.id.salirCrearClan);
        tiendaFragment=new TiendaFragment();

        viewModel = new ViewModelProvider(this).get(ViewModelCrearClan.class);
        viewModel.Perfil();


        imageClan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextNombre.getText().toString()!=null){
                    datosAEnviar.putString("Nombre",editTextNombre.getText().toString());
                }else{
                    datosAEnviar.putString("Nombre","");
                }
             /*
                datosAEnviar.putString("Descripcion",editTextNombre.getText().toString());*/
                DialogEscudos dialogEscudos = new DialogEscudos();
                dialogEscudos.setArguments(datosAEnviar );
                dialogEscudos.show(getActivity().getSupportFragmentManager(), "DialogoPerfil");
                dismiss();
            }
        });

        Bundle datosRecuperados = getArguments();
        if (datosRecuperados == null) {
            System.out.println("no hay naditaaaa");

        } else {
            editTextNombre.setText(datosRecuperados.getString("Nombre"));
          //  editTextDescripcion.setText(datosRecuperados.getString("Descripcion"));
            imageClan.setImageResource(datosRecuperados.getInt("imagen"));
            Imagen=datosRecuperados.getInt("imagen");
        }
        buttonCrearClan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nombre = editTextNombre.getText().toString();
                String Descripcion = editTextNombre.getText().toString();

                String Tipo = "";
                if (radiobuttonPublico.isChecked() == true) {
                    Tipo = "Publico";
                } else if (radiobuttonPrivado.isChecked() == true) {
                    Tipo = "Privado";
                }else{
                    Tipo="Publico";
                }
              if(Imagen==0){
                  Imagen=R.drawable.perfil;
              }
                if(!Nombre.equals("")){


                    final Observer<Usuario> observer= new Observer<Usuario>() {
                        @Override
                        public void onChanged(Usuario usuario) {
                            if(usuario==null){
                                System.out.println("no ha naditaaaa");
                            }else{
                                System.out.println(usuario.getNombre());
                                listaIntegrantes= new ArrayList<Usuario>();
                                listaIntegrantes.add(usuario);
                            }
                        }
                    };
                    viewModel.getResultado().observe(getActivity(),observer);
                    listaMensajes= new ArrayList<Mensaje>();
                    Clan clan = new Clan(Nombre, Imagen, Descripcion, Tipo, listaIntegrantes,listaMensajes);
                    viewModel.RegistrarClan(clan);
                    final Observer<String> observe= new Observer<String>() {
                        @Override
                        public void onChanged(String idClan) {
                            if(idClan==null){
                                System.out.println("no ha naditaaaa");
                            }else{
                                viewModel.EditarClan("Clan",idClan,getContext());
                                FragmentTransaction trans = getActivity().getSupportFragmentManager()
                                        .beginTransaction();
                                trans.replace(R.id.root_frame, new TiendaFragment());
                                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                trans.addToBackStack(null);
                                trans.commit();
                                dismiss();
                            }
                        }
                    };
                    viewModel.getIdClan().observe(getActivity(),observe);
                }
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

            return vista;
        }
    }
