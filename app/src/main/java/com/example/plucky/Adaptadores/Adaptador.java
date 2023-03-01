package com.example.plucky.Adaptadores;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plucky.Clases.Usuario;
import com.example.plucky.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

//En este adaptador se visualizaran todos los usuarios

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyHolder> implements View.OnClickListener{

    private Context context;
    private List<Usuario> usuarioList;
    private View.OnClickListener listener;

    public Adaptador(Context context, List<Usuario> usuarioList) {
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
         String  imagen=usuarioList.get(position).getImagen();
         String nombre=usuarioList.get(position).getNombre();
         String correo=usuarioList.get(position).getCorreo();
         String universidad=usuarioList.get(position).getUniversidad();
         String semestre= usuarioList.get(position).getSemestre();
        int nivel=usuarioList.get(position).getNivel();
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
