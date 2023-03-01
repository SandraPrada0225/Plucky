package com.example.plucky.ui.Dialogos;
 
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plucky.Clases.Clan;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.Menu;
import com.example.plucky.R;
import com.example.plucky.Registro;
import com.example.plucky.ViewModel.ViewModelPerfil;
import com.example.plucky.ui.online.DialogClan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 * en este fragment se visualizaran todos los datos del perfil del jugador: nombre, correo, universidad, semestre, clan(si tiene),
 */
public class  FragmentPerfil extends Fragment {



    TextView Txtnombre,Txtcorreo,Txtuniversidad,Txtsemestre,Txtclan,TxtIntegrantes;
    CircleImageView imagenPerfil;
    ImageView imagenclan;
    Button Bsalir;
    FloatingActionButton Beditar;
    ViewModelPerfil ViewModel;
    String correo;
    CardView cardViewPerfilClan;
    Bundle datosAEnviar = new Bundle();


    public FragmentPerfil() {
        // Required empty public constructor

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_dialog_perfil,null);
        Txtnombre=view.findViewById(R.id.nombrePerfilUsuario);
        Txtcorreo=view.findViewById(R.id.correoPerfilUsuario);
        Txtuniversidad=view.findViewById(R.id.universidadPerfilUsuario);
        Txtsemestre=view.findViewById(R.id.semestrePerfilUsuario);
        Txtclan=view.findViewById(R.id.nombreCardClanPerfil);
        TxtIntegrantes= view.findViewById(R.id.integrantesCardClanPerfil);
        imagenclan= view.findViewById(R.id.imagenCardClanPerfil);
        Beditar=view.findViewById(R.id.editarPerfilUsuario);
        imagenPerfil=view.findViewById(R.id.imagenPerfil);
        cardViewPerfilClan= view.findViewById(R.id.clanPerfil);
        cardViewPerfilClan.setVisibility(view.GONE);
        ViewModel = new ViewModelProvider(this).get(ViewModelPerfil.class);

        final Observer<Usuario> observer= new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                Txtnombre.setText(usuario.getNombre());
                Txtcorreo.setText(usuario.getCorreo());
                Txtuniversidad.setText(usuario.getUniversidad());
                Txtsemestre.setText(usuario.getSemestre());
                if(!usuario.getImagen().isEmpty()){
                    imagenPerfil.setImageResource(Integer.parseInt(usuario.getImagen()));
                }
                if(!usuario.getClan().equals("0")){
                    final Observer<Clan> observered= new Observer<Clan>() {
                        @Override
                        public void onChanged(Clan clan) {
                            Txtclan.setText(clan.getNombre());
                            imagenclan.setImageResource(clan.getImagen());
                            cardViewPerfilClan.setVisibility(view.VISIBLE);

                            cardViewPerfilClan.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    datosAEnviar.putString("Nombre",clan.getNombre());
                                    DialogClan dialogClan = new DialogClan();
                                    dialogClan.setArguments(datosAEnviar );
                                    dialogClan.show(getActivity().getSupportFragmentManager(), "DialogoPerfil");
                                }
                            });
                        }
                    };
                    ViewModel.getMutableLiveData().observe(getActivity(),observered);
                    ViewModel.Clan(usuario.getClan());


                }
            }
        };
        ViewModel.getResultado().observe(getActivity(),observer);
        ViewModel.Perfil();





        EventosBotones();
        return view;


    }

    private void EventosBotones() {
        Beditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentEditarPerfil dialogoEditarPerfil = new FragmentEditarPerfil();
                dialogoEditarPerfil.show(getActivity().getSupportFragmentManager(), "DialogoEditarPerfil");
            }
        });



    }






}