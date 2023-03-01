package com.example.plucky.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plucky.Clases.Clan;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


//En este adaptador se vsualizaran todos los clanes existentes

public class AdaptadorClan extends  RecyclerView.Adapter<AdaptadorClan.MyHolder>implements View.OnClickListener {

    private Context context;
    private List<Clan> clanList;
    private View.OnClickListener listener;

    public AdaptadorClan(Context context, List<Clan> clanList) {
        this.context = context;
        this.clanList = clanList;
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


    @NonNull
    @Override
    public AdaptadorClan.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.clan,parent,false);
        view.setOnClickListener(this);
        return new AdaptadorClan.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        int  imagen=clanList.get(position).getImagen();
        String nombre=clanList.get(position).getNombre();
        holder.imagenClan.setImageResource(imagen);
        holder.nombreclan.setText(nombre);
    }



    @Override
    public int getItemCount() {
        return clanList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        ImageView imagenClan;
        TextView nombreclan;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imagenClan= itemView.findViewById(R.id.imagenCardClan);
            nombreclan= itemView.findViewById(R.id.nombreCardClan);
        }
    }
}
