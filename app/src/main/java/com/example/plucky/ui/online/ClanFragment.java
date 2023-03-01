package com.example.plucky.ui.online;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.plucky.R;
import com.example.plucky.ViewModel.ViewModelChatClan;
import com.example.plucky.ViewModel.ViewModelClan;
import com.example.plucky.ui.tienda.TiendaFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    CardView buttonChatClan;
    Bundle datosAEnviar = new Bundle();
    ViewModelClan viewModelClan;

    public ClanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClanFragment newInstance(String param1, String param2) {
        ClanFragment fragment = new ClanFragment();
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
       View view=inflater.inflate(R.layout.fragment_clan, container, false);
       buttonChatClan= view.findViewById(R.id.chatclan);
       viewModelClan=new ViewModelProvider(this).get(ViewModelClan.class);

       buttonChatClan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               viewModelClan.Perfil();
               final Observer<String[]> observe= new Observer<String[]>() {
                   @Override
                   public void onChanged(String[] usuario) {
                       if(usuario[0]==null){
                           System.out.println("algo pasa");
                       }else{
                           datosAEnviar.putString("Nombre",usuario[0]);
                           datosAEnviar.putString("Clan",usuario[1]);
                           FragmentTransaction trans = getActivity().getSupportFragmentManager()
                                   .beginTransaction();
                           ChatClanFragment dialogClan = new ChatClanFragment();
                           dialogClan.setArguments(datosAEnviar );
                           trans.replace(R.id.root_frame, dialogClan);
                           trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                           trans.addToBackStack(null);
                           trans.commitAllowingStateLoss();
                       }
                   }
               };
               viewModelClan.getResultado().observe(getActivity(),observe);

           }
       });

       return  view;
    }
}