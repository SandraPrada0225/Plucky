package com.example.plucky.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plucky.Clases.Amigos;
import com.example.plucky.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

//En este adaptador se visualizaran los amigosa del jugador

public class AdaptadorAmigos extends RecyclerView.Adapter<AdaptadorAmigos.MyHolder> implements View.OnClickListener{

private Context context;
private List<Amigos> usuarioList;
private View.OnClickListener listener;

public AdaptadorAmigos(Context context, List<Amigos> usuarioList) {
        this.context = context;
        this.usuarioList = usuarioList;
        }

@NonNull
@Override
public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.jugadores,parent,false);
        view.setOnClickListener(this);
        return new MyHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String  imagen="";
        String nombre="nombre";
        String correo=usuarioList.get(position).getAmigo();
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
        return usuarioList.size();
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
