package com.example.plucky.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plucky.Clases.Avatar;
import com.example.plucky.EscenarioOnline;
import com.example.plucky.R;
import com.example.plucky.ui.Dialogos.FragmentEditarPerfil;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

//En este adaptador se visualizaran los avatar que el usuario podrá elegir

public class AdaptadorAvatar extends RecyclerView.Adapter<AdaptadorAvatar.MyHolder>implements View.OnClickListener {
    private Context context;
    private List<Avatar> ListaAvatar;
    private View.OnClickListener listener;
    View view;


    public AdaptadorAvatar(Context context, List<Avatar> listaAvatar) {
        this.context = context;
        ListaAvatar = listaAvatar;
    }

    @NonNull
    @Override
    public AdaptadorAvatar.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(context).inflate(R.layout.avatar,parent,false);
        view.setOnClickListener(this);
        return new AdaptadorAvatar.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        int imagen= ListaAvatar.get(position).getAvatarId();
        holder.imageView.setImageResource(imagen);
    }


    @Override
    public int getItemCount() {
        return ListaAvatar.size();
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
            imageView= itemView.findViewById(R.id.imagenAvatar);
        }
    }
}
