package com.example.plucky.ui.mundo;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.plucky.ActivityRoom;
import com.example.plucky.Ajustes;
import com.example.plucky.EscenarioOnline;
import com.example.plucky.Juego;
import com.example.plucky.R;
import com.example.plucky.ViewModel.ViewModelMundo;
import com.example.plucky.ui.Dialogos.FragmentSocial;
import com.example.plucky.ui.Dialogos.Tab;

import java.util.jar.JarEntry;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class MundoFragment extends Fragment {


    CardView cardNivel, cardMonedas, cardGemas, cardJugador, cardJugadores, cardAjustes,Bjugar;
    TextView Txtjugadormundo, TxtmonedasMundo, Txtgemasmundo, Txtnivelmundo;
    ViewModelMundo ViewModel;
    View vista;
    ImageView imagenPerfilMundo;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_mundo, container, false);

        cardNivel = vista.findViewById(R.id.cardNivel);
        cardMonedas = vista.findViewById(R.id.cardMonedas);
        cardGemas = vista.findViewById(R.id.cardGemas);
        cardJugador = vista.findViewById(R.id.cardJugador);
        cardJugadores = vista.findViewById(R.id.cardJugadores);
        cardAjustes = vista.findViewById(R.id.cardAjustes);
        Txtgemasmundo = vista.findViewById(R.id.gemasMundo);
        Txtjugadormundo = vista.findViewById(R.id.jugadorMundo);
        TxtmonedasMundo = vista.findViewById(R.id.monedasMundo);
        Txtnivelmundo = vista.findViewById(R.id.nivelMundo);
        Bjugar = vista.findViewById(R.id.juegoMundo);
        imagenPerfilMundo=vista.findViewById(R.id.imagenPerfilMundo);
        ViewModel = new ViewModelProvider(this).get(ViewModelMundo.class);



        //animacion
      /*  ValueAnimator animation = ValueAnimator.ofFloat(0f, 100f);
        animation.setDuration(1000);
        animation.start();

        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                // You can use the animated value in a property that uses the
                // same type as the animation. In this case, you can use the
                // float value in the translationX property.
                float animatedValue = (float)updatedAnimation.getAnimatedValue();
                BJugar.setTranslationX(animatedValue);
            }
        });*/
        // animacion



        cardNivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "ya me caseeee", Toast.LENGTH_SHORT).show();
            }
        });

        cardMonedas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "de tu tornillo suelto", Toast.LENGTH_SHORT).show();
            }
        });

        cardGemas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "me atragante el caramelo envuelto", Toast.LENGTH_SHORT).show();
            }
        });

        cardJugadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Tab dialogoPerfil = new Tab();
                dialogoPerfil.show(getActivity().getSupportFragmentManager(), "DialogoPerfil");


            }
        });

        cardJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Tab dialogoPerfil = new Tab();
                dialogoPerfil.show(getActivity().getSupportFragmentManager(), "DialogoPerfil");

            }
        });
        Bjugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent intent = new Intent(getActivity(), ActivityRoom.class);
              startActivity(intent);
            }
        });


        cardAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Ajustes.class);
                startActivity(intent);
            }
        });


        final Observer<String[]> observer = new Observer<String[]>() {
            @Override
            public void onChanged(String[] strings) {
                Txtjugadormundo.setText(strings[0]);
                Txtnivelmundo.setText(strings[1]);
                TxtmonedasMundo.setText(strings[2]);
                Txtgemasmundo.setText(strings[3]);
                if(!strings[4].isEmpty()){
                    imagenPerfilMundo.setImageResource(Integer.parseInt(strings[4]));
                }
            }


        };

        ViewModel.getDatosMundoMutableLiveData().observe(getActivity(), observer);
        ViewModel.DatosMundo();
        return vista;
    }


}






