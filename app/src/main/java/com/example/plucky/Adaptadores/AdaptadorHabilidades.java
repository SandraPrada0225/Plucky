package com.example.plucky.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plucky.Clases.Avatar;
import com.example.plucky.Clases.Habilidad;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.R;

import java.util.List;

//En este adaptador se podran visualizar todas las habilidades de Plucky elegibles en el modulo de equipo

public class AdaptadorHabilidades extends RecyclerView.Adapter<AdaptadorHabilidades.MyHolder> implements View.OnClickListener{

    private Context context;
    private List<Habilidad> listaHabilidades;
    private View.OnClickListener listener;
    View view;


    public AdaptadorHabilidades(Context context, List<Habilidad> listaHabilidades) {
        this.context = context;
        this.listaHabilidades = listaHabilidades;
    }

    @NonNull
    @Override
    public AdaptadorHabilidades.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(context).inflate(R.layout.habilidades,parent,false);
        view.setOnClickListener(this);
        return new AdaptadorHabilidades.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        int imagen= listaHabilidades.get(position).getImagen();
        holder.imageView.setImageResource(imagen);
    }


    @Override
    public int getItemCount() {
        return listaHabilidades.size();
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


    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.imagenHabilidad);
        }
    }
}

