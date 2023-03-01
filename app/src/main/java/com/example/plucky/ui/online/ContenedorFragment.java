package com.example.plucky.ui.online;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.plucky.Menu;
import com.example.plucky.R;
import com.example.plucky.Registro;
import com.example.plucky.ViewModel.ViewModelPlaceholderFragment;
import com.example.plucky.ui.tienda.TiendaFragment;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContenedorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContenedorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    FragmentTransaction transaction;
    Fragment onlineFrament,tiendaFragment;
    ViewModelPlaceholderFragment viewModel;
    public ContenedorFragment() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContenedorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContenedorFragment newInstance(String param1, String param2) {
        ContenedorFragment fragment = new ContenedorFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
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
        View view= inflater.inflate(R.layout.fragment_contenedor, container, false);
        viewModel = new ViewModelProvider(this).get(ViewModelPlaceholderFragment.class);
        tiendaFragment=new TiendaFragment();
        onlineFrament= new OnlineFragment();

       final Observer<String> observer= new Observer<String>() {
            @Override
            public void onChanged(String clan) {

                if(clan.equals("0")){
                    FragmentTransaction transaction = getChildFragmentManager()

                            .beginTransaction();
                    /*
                     * When this container fragment is created, we fill it with our first
                     * "real" fragment
                     */
                    transaction.replace(R.id.root_frame, new OnlineFragment());
                    transaction.commitAllowingStateLoss();
                }else{
                    FragmentTransaction transaction = getChildFragmentManager()
                            .beginTransaction();
                    /*
                     * When this container fragment is created, we fill it with our first
                     * "real" fragment
                     */
                    transaction.replace(R.id.root_frame, new ClanFragment());
                    transaction.commitAllowingStateLoss();
                }
            }
        };

        viewModel.getClan().observe(getActivity(),observer);
        viewModel.Verificarclan();
        return view;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);
    }


}