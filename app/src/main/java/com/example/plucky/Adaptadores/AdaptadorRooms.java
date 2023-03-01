package com.example.plucky.Adaptadores;

import android.content.Context;
import android.content.SyncStatusObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plucky.Clases.Room;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptadorRooms extends RecyclerView.Adapter<AdaptadorRooms.MyHolder> implements View.OnClickListener {

    private Context context;
    private List<Room> roomsList;
    private View.OnClickListener listener;

    public AdaptadorRooms(Context context, List<Room> roomsList) {
        this.context = context;
        this.roomsList = roomsList;
    }

    @NonNull
    @Override
    public AdaptadorRooms.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.room,parent,false);
        view.setOnClickListener(this);
        return new AdaptadorRooms.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String nombre=roomsList.get(position).getId();
        holder.nombreroom.setText(nombre);
        System.out.println("sabor a miiiiiii "+nombre);
    }



    @Override
    public int getItemCount() {
        return roomsList.size();
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

        TextView nombreroom;
        CardView card;

        public MyHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
