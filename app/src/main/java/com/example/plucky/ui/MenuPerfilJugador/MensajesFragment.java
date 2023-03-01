package com.example.plucky.ui.MenuPerfilJugador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plucky.Adaptadores.Adaptador;
import com.example.plucky.Adaptadores.AdaptadorNotificaciones;
import com.example.plucky.Clases.Notificacion;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.R;
import com.example.plucky.ViewModel.ViewModelMensajes;
import com.example.plucky.ViewModel.ViewModelPuntajes;
import com.example.plucky.ui.Dialogos.FragmentPerfilSocial;
import com.example.plucky.ui.online.DialogClan;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MensajesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MensajesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    LinearLayoutManager mLayautManager;
    RecyclerView recyclerViewUsuarios;
    AdaptadorNotificaciones adaptador;
    ViewModelMensajes ViewModel;
    Bundle datosAEnviar = new Bundle();

    public MensajesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MensajesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MensajesFragment newInstance(String param1, String param2) {
        MensajesFragment fragment = new MensajesFragment();
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
        View view= inflater.inflate(R.layout.fragment_mensajes, container, false);

        ViewModel = new ViewModelProvider(this).get(ViewModelMensajes.class);
        mLayautManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true);
        recyclerViewUsuarios = view.findViewById(R.id.recyclerviewNotificaciones);

        final Observer<List<Notificacion>> observer = new Observer<List<Notificacion>>() {
            @Override
            public void onChanged(List<Notificacion> notificaciones) {
                if (notificaciones != null) {
                    System.out.println("hola "+notificaciones.size());
                    adaptador = new AdaptadorNotificaciones(getActivity(), notificaciones);
                    recyclerViewUsuarios.setAdapter(adaptador);

                   adaptador.setOnClickListener(new AdaptadorNotificaciones.OnItemClickListener() {
                       @Override
                       public void onItemClick(int position) {
                           datosAEnviar.putString("Nombre",notificaciones.get(position).getClan());
                           DialogClan dialogClan = new DialogClan();
                           dialogClan.setArguments(datosAEnviar );
                           dialogClan.show(getActivity().getSupportFragmentManager(), "DialogoPerfil");
                       }

                       @Override
                       public void onDeleteClick(int position) {
                           ViewModel.EliminarNotificacion(notificaciones.get(position).getIdNotificacion());
                       }

                       @Override
                       public void onAceptedClick(int position) {
                           ViewModel.Editar("Clan", String.valueOf(notificaciones.get(position).getClan()) ,getContext());
                           ViewModel.EliminarNotificacion(notificaciones.get(position).getIdNotificacion());
                       }
                   });
                }

            }
        };

        ViewModel.getNotificacionesMutableLiveData().observe(getActivity(), observer);
        ViewModel.Notificaciones();
        mLayautManager.setReverseLayout(true);//Ordena la A -Z
        mLayautManager.setStackFromEnd(true);// Empieza desde arriba sin tener desliz
        recyclerViewUsuarios.setHasFixedSize(true);
        recyclerViewUsuarios.setLayoutManager(mLayautManager);


        return view;

    }
}