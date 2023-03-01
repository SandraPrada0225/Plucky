package com.example.plucky.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.plucky.Clases.Solicitudes;
import com.example.plucky.Clases.Sticker;
import com.example.plucky.R;

import java.util.List;

//En este adaptador se visualizaran las solicitudes de amistad qu envian otros usuarios al jugador

public class AdaptadorSolicitudes extends RecyclerView.Adapter<AdaptadorSolicitudes.MyHolder>{

    private Context context;
    private List<Solicitudes> listaSolicitudes;
    private OnItemClickListener mlistener;
    View view;

    public AdaptadorSolicitudes(Context context, List<Solicitudes> listaSolicitudes) {
        this.context = context;
        this.listaSolicitudes = listaSolicitudes;
    }



    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
        void onAceptedClick(int position);

    }

    public void setOnClickListener(OnItemClickListener listener){
        mlistener=listener;
    }

    @NonNull
    @Override
    public AdaptadorSolicitudes.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(context).inflate(R.layout.solicitudes,parent,false);

        return new AdaptadorSolicitudes.MyHolder(view,mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorSolicitudes.MyHolder holder, int position) {
        String nombre= listaSolicitudes.get(position).getUsuarioEnviar().getNombre();
        holder.nombre.setText(nombre);
    }

    @Override
    public int getItemCount() {
        return listaSolicitudes.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        ImageView imageViewRechazar;
        CardView imageViewAceptar;
        public MyHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            nombre= itemView.findViewById(R.id.nombreEmisorSolicitud);
            imageViewAceptar= itemView.findViewById(R.id.aceptarSolicitud);
            imageViewRechazar= itemView.findViewById(R.id.rechazarSolicitud);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position= getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            imageViewRechazar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position= getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });

            imageViewAceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position= getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onAceptedClick(position);
                        }
                    }
                }
            });
        }

        }


    }

