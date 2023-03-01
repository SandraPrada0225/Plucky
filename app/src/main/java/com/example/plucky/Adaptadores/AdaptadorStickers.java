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
import com.example.plucky.Clases.Mensaje;
import com.example.plucky.Clases.Sticker;
import com.example.plucky.R;

import java.util.List;

//En este adaptador se visualizaran todos los extickers que el usuario perteneciente a un clan podrá envia al chat del clan
public class AdaptadorStickers  extends RecyclerView.Adapter<AdaptadorStickers.MyHolder> implements View.OnClickListener{


    private Context context;
    private List<Sticker> listaSticker;
    private View.OnClickListener listener;
    View view;

    public AdaptadorStickers(Context context, List<Sticker> listaSticker) {
        this.context = context;
        this.listaSticker = listaSticker;
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
    public AdaptadorStickers.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(context).inflate(R.layout.sticker,parent,false);
        view.setOnClickListener(this);
        return new AdaptadorStickers.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorStickers.MyHolder holder, int position) {
        String animacion= listaSticker.get(position).getAnimacion();
        holder.animationView.setAnimation(animacion);
    }

    @Override
    public int getItemCount() {
        return listaSticker.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        LottieAnimationView animationView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            animationView= itemView.findViewById(R.id.animacionSticker);
        }
    }
}
