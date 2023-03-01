   package com.example.plucky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plucky.Adaptadores.AdaptadorBloques;
import com.example.plucky.Clases.Bloque;
import com.example.plucky.Clases.Jugador;
import com.example.plucky.GameObjects.Movimiento;
import com.example.plucky.NavesJuego.input.KeyBoard;
import com.example.plucky.ViewModel.ViewModelEscenarioOnline;
import com.google.firebase.database.collection.LLRBNode;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

public class  EscenarioOnline extends AppCompatActivity {

    LinearLayoutManager mLayautManager;
    LinearLayoutManager mLayautManagerEjecutables;
    RecyclerView recyclerviewbloques;
    static RecyclerView recyclerViewMain;
    static AdaptadorBloques adaptador;
    static  ViewModelEscenarioOnline ViewModel;
    TextView nombreBloque;
    public static List<Bloque> bloques;
    static ArrayList<Bloque> ejecutables;
    TextView tiempoView;
    KeyBoard movimiento;
    CardView cardMover,cardEvento,cardControl, cardVariable,cardClase,cardOperadores,cardPercibir;
    int eliminar;
    int  estado;
    CountDownTimer cuenta;
    public MyDragEventListener mDragListen;
    private MyDragEventListenerBloques mDragListenBloques;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_escenario_online);
        nombreBloque=findViewById(R.id.nombreBloque);
        tiempoView=findViewById(R.id.tiempoEscenario);

        movimiento= new KeyBoard();
        ViewModel = new ViewModelProvider(this).get(ViewModelEscenarioOnline.class);
        estado=0;
        recyclerviewbloques =findViewById(R.id.recyclerviewbloques);
        recyclerViewMain =findViewById(R.id.recyclerviewMain);
        bloques= new ArrayList<>();
        ejecutables= new ArrayList<>();
        ejecutables.add(new Bloque(1, 4," Inicio ",0,0,0,0,0,0,ejecutables));
        recyclerviewbloques.setHasFixedSize(true);
        mLayautManager= new LinearLayoutManager(EscenarioOnline.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerviewbloques.setLayoutManager(mLayautManager);
        ViewModel.Puntajes();
        mLayautManagerEjecutables= new LinearLayoutManager(EscenarioOnline.this,LinearLayoutManager.VERTICAL,false);
        recyclerViewMain.setHasFixedSize(true);
        recyclerViewMain.setLayoutManager(mLayautManagerEjecutables);
        // Creates a new drag event listener
        mDragListen = new MyDragEventListener();
        mDragListenBloques = new MyDragEventListenerBloques();
        // Sets the drag event listener for the View
        recyclerViewMain.setOnDragListener(mDragListen);
        // Sets the drag event listener for the View
        recyclerviewbloques.setOnDragListener(mDragListenBloques);
        ItemTouchHelper itemTouchHelper= new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerViewMain);


        cardMover= findViewById(R.id.cardMover);
        cardClase= findViewById(R.id.cardClase);
        cardControl= findViewById(R.id.cardControl);
        cardEvento= findViewById(R.id.cardEvento);
        cardOperadores= findViewById(R.id.cardOperador);
        cardVariable=findViewById(R.id.cardVariable);
        cardPercibir= findViewById(R.id.cardPercibir);




        final Observer<List<Bloque>> observer= new Observer<List<Bloque>>() {
            @Override
            public void onChanged(List<Bloque> list) {
                if(list!=null){
                    bloques= list;
                     adaptador=new AdaptadorBloques(EscenarioOnline.this,list,0);
                    recyclerviewbloques.setAdapter(adaptador);

                }else{
                    Toast.makeText(EscenarioOnline.this, "No Hay elementos en la lista", Toast.LENGTH_SHORT).show();
                }
            }

        };
        ViewModel.getBloquesMutableLiveData().observe(this,observer);


        cardMover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardMover.setCardBackgroundColor(Color.GRAY);
                cardClase.setCardBackgroundColor(Color.TRANSPARENT);
                cardControl.setCardBackgroundColor(Color.TRANSPARENT);
                cardEvento.setCardBackgroundColor(Color.TRANSPARENT);
                cardOperadores.setCardBackgroundColor(Color.TRANSPARENT);
                cardVariable.setCardBackgroundColor(Color.TRANSPARENT);
                cardPercibir.setCardBackgroundColor(Color.TRANSPARENT);


                ViewModel.ObtenerBloquesMover();

                final Observer<List<Bloque>> observer= new Observer<List<Bloque>>() {
                    @Override
                    public void onChanged(List<Bloque> list) {
                        if(list!=null){
                            bloques= list;
                            adaptador=new AdaptadorBloques(EscenarioOnline.this,list,1);
                            recyclerviewbloques.setAdapter(adaptador);
                        }else{
                            Toast.makeText(EscenarioOnline.this, "No Hay elementos en la lista", Toast.LENGTH_SHORT).show();
                        }
                    }

                };
                ViewModel.getBloquesMutableLiveData().observe(EscenarioOnline.this,observer);
            }
        });



        cardClase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardMover.setCardBackgroundColor(Color.TRANSPARENT);
                cardClase.setCardBackgroundColor(Color.GRAY);
                cardControl.setCardBackgroundColor(Color.TRANSPARENT);
                cardEvento.setCardBackgroundColor(Color.TRANSPARENT);
                cardOperadores.setCardBackgroundColor(Color.TRANSPARENT);
                cardVariable.setCardBackgroundColor(Color.TRANSPARENT);
                cardPercibir.setCardBackgroundColor(Color.TRANSPARENT);

                ViewModel.ObtenerBloquesClases();

                final Observer<List<Bloque>> observer= new Observer<List<Bloque>>() {
                    @Override
                    public void onChanged(List<Bloque> list) {
                        if(list!=null){
                            bloques= list;
                            adaptador=new AdaptadorBloques(EscenarioOnline.this,list,2);
                            recyclerviewbloques.setAdapter(adaptador);
                        }else{
                            Toast.makeText(EscenarioOnline.this, "No Hay elementos en la lista", Toast.LENGTH_SHORT).show();
                        }
                    }

                };
                ViewModel.getBloquesMutableLiveData().observe(EscenarioOnline.this,observer);
            }
        });


        cardControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardMover.setCardBackgroundColor(Color.TRANSPARENT);
                cardClase.setCardBackgroundColor(Color.TRANSPARENT);
                cardControl.setCardBackgroundColor(Color.GRAY);
                cardEvento.setCardBackgroundColor(Color.TRANSPARENT);
                cardOperadores.setCardBackgroundColor(Color.TRANSPARENT);
                cardVariable.setCardBackgroundColor(Color.TRANSPARENT);
                cardPercibir.setCardBackgroundColor(Color.TRANSPARENT);

                ViewModel.ObtenerBloquesControl();

                final Observer<List<Bloque>> observer= new Observer<List<Bloque>>() {
                    @Override
                    public void onChanged(List<Bloque> list) {
                        if(list!=null){
                            bloques= list;
                            adaptador=new AdaptadorBloques(EscenarioOnline.this,list,3);
                            recyclerviewbloques.setAdapter(adaptador);
                        }else{
                            Toast.makeText(EscenarioOnline.this, "No Hay elementos en la lista", Toast.LENGTH_SHORT).show();
                        }
                    }

                };
                ViewModel.getBloquesMutableLiveData().observe(EscenarioOnline.this,observer);
            }
        });



        cardEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardMover.setCardBackgroundColor(Color.TRANSPARENT);
                cardClase.setCardBackgroundColor(Color.TRANSPARENT);
                cardControl.setCardBackgroundColor(Color.TRANSPARENT);
                cardEvento.setCardBackgroundColor(Color.GRAY);
                cardOperadores.setCardBackgroundColor(Color.TRANSPARENT);
                cardVariable.setCardBackgroundColor(Color.TRANSPARENT);
                cardPercibir.setCardBackgroundColor(Color.TRANSPARENT);
                ViewModel.ObtenerBloquesEvento();

                final Observer<List<Bloque>> observer= new Observer<List<Bloque>>() {
                    @Override
                    public void onChanged(List<Bloque> list) {
                        if(list!=null){
                            bloques= list;
                            adaptador=new AdaptadorBloques(EscenarioOnline.this,list,4);
                            recyclerviewbloques.setAdapter(adaptador);
                        }else{
                            Toast.makeText(EscenarioOnline.this, "No Hay elementos en la lista", Toast.LENGTH_SHORT).show();
                        }
                    }

                };
                ViewModel.getBloquesMutableLiveData().observe(EscenarioOnline.this,observer);
            }
        });

        cardOperadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardMover.setCardBackgroundColor(Color.TRANSPARENT);
                cardClase.setCardBackgroundColor(Color.TRANSPARENT);
                cardControl.setCardBackgroundColor(Color.TRANSPARENT);
                cardEvento.setCardBackgroundColor(Color.TRANSPARENT);
                cardOperadores.setCardBackgroundColor(Color.GRAY);
                cardVariable.setCardBackgroundColor(Color.TRANSPARENT);
                cardPercibir.setCardBackgroundColor(Color.TRANSPARENT);
                ViewModel.ObtenerBloquesOperadores();

                final Observer<List<Bloque>> observer= new Observer<List<Bloque>>() {
                    @Override
                    public void onChanged(List<Bloque> list) {
                        if(list!=null){
                            bloques= list;
                            adaptador=new AdaptadorBloques(EscenarioOnline.this,list,5);
                            recyclerviewbloques.setAdapter(adaptador);
                        }else{
                            Toast.makeText(EscenarioOnline.this, "No Hay elementos en la lista", Toast.LENGTH_SHORT).show();
                        }
                    }

                };
                ViewModel.getBloquesMutableLiveData().observe(EscenarioOnline.this,observer);
            }
        });

        final Observer<Bloque> observeBloque= new Observer<Bloque>() {
            @Override
            public void onChanged(Bloque bloque) {
                bloque.setPosition(ejecutables.size());
                ejecutables.add(bloque);
                adaptador= new AdaptadorBloques(EscenarioOnline.this,ejecutables,0);
                System.out.println("pero que carajos >:V ahora se van a duplicar :'v");
                recyclerViewMain.setAdapter(adaptador);
            }
        };
        ViewModel.getBloque().observe(this,observeBloque);
        iniciarCuentaRegresiva();
    }

    private void iniciarCuentaRegresiva(){
        System.out.println("aqui entra caramba");
        adaptador= new AdaptadorBloques(EscenarioOnline.this,ejecutables,0);
        recyclerViewMain.setAdapter(adaptador);
        cuenta= new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long tiempo= millisUntilFinished/1000;
                int minutos=Integer.parseInt(String.valueOf(tiempo/60));
                long segundos=tiempo%60;
                String  minutosMostrar= String.valueOf(minutos);
                String segundosMostrar=String.valueOf(segundos);
                tiempoView.setText(" "+minutosMostrar+" : "+segundosMostrar);
            }

            @Override
            public void onFinish() {
                Toast.makeText(EscenarioOnline.this, "yaaaaaaaa", Toast.LENGTH_SHORT).show();
                ViewModel.consultarId();
                final Observer<String> observer = new Observer<String>() {
                    @Override
                    public void onChanged(String id) {
                        if (id != null) {
                            ViewModel.editarJugadorRoom(id,ejecutables,getIntent().getExtras().getString("room"));
                            Toast.makeText(EscenarioOnline.this, "holiwis "+ ejecutables.size(), Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent (EscenarioOnline.this, Juego.class);
                            intent.putExtra("room",getIntent().getExtras().getString("room"));
                            intent.putExtra("idJugador",id);
                           // startActivity(intent);
                        }

                    }
                };
                ViewModel.getIdUsuario().observe(EscenarioOnline.this, observer);
            }
        }.start();

    }




    //eventos de arrastre bloques ejecutables
    protected class MyDragEventListener implements View.OnDragListener {

        public boolean onDrag(View v, DragEvent event) {


            final int action = event.getAction();

            switch (action) {


                case DragEvent.ACTION_DRAG_STARTED:

                    ItemTouchHelper.SimpleCallback simpleCallback= new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT |ItemTouchHelper.START|ItemTouchHelper.END,0) {
                        @Override
                        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                            int fromPosition=viewHolder.getLayoutPosition();
                            int toPosition= target.getLayoutPosition();
                            Collections.swap(ejecutables,fromPosition,toPosition);
                            System.out.println("se está moviendo a la posicion ");
                            recyclerView.getAdapter().notifyItemMoved(fromPosition,toPosition);
                            return false;
                        }

                        @Override
                        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                        }
                    };
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        v.invalidate();
                        return (true);
                    } else {
                        return (false);
                    }

                case DragEvent.ACTION_DRAG_ENTERED: {
                    if(estado==0){
                        estado=1;
                    }
                    v.invalidate();
                    return (true);
                }

                case DragEvent.ACTION_DRAG_LOCATION:
                    return (true);


                case DragEvent.ACTION_DRAG_EXITED:
                    v.invalidate();
                    return (true);


                case DragEvent.ACTION_DROP:
                    System.out.println(estado);
                    // Gets the item containing the dragged data
                    ClipData.Item item = event.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    String dragData =(String) item.getText();

                    // Displays a message containing the dragged data.
                   //Toast.makeText(EscenarioOnline.this, "Dragged data is "+ event.getLocalState(), Toast.LENGTH_LONG).show();
                    v.invalidate();

                    if(estado==2){
                        //aqui agregamos los bloques
                        Bloque bloque=generarbloques(bloques.get((Integer)event.getLocalState()),(int)event.getX(),(int)event.getY(),ejecutables);
                        if(bloque.getDistancia()!=-1){
                            System.out.println("calculamos la distancia "+bloque.getDistancia());
                            ejecutables.add(bloque.getPosition(),bloque);
                        }else {
                            System.out.println("jum pio");
                        }
                        adaptador= new AdaptadorBloques(EscenarioOnline.this,ejecutables,0);
                        recyclerViewMain.setAdapter(adaptador);
                        //ViewModel.Consultarbloque((Integer) event.getLocalState());


                    }else{
                        System.out.println("pailander");
                    }
                    estado=0;
                    return (true);


//                break;

                case DragEvent.ACTION_DRAG_ENDED:
                    v.invalidate();
                    return (true);

                default:
                    Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");

                    break;
            }
            return true;
        }

    }
    ItemTouchHelper.SimpleCallback simpleCallback= new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN| ItemTouchHelper.START|ItemTouchHelper.END, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition= viewHolder.getAdapterPosition();
            int toPosition=target.getAdapterPosition();
            Collections.swap(ejecutables,fromPosition,toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };


    //eventos de arrastre bloques elegibles
    protected class MyDragEventListenerBloques implements View.OnDragListener {

        public boolean onDrag(View v, DragEvent event) {

            final int action = event.getAction();

            switch (action) {




                case DragEvent.ACTION_DRAG_STARTED:
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                        v.invalidate();

                        return (true);

                    } else {
                        return (false);
                    }

                case DragEvent.ACTION_DRAG_ENTERED: {
                    if(estado==0){
                        estado=2;
                    }
                    v.invalidate();





                    return (true);
                }



                case DragEvent.ACTION_DRAG_LOCATION:
                    return (true);


                case DragEvent.ACTION_DRAG_EXITED:
                    v.invalidate();

                    return (true);


                case DragEvent.ACTION_DROP:
                    System.out.println(estado);
                    // Gets the item containing the dragged data
                    ClipData.Item item = event.getClipData().getItemAt(0);
                    // Gets the text data from the item.
                    String dragData =(String) item.getText();
                    // Displays a message containing the dragged data.
                    //Toast.makeText(EscenarioOnline.this, "Dragged data is "+ event.getLocalState(), Toast.LENGTH_LONG).show();
                    v.invalidate();
                    if(estado==1){
                        eliminar=Integer.parseInt(String.valueOf(event.getLocalState()));
                        ejecutables.remove(eliminar);
                        adaptador= new AdaptadorBloques(EscenarioOnline.this,ejecutables,0);
                        recyclerViewMain.setAdapter(adaptador);

                    }else{
                        System.out.println("landers");
                    }
                    estado=0;

                    return (true);
//                break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.invalidate();
                    return (true);

                default:
                    Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");

                    break;
            }
            return true;
        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();

    }



   public Bloque  generarbloques(Bloque bloqueAdd, int x, int y, List<Bloque> lista){
        System.out.println("esta es la cantidad de bloques "+lista.size());
        Bloque bloque;
        List<Bloque> listabloque;
       listabloque= new ArrayList<>();
        bloque= new Bloque(bloqueAdd.getIdbloque(), bloqueAdd.getConcepto(),bloqueAdd.getNombre(),0,x,y,0,0,0,listabloque);
       for (int i=0; i<lista.size();i++) {
            recyclerViewMain.getLayoutManager().findViewByPosition(i).getX();
           if((recyclerViewMain.getLayoutManager().findViewByPosition(i).getX()<x&&recyclerViewMain.getLayoutManager().findViewByPosition(i).getY()<y)&&(recyclerViewMain.getLayoutManager().findViewByPosition(i).getX()+recyclerViewMain.getLayoutManager().findViewByPosition(i).getWidth()>x&&recyclerViewMain.getLayoutManager().findViewByPosition(i).getY()+recyclerViewMain.getLayoutManager().findViewByPosition(i).getHeight()>y)&&lista.get(i).getConcepto()==3){
               lista.get(i).getBloques().add(bloque);
               System.out.println("Bananaaaaa");
           }
       }
       return bloque;
       }



    }
 /*Notas para arreglar
* la clase de buscar bloque ya no es necesaria
*
* */