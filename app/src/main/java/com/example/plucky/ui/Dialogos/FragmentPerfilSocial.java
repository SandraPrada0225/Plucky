package com.example.plucky.ui.Dialogos;

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

import com.example.plucky.Clases.Amigos;
import com.example.plucky.Clases.Clan;
import com.example.plucky.Clases.Notificacion;
import com.example.plucky.Clases.Solicitudes;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.R;
import com.example.plucky.ViewModel.ViewModelPerfilSocial;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPerfilSocial#newInstance} factory method to
 * create an instance of this fragment.
 * En este fragment se podrá visualizar los perfiles de los usuarios
 */


public class FragmentPerfilSocial extends AppCompatDialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View vista;
    ImageView atras,imageViewClan;
    TextView Txtnombre,Txtcorreo,Txtuniversidad,Txtsemestre,TxtClanNombre,TxtIntegrantes;
    CircleImageView imagenPerfil;
    CardView cardViewInvitarClan,cardViewClan,cardViewSolicitudamistad;
    ViewModelPerfilSocial viewModelPerfilSocial;
    boolean conteo;
    Usuario user;

    public FragmentPerfilSocial() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPerfilSocial.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPerfilSocial newInstance(String param1, String param2) {
        FragmentPerfilSocial fragment = new FragmentPerfilSocial();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista= inflater.inflate(R.layout.fragment_perfil_social,container,false);
        atras= vista.findViewById(R.id.atrasSocial);
        Txtnombre=vista.findViewById(R.id.nombrePerfilSocial);
        Txtcorreo=vista.findViewById(R.id.correoPerfilSocial);
        Txtuniversidad=vista.findViewById(R.id.universidadPerfilSocial);
        Txtsemestre=vista.findViewById(R.id.semestrePerfilSocial);
        cardViewInvitarClan= vista.findViewById(R.id.invitarClanPerfilSocial);
        cardViewInvitarClan.setVisibility(vista.GONE);
        cardViewClan= vista.findViewById(R.id.cardClanPerfilSocial);
        cardViewClan.setVisibility(vista.GONE);
        imageViewClan= vista.findViewById(R.id.imagenCardClanPerfilSocial);
        TxtClanNombre= vista.findViewById(R.id.nombreCardClanPerfilSocial);
        TxtIntegrantes= vista.findViewById(R.id.integrantesCardClanPefilSocial);
        conteo=false;
        viewModelPerfilSocial = new ViewModelProvider(this).get(ViewModelPerfilSocial.class);
        cardViewSolicitudamistad= vista.findViewById(R.id.enviarSolicitudPerfilSocial);
        cardViewSolicitudamistad.setVisibility(vista.GONE);
        // Inflate the layout for this fragment

        //consultamos si el usuario tiene clan
        Bundle datosRecuperados = getArguments();
        final Observer<Usuario> observered= new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                if(usuario==null){
                    System.out.println("no ha naditaaaa");
                }else{
                    if(!usuario.getClan().equals("0")) {
                        System.out.println("vanesa come mocos " + usuario);
                        conteo = true;
                    }
                    user= usuario;
                }
            }
        };
        viewModelPerfilSocial.getPerfil().observe(getActivity(),observered);
        viewModelPerfilSocial.Perfil();

        if (datosRecuperados == null) {
         System.out.println("no hay naditaaaa");

        }else{
            final Observer<Usuario> observer= new Observer<Usuario>() {
                @Override
                public void onChanged(Usuario usuario) {
                    if(usuario==null){
                        System.out.println("no ha naditaaaa");
                    }else{
                        Txtnombre.setText( usuario.getNombre());
                        Txtcorreo.setText(usuario.getCorreo());
                        Txtsemestre.setText(usuario.getSemestre());
                        Txtuniversidad.setText(usuario.getUniversidad());
                        //consultamos si el usuario seleccionado tiene clan
                        if(!usuario.getClan().equals("0") || usuario.getClan().isEmpty()){
                            System.out.println("pocha tambien come mocos "+usuario);
                            cardViewClan.setVisibility(vista.VISIBLE);
                            final Observer<Clan> observe= new Observer<Clan>() {
                                @Override
                                public void onChanged(Clan clan) {
                                    imageViewClan.setImageResource(clan.getImagen());
                                    TxtClanNombre.setText(clan.getNombre());
                                    TxtIntegrantes.setText(String.valueOf(clan.getIntegrantes().size()));
                                }
                            };
                            viewModelPerfilSocial.getMutableLiveData().observe(getActivity(),observe);
                            viewModelPerfilSocial.Clan(usuario.getClan());
                        }else
                        if(usuario.getClan().equals("0")&&conteo==true){
                            cardViewInvitarClan= vista.findViewById(R.id.invitarClanPerfilSocial);
                            cardViewInvitarClan.setVisibility(vista.VISIBLE);
                            cardViewInvitarClan.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String tiempo=""+System.currentTimeMillis();
                                    final Observer<Usuario> observered= new Observer<Usuario>() {
                                        @Override
                                        public void onChanged(Usuario usuarioperfil) {
                                            if(usuarioperfil==null){
                                                System.out.println("no ha naditaaaa");
                                            }else{
                                                System.out.println("hola :'v"+usuario.getUid());
                                                viewModelPerfilSocial.EnviarNotificacion(new Notificacion(tiempo,usuario.getUid(),usuarioperfil.getClan(),usuarioperfil.getImagen(),usuarioperfil.getNombre(),usuarioperfil.getImagen(),tiempo));
                                            }
                                        }
                                    };
                                    viewModelPerfilSocial.getPerfil().observe(getActivity(),observered);
                                    viewModelPerfilSocial.Perfil();

                                    dismiss();
                                }
                            });
                        }
                        boolean amigos= true;
                        if(usuario.getAmigos().size()==0){
                            cardViewSolicitudamistad.setVisibility(vista.VISIBLE);
                        }else{
                            for(Amigos i:usuario.getAmigos() ){
                                if(i.getAmigo()==user.getUid()){
                                    amigos=false;
                                }
                            }
                            if(conteo==true) {
                                cardViewSolicitudamistad.setVisibility(vista.VISIBLE);
                            }
                        }
                        cardViewSolicitudamistad.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String tiempo=""+System.currentTimeMillis();
                                viewModelPerfilSocial.enviarSolicitud(new Solicitudes(tiempo,user,usuario.getUid(),tiempo));
                                System.out.println(user.getNombre());
                                dismiss();
                            }
                        });
                    }
                }
            };
            viewModelPerfilSocial.getResultado().observe(getActivity(),observer);
            viewModelPerfilSocial.PerfilUsuario(datosRecuperados.getString("correo"));

        }



        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return vista;
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

}