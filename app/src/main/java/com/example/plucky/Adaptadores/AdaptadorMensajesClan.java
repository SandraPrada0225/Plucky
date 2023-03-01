package com.example.plucky.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.plucky.Clases.Habilidad;
import com.example.plucky.Clases.Mensaje;
import com.example.plucky.R;

import java.util.List;

//En este adaptador se visualizaran los mensajes que envia el clan en el chat, si el jugador pertenece a un clan

public class AdaptadorMensajesClan extends RecyclerView.Adapter<AdaptadorMensajesClan.MyHolder> implements View.OnClickListener {


    private Context context;
    private List<Mensaje> listaMensaje;
    private View.OnClickListener listener;
    int messageizquierda=0,messagederecha=1;
    String usuario;
    View view;

    public AdaptadorMensajesClan(Context context, List<Mensaje> listaMensajes,String usuario) {
        this.context = context;
        this.listaMensaje = listaMensajes;
        this.usuario=usuario;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @NonNull
    @Override
    public AdaptadorMensajesClan.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType==messagederecha){
            view= LayoutInflater.from(context).inflate(R.layout.mensajederecha,parent,false);
            view.setOnClickListener(this);
            return new AdaptadorMensajesClan.MyHolder(view);
        }else{
            view= LayoutInflater.from(context).inflate(R.layout.mensaje,parent,false);
            view.setOnClickListener(this);
            return new AdaptadorMensajesClan.MyHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorMensajesClan.MyHolder holder, int position) {
        String mensaje= listaMensaje.get(position).getMensaje();
        String emisor= listaMensaje.get(position).getIdemisor();
        String time = listaMensaje.get(position).getTiempo();

        if(listaMensaje.get(position).getTipo().equals("Text")){
            holder.imageView.setVisibility(view.GONE);
            holder.textView.setVisibility(view.VISIBLE);
            holder.textView.setText(mensaje);
        }else{
            holder.imageView.setVisibility(view.VISIBLE);
            holder.textView.setVisibility(view.GONE);
            holder.imageView.setAnimation(mensaje);
        }
       // holder.imageView.setImageResource(imagen);
    }

    @Override
    public int getItemViewType(int position) {
       if(listaMensaje.get(position).getIdemisor().equals(usuario)){
           return messagederecha;
       }else{
           return messageizquierda;
       }
    }

    @Override
    public int getItemCount() {
        return listaMensaje.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        LottieAnimationView imageView;
        TextView textView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.imagenEnviar);
            textView= itemView.findViewById(R.id.mensaje);

        }
    }
}
