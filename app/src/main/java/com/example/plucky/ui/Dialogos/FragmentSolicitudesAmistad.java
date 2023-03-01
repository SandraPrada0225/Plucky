package com.example.plucky.ui.Dialogos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plucky.Adaptadores.AdaptadorSolicitudes;
import com.example.plucky.Clases.Amigos;
import com.example.plucky.Clases.Solicitudes;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.R;
import com.example.plucky.ViewModel.ViewModelSolicitud;
import com.example.plucky.ui.online.DialogClan;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSolicitudesAmistad#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSolicitudesAmistad extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    LinearLayoutManager mLayautManager;
    RecyclerView recyclerViewUsuarios;
    AdaptadorSolicitudes adaptador;
    ViewModelSolicitud ViewModel;
    Usuario amigo;
    Bundle datosAEnviar = new Bundle();

    public FragmentSolicitudesAmistad() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSolicitudesAmistad.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSolicitudesAmistad newInstance(String param1, String param2) {
        FragmentSolicitudesAmistad fragment = new FragmentSolicitudesAmistad();
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
        View view= inflater.inflate(R.layout.fragment_solicitudes_amistad, container, false);


        ViewModel = new ViewModelProvider(this).get(ViewModelSolicitud.class);
        mLayautManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true);
        recyclerViewUsuarios = view.findViewById(R.id.recyclerviewSolicitudUsuarios);

        final Observer<Usuario> observered= new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                if(usuario==null){
                    System.out.println("no ha naditaaaa");
                }else{
                    amigo= usuario;
                }
            }
        };
        ViewModel.getPerfil().observe(getActivity(),observered);
        ViewModel.Perfil();

        final Observer<List<Solicitudes>> observer = new Observer<List<Solicitudes>>() {
            @Override
            public void onChanged(List<Solicitudes> solicitudes) {
                if (solicitudes != null) {
                    System.out.println("hola mujer  "+solicitudes.size());
                    adaptador = new AdaptadorSolicitudes(getActivity(), solicitudes);
                    recyclerViewUsuarios.setAdapter(adaptador);

                    adaptador.setOnClickListener(new AdaptadorSolicitudes.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            DialogClan dialogClan = new DialogClan();
                            dialogClan.setArguments(datosAEnviar);
                            dialogClan.show(getActivity().getSupportFragmentManager(), "DialogoPerfil");
                        }

                        @Override
                        public void onDeleteClick(int position) {
                            ViewModel.eliminarSolicitud(solicitudes.get(position).getIdSolicitud());
                        }

                        @Override
                        public void onAceptedClick(int position) {
                            System.out.println("holassssssasasas");
                            System.out.println("holis pinguis "+position);
                            Usuario usuario= solicitudes.get(position).getUsuarioEnviar();
                            ViewModel.agregarAmigo(new Amigos(usuario.getCorreo(),String.valueOf(System.currentTimeMillis())),
                                    new Amigos(amigo.getCorreo(),String.valueOf(System.currentTimeMillis())), usuario.getUid(),amigo.getUid());
                            ViewModel.eliminarSolicitud(solicitudes.get(position).getIdSolicitud());

                        }
                    });
                }

            }
        };

        ViewModel.getSolicitudesMutableLiveData().observe(getActivity(), observer);
        ViewModel.solicitud();
        mLayautManager.setReverseLayout(true);//Ordena la A -Z
        mLayautManager.setStackFromEnd(true);// Empieza desde arriba sin tener desliz
         recyclerViewUsuarios.setHasFixedSize(true);
        recyclerViewUsuarios.setLayoutManager(mLayautManager);

        return view;
    }
}