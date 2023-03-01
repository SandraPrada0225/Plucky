package com.example.plucky.Adaptadores;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plucky.Clases.Avatar;
import com.example.plucky.Clases.Bloque;
import com.example.plucky.DetalleJugador;
import com.example.plucky.EscenarioOnline;
import com.example.plucky.R;
import com.example.plucky.ViewModel.ViewModelEscenarioOnline;
import com.google.android.gms.common.util.CollectionUtils;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;


// En este adaptador se visualizaran los bloques que el usuario podrá elegir en el momentro de programar a Plucky
public class  AdaptadorBloques extends RecyclerView.Adapter<AdaptadorBloques.MyHolder>{


    private Context context;
    private List<Bloque> Listabloques;
    private List<Bloque> listaBloquesGeneral;
    static Bloque bloque;
    static String arreglo="";
    ClipData dragData;
    EscenarioOnline escenarioOnline;
    int value;
    int estado;
    int position;
    static int posicionN;
    AdaptadorBloques adaptador;
    View view;




    public AdaptadorBloques(Context context, List<Bloque> listabloques, int value) {
        this.context = context;
        Listabloques = listabloques;
        listaBloquesGeneral= new ArrayList<>();
        estado=0;
        this.value= value;
        escenarioOnline = new EscenarioOnline();
        position=0;
        posicionN=0;
        bloque= new Bloque();
    }



    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(context).inflate(R.layout.bloquecontrol,parent,false);
        return new AdaptadorBloques.MyHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        if (value == 1) {
            listaBloquesGeneral = Listabloques;
        }


        if(!CollectionUtils.isEmpty(Listabloques.get(position).getBloques()) ){
            System.out.println("si hay elementos juemadreeeee ");
            adaptador=new AdaptadorBloques(context,Listabloques.get(position).getBloques(),0);
            holder.recyclerView.setAdapter(adaptador);
        }

        this.position = position;
        String nombre = Listabloques.get(position).getNombre();
        holder.nombreBloque.setText(nombre);


        int color;
        if (Listabloques.get(position).getConcepto() == 1) {
            color = Color.argb(250, 223, 136, 164);
            holder.card.setCardBackgroundColor(color);
            color = Color.argb(250, 166, 51, 88);
            holder.nombreBloque.setTextColor(color);
        } else if (Listabloques.get(position).getConcepto() == 2) {
            color = Color.argb(250, 255, 64, 64);
            holder.card.setCardBackgroundColor(color);
        } else if (Listabloques.get(position).getConcepto() == 3) {
            color = Color.argb(250, 255, 191, 50);
            holder.card.setCardBackgroundColor(color);
        } else if (Listabloques.get(position).getConcepto() == 4) {
            color = Color.argb(250, 70, 200, 126);
            holder.card.setCardBackgroundColor(color);
        } else if (Listabloques.get(position).getConcepto() == 5) {
            color = Color.argb(250, 209, 209, 217);
            holder.card.setCardBackgroundColor(color);
        }

        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) holder.card.getLayoutParams();
        marginParams.setMargins(Listabloques.get(position).getDistancia(), marginParams.topMargin, marginParams.rightMargin, marginParams.bottomMargin);
        holder.card.setLayoutParams(marginParams);



      holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                posicionN=holder.getAdapterPosition();
                ClipData.Item item = new ClipData.Item((String)v.getTag());
                holder.getAdapterPosition();
                dragData = new ClipData( (String )v.getTag(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);
                v.startDragAndDrop(dragData,myShadow, holder.getAdapterPosition(), 0);
                return true;
            }
        });

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             




    }


    @Override
    public int getItemCount() {
        if (Listabloques != null)
            return Listabloques.size();
        else
            return 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView nombreBloque;
        CardView card;
        RecyclerView recyclerView;
        LinearLayoutManager mLayautManager;


        AdaptadorBloques adaptador;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            card= itemView.findViewById(R.id.cardBloque);
            nombreBloque= itemView.findViewById(R.id.nombreBloque);
            recyclerView= itemView.findViewById(R.id.recyclerviewBloque);
            mLayautManager= new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(mLayautManager);
            }


    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    //Definimos aqui la sombra
    private static class MyDragShadowBuilder extends View.DragShadowBuilder {
        private static Drawable shadow;

        public MyDragShadowBuilder(View v) {
            super(v);
            shadow = new ColorDrawable(Color.LTGRAY);
        }

        @Override
        public void onProvideShadowMetrics (Point size, Point touch){
            int width = getView().getWidth();
            int height = getView().getHeight();
            shadow.setBounds(0, 0, width, height);
            size.set(width, height);
            touch.set(width / 2, height / 2);
        }
        @Override
        public void onDrawShadow(Canvas canvas) {
            shadow.draw(canvas);
        }

    }

     





}
