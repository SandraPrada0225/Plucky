package com.example.plucky.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plucky.Clases.Jugador;
import com.example.plucky.Clases.Room;
import com.example.plucky.Juego;
import com.example.plucky.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptadorJugador extends RecyclerView.Adapter<AdaptadorJugador.MyHolder> implements View.OnClickListener {

    private Context context;
    private List<Jugador> jugadors;
    private View.OnClickListener listener;

    public AdaptadorJugador(Context context, List<Jugador> jugadors) {
        this.context = context;
        this.jugadors = jugadors;
    }

    @NonNull
    @Override
    public AdaptadorJugador.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.jugadores,parent,false);
        view.setOnClickListener(this);
        return new AdaptadorJugador.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorJugador.MyHolder holder, int position) {
        String  imagen=jugadors.get(position).getArma();
        Toast.makeText(context, ""+imagen, Toast.LENGTH_SHORT).show();
        String nombre="nombre";

        String correo=jugadors.get(position).getId();
        String universidad="universidad";
        String semestre="semestre";
        int nivel=1;
        String n= String.valueOf(nivel);
        holder.nombreJugador.setText(nombre);
        holder.correoJugador.setText(correo);
        holder.nivelJugador.setText(n);
        if(!imagen.isEmpty()){
            holder.imagenJugador.setImageResource(Integer.parseInt(imagen));
        }
    }



    @Override
    public int getItemCount() {
        return jugadors.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        CircleImageView imagenJugador;
        TextView nombreJugador,correoJugador,nivelJugador;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            imagenJugador= itemView.findViewById(R.id.imagenJugador);
            nombreJugador= itemView.findViewById(R.id.nombreJugador);
            correoJugador= itemView.findViewById(R.id.correoJugador);
            nivelJugador= itemView.findViewById(R.id.nivelJugador);
        }
    }
}

