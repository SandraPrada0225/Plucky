package com.example.plucky.ui.online;

import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.plucky.Adaptadores.Adaptador;
import com.example.plucky.Adaptadores.AdaptadorClan;
import com.example.plucky.Clases.Clan;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.R;
import com.example.plucky.ViewModel.ViewModelBuscarClan;
import com.example.plucky.ViewModel.ViewModelPuntajes;
import com.example.plucky.ui.Dialogos.FragmentPerfilSocial;
import com.example.plucky.ui.equipo.DialogHabilidadDetalle;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogBuscarClan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class  DialogBuscarClan extends AppCompatDialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    LinearLayoutManager mLayautManager;
    RecyclerView recyclerViewClan;
    AdaptadorClan adaptador;
    ViewModelBuscarClan ViewModel;
    CardView bCrearNuevoClan;
    CardView cardBuscarClan;
    EditText editTextBuscarClan;
    List<Clan> clanList;
    Clan clanSeleccionado;
    Bundle datosAEnviar = new Bundle();
    ImageView salir;

    public DialogBuscarClan() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuscarClan.
     */
    // TODO: Rename and change types and number of parameters
    public static DialogBuscarClan newInstance(String param1, String param2) {
        DialogBuscarClan fragment = new DialogBuscarClan();
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
        View vista= inflater.inflate(R.layout.fragment_buscar_clan, container, false);
        bCrearNuevoClan= vista.findViewById(R.id.crearNuevoClan);
        ViewModel = new ViewModelProvider(this).get(ViewModelBuscarClan.class);
        mLayautManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true);
        recyclerViewClan = vista.findViewById(R.id.recyclerviewBuscarClan);
        editTextBuscarClan= vista.findViewById(R.id.nombreBuscarClan);
        cardBuscarClan= vista.findViewById(R.id.CardBuscarClan);
        salir=vista.findViewById(R.id.salirBuscarClan);

        cardBuscarClan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Observer<List<Clan>> observer = new Observer<List<Clan>>() {
                    @Override
                    public void onChanged(List<Clan> lista) {
                        if (lista != null) {
                            clanList= new ArrayList<>();
                            System.out.println("la cantidad de clanes"+lista.size());
                            for(int i=0;i<lista.size();i++){
                                System.out.println(lista.get(i).getNombre());
                                if(!lista.get(i).getNombre().equals("")) {
                                    if (lista.get(i).getNombre().substring(0, editTextBuscarClan.getText().toString().length()).equals(editTextBuscarClan.getText().toString())) {
                                        clanList.add(lista.get(i));
                                        System.out.println(lista.get(i).getNombre());
                                    }
                                }
                            }
                            adaptador = new AdaptadorClan(getActivity(), clanList);

                            adaptador.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    clanSeleccionado=clanList.get(recyclerViewClan.getChildAdapterPosition(v));
                                    datosAEnviar.putString("Nombre",clanSeleccionado.getNombre());
                                    DialogClan dialogClan = new DialogClan();
                                    dialogClan.setArguments(datosAEnviar );
                                    dialogClan.show(getActivity().getSupportFragmentManager(), "DialogoPerfil");
                                }
                            });

                            recyclerViewClan.setAdapter(adaptador);
                        }

                    }
                };
                ViewModel.getMutableLiveData().observe(getActivity(), observer);
                mLayautManager.setReverseLayout(true);//Ordena la A -Z
                mLayautManager.setStackFromEnd(true);// Empieza desde arriba sin tener desliz
                recyclerViewClan.setLayoutManager(mLayautManager);
                ViewModel.Clanes();
            }
        });
        bCrearNuevoClan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCrearClan dialogcrearClan = new DialogCrearClan();
                dialogcrearClan.show(getActivity().getSupportFragmentManager(), "DialogoPerfil");
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