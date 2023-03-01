package com.example.plucky.Adaptadores;

import android.content.Context;
import android.content.SyncStatusObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plucky.Clases.Avatar;
import com.example.plucky.Clases.EscudoClan;
import com.example.plucky.R;

import java.util.List;


//en este adaptador se visualizaran todos escudos que jugador creador de un clan podrá elegir
public class AdaptadorEscudos extends RecyclerView.Adapter<AdaptadorEscudos.MyHolder>implements View.OnClickListener {

    private Context context;
    private List<EscudoClan> ListaEscudos;
    private View.OnClickListener listener;
    View view;

    public AdaptadorEscudos(Context context, List<EscudoClan> listaEscudos) {
        this.context = context;
        ListaEscudos = listaEscudos;
    }
    @NonNull
    @Override
    public AdaptadorEscudos.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(context).inflate(R.layout.escudo,parent,false);
        view.setOnClickListener(this);
        return new AdaptadorEscudos.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorEscudos.MyHolder holder, int position) {
        int imagen= ListaEscudos.get(position).getEscudoclan();
        holder.imageView.setImageResource(imagen);
    }

    @Override
    public int getItemCount() {
        return ListaEscudos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }


    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.imagenEscudo);
        }
    }
}
