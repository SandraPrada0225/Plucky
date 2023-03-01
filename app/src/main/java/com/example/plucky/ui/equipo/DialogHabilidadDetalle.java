package com.example.plucky.ui.equipo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plucky.Adaptadores.AdaptadorHabilidades;
import com.example.plucky.Clases.Habilidad;
import com.example.plucky.R;
import com.example.plucky.ViewModel.ViewModelEquipo;
import com.example.plucky.ViewModel.ViewModelHabilidadDetalle;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogHabilidadDetalle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogHabilidadDetalle extends AppCompatDialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView Txtdescripcion, TxtNombre;
    ImageView imagen, imagenfondo;
    View vista;
    CardView cardViewHabilidad;
    ViewModelHabilidadDetalle viewModelHabilidadDetalle;

    public DialogHabilidadDetalle() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DialogHabilidadDetalle.
     */
    // TODO: Rename and change types and number of parameters
    public static DialogHabilidadDetalle newInstance(String param1, String param2) {
        DialogHabilidadDetalle fragment = new DialogHabilidadDetalle();
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
        vista= inflater.inflate(R.layout.fragment_dialog_habilidad_detalle, container, false);
        Txtdescripcion= vista.findViewById(R.id.descripcionHabilidadDetalle);
        imagen= vista.findViewById(R.id.imagenHabilidadDetalle);
        imagenfondo= vista.findViewById(R.id.imagenfondoHabilidaddetalle);
        cardViewHabilidad= vista.findViewById(R.id.cardHabilidadSeleccionada);
        TxtNombre= vista.findViewById(R.id.idnombreHabilidad);
        viewModelHabilidadDetalle = new ViewModelProvider(this).get(ViewModelHabilidadDetalle.class);

        Bundle datosRecuperados = getArguments();
        if (datosRecuperados == null) {
            System.out.println("no hay naditaaaa");

        }else{
            System.out.println("si hay informamacioooon");
            if(datosRecuperados.getInt("idHabilidad")==1){
                imagenfondo.setImageResource(R.drawable.laser12dialog);
            }else if(datosRecuperados.getInt("idHabilidad")==2){
                imagenfondo.setImageResource(R.drawable.laser2dialog);
            }else if(datosRecuperados.getInt("idHabilidad")==3){
                imagenfondo.setImageResource(R.drawable.laser3dialog);
            }else if(datosRecuperados.getInt("idHabilidad")==4){
                imagenfondo.setImageResource(R.drawable.mascota1dialog);
            }else if(datosRecuperados.getInt("idHabilidad")==5){
                imagenfondo.setImageResource(R.drawable.mascota2dialog);
            }else if(datosRecuperados.getInt("idHabilidad")==6){
                imagenfondo.setImageResource(R.drawable.mascota3dialog);
            }else if(datosRecuperados.getInt("idHabilidad")==7){
                imagenfondo.setImageResource(R.drawable.escudo1dialog);
            }else if(datosRecuperados.getInt("idHabilidad")==8){
                imagenfondo.setImageResource(R.drawable.escudo2dialog);
            }else if(datosRecuperados.getInt("idHabilidad")==9){
                imagenfondo.setImageResource(R.drawable.escudo3dialog);
            }
            Txtdescripcion.setText( datosRecuperados.getString("descripcion"));
            TxtNombre.setText(datosRecuperados.getString("nombre"));
            imagen.setImageResource(Integer.parseInt(datosRecuperados.getString("imagen")));
            System.out.println("hola mundoooo "+datosRecuperados.getInt("idHabilidad"));

        }

        cardViewHabilidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("holissss pinguissss  "+datosRecuperados.getInt("idHabilidad"));
                viewModelHabilidadDetalle.consultarHabilidad(datosRecuperados.getInt("idHabilidad"));

                final Observer<Habilidad> observer = new Observer<Habilidad>() {
                    @Override  
                    public void onChanged(Habilidad habilidad) {
                        if(habilidad!=null){
                           if(habilidad.getTipo()==1){
                               viewModelHabilidadDetalle.Editar("Arma",habilidad,getContext());
                           }else if(habilidad.getTipo()==2){
                               viewModelHabilidadDetalle.Editar("Mascota",habilidad,getContext());
                           }else{
                               viewModelHabilidadDetalle.Editar("Escudo",habilidad,getContext());
                           }
                        }else{
                            Toast.makeText(getContext(), "No Hay elementos en la lista", Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                viewModelHabilidadDetalle.getHabilidad().observe(getActivity(),observer);
                dismiss();



            }
        });

        return vista;
    }



    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }
}