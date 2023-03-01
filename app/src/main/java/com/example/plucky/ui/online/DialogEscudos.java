package com.example.plucky.ui.online;

import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.plucky.Adaptadores.Adaptador;
import com.example.plucky.Adaptadores.AdaptadorEscudos;
import com.example.plucky.Clases.EscudoClan;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.R;
import com.example.plucky.ViewModel.ViewModelEscudos;
import com.example.plucky.ViewModel.ViewModelPuntajes;
import com.example.plucky.ui.Dialogos.FragmentPerfilSocial;

import java.sql.Array;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogEscudos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogEscudos extends AppCompatDialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    LinearLayoutManager mLayautManager;
    RecyclerView recyclerViewEscudos;
    AdaptadorEscudos adaptador;
    ViewModelEscudos viewModel;
    EscudoClan escudoSeleccion;
    Bundle datosAEnviar = new Bundle();
    ImageView buttonSalir;



    public DialogEscudos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DialogEscudos.
     */
    // TODO: Rename and change types and number of parameters
    public static DialogEscudos newInstance(String param1, String param2) {
        DialogEscudos fragment = new DialogEscudos();
        Bundle args = new Bundle();
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
        View vista= inflater.inflate(R.layout.fragment_dialog_escudos, container, false);
        viewModel = new ViewModelProvider(this).get(ViewModelEscudos.class);
        mLayautManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true);
        recyclerViewEscudos = vista.findViewById(R.id.recyclerviewEscudos);
        buttonSalir= vista.findViewById(R.id.salirEmblema);
        viewModel.ConsultarEscudos();
        final Observer<List<EscudoClan>> observer = new Observer<List<EscudoClan>>() {
            @Override
            public void onChanged(List<EscudoClan> escudos) {
                if (escudos != null) {
                    System.out.println("no señor, no está vacio "+escudos.get(1).getEscudoclan());
                    adaptador = new AdaptadorEscudos(getContext(), escudos);
                    adaptador.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle datosRecuperados = getArguments();
                            escudoSeleccion=escudos.get(recyclerViewEscudos.getChildAdapterPosition(v));
                            datosAEnviar.putInt("id", escudoSeleccion.getIdEscudoclan());
                            datosAEnviar.putInt("imagen",escudoSeleccion.getEscudoclan());
                            datosAEnviar.putString("Nombre",datosRecuperados.getString("Nombre"));
                            DialogCrearClan dialogoCrearClan= new DialogCrearClan();
                            dialogoCrearClan.setArguments(datosAEnviar);
                            dialogoCrearClan.show(getActivity().getSupportFragmentManager(), "DialogCrearClan");
                            dismiss();
                        }
                    });

                    recyclerViewEscudos.setAdapter(adaptador);
                }else{
                    System.out.println("vacioooooooo");
                }

            }
        };
        buttonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        viewModel.getEscudos().observe(this,observer);
        mLayautManager.setReverseLayout(true);//Ordena la A -Z
        mLayautManager.setStackFromEnd(true);// Empieza desde arriba sin tener desliz
        recyclerViewEscudos.setLayoutManager(new GridLayoutManager(getContext(),3));
        return vista;
    }
}