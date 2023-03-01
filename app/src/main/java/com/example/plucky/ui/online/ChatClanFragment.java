package com.example.plucky.ui.online;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.plucky.Adaptadores.Adaptador;
import com.example.plucky.Adaptadores.AdaptadorMensajesClan;
import com.example.plucky.Adaptadores.AdaptadorStickers;
import com.example.plucky.Clases.Clan;
import com.example.plucky.Clases.Mensaje;
import com.example.plucky.Clases.Sticker;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.R;
import com.example.plucky.ViewModel.ViewModelBuscarClan;
import com.example.plucky.ViewModel.ViewModelChatClan;
import com.example.plucky.ui.Dialogos.FragmentPerfilSocial;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatClanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatClanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    EditText editTextMensajeClan;
    ImageView imageViewEnviar, imageViewsticker,imageClan,imageViewSalir;
    RecyclerView recyclerViewMensajesClan;
    LinearLayoutManager mLayautManager;
    RecyclerView recyclerviewMensajeSticker;
    LinearLayoutManager mLayautManager1;
    AdaptadorMensajesClan adaptador;
    AdaptadorStickers adaptadorStickers;
    ViewModelChatClan viewModelChatClan;
    CardView cardViewClan;
    Mensaje mensaje;
    Sticker StickerSeleccionado;
    TextView textViewClan,textViewIntegrantes;
    Bundle datosAEnviar = new Bundle();
    public ChatClanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatClanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatClanFragment newInstance(String param1, String param2) {
        ChatClanFragment fragment = new ChatClanFragment();
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
        View vista= inflater.inflate(R.layout.fragment_chat_clan, container, false);
        viewModelChatClan = new ViewModelProvider(this).get(ViewModelChatClan.class);
        editTextMensajeClan= vista.findViewById(R.id.mensajeClan);
        imageViewEnviar= vista.findViewById(R.id.enviarMensajeclan);
        imageViewsticker=vista.findViewById(R.id.enviarImagenClan);
        recyclerViewMensajesClan= vista.findViewById(R.id.recyclerviewMensajesClan);
//        recyclerviewMensajeSticker= vista.findViewById(R.id.recyclerviewMensajeSticker);
        imageClan=vista.findViewById(R.id.imagenChatClan);
        textViewClan= vista.findViewById(R.id.nombreChatClan);
        textViewIntegrantes= vista.findViewById(R.id.integrantesChatClan);
        imageViewSalir= vista.findViewById(R.id.salirChatClan);
        cardViewClan= vista.findViewById(R.id.cardChatclan);
        mLayautManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true);
        mLayautManager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true);
        viewModelChatClan.Perfil();
        Bundle datosRecuperados = getArguments();
        if(datosRecuperados!=null){
                        final Observer<Clan> observe= new Observer<Clan>() {
                            @Override
                            public void onChanged(Clan clan) {
                                imageClan.setImageResource(clan.getImagen());
                                textViewClan.setText(clan.getNombre());
                                textViewIntegrantes.setText(String.valueOf(clan.getIntegrantes().size()));
                                adaptador = new AdaptadorMensajesClan(getActivity(), clan.getMensajes(),datosRecuperados.getString("Nombre"));

                                adaptador.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                                recyclerViewMensajesClan.setAdapter(adaptador);
                                mLayautManager.setStackFromEnd(true);// Empieza desde arriba sin tener desliz
                                recyclerViewMensajesClan.setLayoutManager(mLayautManager);

                            }
                        };
                        viewModelChatClan.getMutableLiveData().observe(getActivity(),observe);
                        viewModelChatClan.Clan(datosRecuperados.getString("Clan"));

            imageViewEnviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!editTextMensajeClan.getText().toString().trim().equals("")){
                        String idMensaje= ""+System.currentTimeMillis();
                        String stringMensaje= editTextMensajeClan.getText().toString().trim();
                        String stringTipo="Text";
                                    String stringEmisor=datosRecuperados.getString("Nombre");
                                    String stringClan= datosRecuperados.getString("Clan");
                                    mensaje= new Mensaje(idMensaje,stringMensaje,stringEmisor,idMensaje,stringTipo);
                                    viewModelChatClan.InsertarMensajeClan(mensaje,stringClan);

                                    final Observer<List<Mensaje>> observe= new Observer<List<Mensaje>>() {
                                        @Override
                                        public void onChanged(List<Mensaje> mensajes) {
                                            adaptador = new AdaptadorMensajesClan(getActivity(), mensajes,datosRecuperados.getString("Nombre"));

                                            adaptador.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                }
                                            });
                                            recyclerViewMensajesClan.setAdapter(adaptador);
                                            mLayautManager.setStackFromEnd(true);// Empieza desde arriba sin tener desliz
                                            recyclerViewMensajesClan.setLayoutManager(mLayautManager);

                                        }
                                    };
                                    viewModelChatClan.getMensajesclan().observe(getActivity(),observe);
                                    viewModelChatClan.MostrarMensajesClan(stringClan);
                    }
                }
            });

            /*imageViewsticker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Observer<List<Sticker>> observe= new Observer<List<Sticker>>() {
                        @Override
                        public void onChanged(List<Sticker> stickers) {
                            adaptadorStickers = new AdaptadorStickers(getActivity(), stickers);

                            adaptadorStickers.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    StickerSeleccionado=stickers.get(recyclerviewMensajeSticker.getChildAdapterPosition(v));
                                    String idMensaje= ""+System.currentTimeMillis();
                                    String stringMensaje= StickerSeleccionado.getAnimacion();
                                    String stringTipo="Sticker";
                                    viewModelChatClan.Perfil();
                                                mensaje= new Mensaje(idMensaje,stringMensaje,datosRecuperados.getString("Nombre"),idMensaje,stringTipo);
                                                viewModelChatClan.InsertarMensajeClan(mensaje,datosRecuperados.getString("Clan"));


                                                final Observer<List<Mensaje>> observe= new Observer<List<Mensaje>>() {
                                                    @Override
                                                    public void onChanged(List<Mensaje> mensajes) {

                                                        adaptador = new AdaptadorMensajesClan(getActivity(), mensajes,datosRecuperados.getString("Nombre"));

                                                        adaptador.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {

                                                            }
                                                        });
                                                        recyclerViewMensajesClan.setAdapter(adaptador);
                                                        mLayautManager.setStackFromEnd(true);// Empieza desde arriba sin tener desliz
                                                        recyclerViewMensajesClan.setLayoutManager(mLayautManager);

                                                    }
                                                };
                                                viewModelChatClan.getMensajesclan().observe(getActivity(),observe);
                                                viewModelChatClan.MostrarMensajesClan(datosRecuperados.getString("Clan"));
                                }
                            });
                            recyclerviewMensajeSticker.setAdapter(adaptadorStickers);
                            mLayautManager1.setStackFromEnd(false)   ;// Empieza desde arriba sin tener desliz
                            recyclerviewMensajeSticker.setLayoutManager(mLayautManager1);

                        }
                    };
                    viewModelChatClan.getListaSticker().observe(getActivity(),observe);
                    viewModelChatClan.ConsultarStickers();

                }
            });


*/
            cardViewClan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModelChatClan.Perfil();
                                datosAEnviar.putString("Nombre",datosRecuperados.getString("Clan"));
                                DialogClan dialogClan = new DialogClan();
                                dialogClan.setArguments(datosAEnviar );
                                dialogClan.show(getActivity().getSupportFragmentManager(), "DialogoPerfil");
                }
            });
        }

        imageViewSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                trans.replace(R.id.root_frame, new ClanFragment());
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);
                trans.commitAllowingStateLoss();
            }
        });
        return vista;
    }
}