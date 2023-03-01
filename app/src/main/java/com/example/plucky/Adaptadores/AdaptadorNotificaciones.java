package com.example.plucky.Adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.plucky.Clases.Notificacion;
import com.example.plucky.Clases.Sticker;
import com.example.plucky.R;

import java.util.List;


//En este Adaptador se visualizaran las notificaciones de eventos importantes que se generen en el clan , si el usuaruio pertenece a un clan
public class AdaptadorNotificaciones extends RecyclerView.Adapter<AdaptadorNotificaciones.MyHolder> {

    private Context context;
    private List<Notificacion> listaNotificacions;
    private OnItemClickListener mlistener;
    View view;

    
    public AdaptadorNotificaciones(Context context, List<Notificacion> listaNotificacions) {
        this.context = context;
        this.listaNotificacions = listaNotificacions;
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
    public AdaptadorNotificaciones.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(context).inflate(R.layout.notificacion,parent,false);
        AdaptadorNotificaciones.MyHolder vh = new MyHolder(view, mlistener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorNotificaciones.MyHolder holder, int position) {
        String nombreUsuario= listaNotificacions.get(position).getUsuario();
        holder.nombreUsuario.setText( listaNotificacions.get(position).getUsuarioEmisor());
        if(listaNotificacions.get(position).getImagenUsuario().equals("")){
            holder.imagenUsuario.setImageResource(R.drawable.perfil);
        }else{
            holder.imagenUsuario.setImageResource(Integer.parseInt(listaNotificacions.get(position).getImagenUsuario()));
        }
        holder.nombreClan.setText( listaNotificacions.get(position).getClan());
        if(listaNotificacions.get(position).getImagenClan().equals("")){
            holder.imagenClan.setImageResource(R.drawable.escudo1);
        }else{
            holder.imagenClan.setImageResource(Integer.parseInt(listaNotificacions.get(position).getImagenClan()));
        }
    }

    @Override
    public int getItemCount() {
        return listaNotificacions.size();
    }




    public static class MyHolder extends RecyclerView.ViewHolder  {
       TextView nombreUsuario,nombreClan;
       ImageView imagenClan,imagenUsuario;
       ImageView imageViewRechazar, imageViewAceptar;

        public MyHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            nombreUsuario= itemView.findViewById(R.id.nombreEmisorNotificacion);
            imagenUsuario= itemView.findViewById(R.id.imagenEmisorNotificacion);
            imagenClan=itemView.findViewById(R.id.imagenClanNotificacion);
            nombreClan= itemView.findViewById(R.id.nombreClanNotificacion);
            imageViewAceptar= itemView.findViewById(R.id.aceptarNotificacionClan);
            imageViewRechazar= itemView.findViewById(R.id.rechazarNotificacionClan);
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
