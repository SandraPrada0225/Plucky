package com.example.plucky.ui.Dialogos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plucky.Adaptadores.AdaptadorAvatar;
import com.example.plucky.Adaptadores.AdaptadorBloques;
import com.example.plucky.Clases.Avatar;
import com.example.plucky.Clases.Bloque;
import com.example.plucky.EscenarioOnline;
import com.example.plucky.Ingreso;
import com.example.plucky.R;
import com.example.plucky.ViewModel.ViewModelEditarPerfil;
import com.example.plucky.ViewModel.ViewModelEscenarioOnline;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentEditarPerfil#newInstance} factory method to
 * create an instance of this fragment.
 * En este fragment el jugador podrá editar: Avatar, Nombre, universidad, semestre. Del perfil
 */
public class FragmentEditarPerfil extends AppCompatDialogFragment {

    LinearLayoutManager mLayautManager;
    RecyclerView recyclerviewAvatar;
    ViewModelEditarPerfil viewModel;
    AdaptadorAvatar adaptador;
    ImageView imagenEditar,salir;
    View vista;
    Context contexto;
    Avatar avatatarSeleccionado;
    TextView nombreEditarTxt,universidadEditarTxt,semestreEditarTxt;
    Button botonEditarNombre,botonEditarUniversidad,botonEditarsemestre;



    public FragmentEditarPerfil() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEditarPerfil.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEditarPerfil newInstance(String param1, String param2) {
        FragmentEditarPerfil fragment = new FragmentEditarPerfil();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_editar_perfil, container, false);
        recyclerviewAvatar =vista.findViewById(R.id.recyclerviewAvatars);
        imagenEditar=vista.findViewById(R.id.imagenEditarAvatar);
        nombreEditarTxt= vista.findViewById(R.id.nombreEditar);
        universidadEditarTxt= vista.findViewById(R.id.universidadEditar);
        semestreEditarTxt= vista.findViewById(R.id.semestreEditar);
        botonEditarNombre= vista.findViewById(R.id.botonEditarNombre);
        botonEditarUniversidad= vista.findViewById(R.id.botonEditarUniversidad);
        botonEditarsemestre= vista.findViewById(R.id.botonEditarSemestre);
        salir= vista.findViewById(R.id.salirEditarPerfil);

        recyclerviewAvatar.setHasFixedSize(true);
        mLayautManager= new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerviewAvatar.setLayoutManager(mLayautManager);
        contexto=getContext();

        viewModel = new ViewModelProvider(this).get(ViewModelEditarPerfil.class);
        viewModel.ConsultarAvatars();
        viewModel.ConsultarMostrarDatosEditar();

        final Observer<String[]> observerDatos = new Observer<String[]>() {
            @Override
            public void onChanged(String[] strings) {
                nombreEditarTxt.setText(strings[0]);
                universidadEditarTxt.setText(strings[1]);
                semestreEditarTxt.setText(strings[2]);
                if(!strings[3].isEmpty()){
                    imagenEditar.setImageResource(Integer.parseInt(strings[3]));
                }
            }
        };
        viewModel.getDatosMostrarEditar().observe(this, observerDatos);




        final Observer<List<Avatar>> observer= new Observer<List<Avatar>>() {
            @Override
            public void onChanged(List<Avatar> list) { 
                if(list!=null){
                    adaptador=new AdaptadorAvatar(getContext(),list);
                    adaptador.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            avatatarSeleccionado=list.get(recyclerviewAvatar.getChildAdapterPosition(v));
                            imagenEditar.setImageResource(avatatarSeleccionado.getAvatarId());
                            viewModel.Editar("Imagen", String.valueOf(avatatarSeleccionado.getAvatarId()) ,getContext());
                        }
                    });
                    recyclerviewAvatar.setAdapter(adaptador);
                }else{
                    Toast.makeText(getContext(), "No Hay elementos en la lista", Toast.LENGTH_SHORT).show();
                }
            }

        };
        viewModel.getAvatars().observe(this,observer);

        botonEditarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editarnombre();
            }
        });

        botonEditarUniversidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditarUniversidad();
            }
        });

        botonEditarsemestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditarSemestre();
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return vista;

    }

    public void Editarnombre(){
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        builder.setTitle("Editar Nombre");

        LinearLayout linearLayout= new LinearLayout(getContext());
        final EditText nombre= new EditText(getContext());
        nombre.setHint("Nombre");
        nombre.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        nombre.setMinEms(10);
        linearLayout.addView(nombre);
        linearLayout.setPadding(10,10,10,10);

        builder.setView(linearLayout);

        builder.setPositiveButton("Cambiar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nombreEditar = nombre.getText().toString().trim();
                viewModel.Editar("Nombre",nombreEditar,getContext());
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    public void EditarUniversidad(){
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        builder.setTitle("Editar Universidad");

        LinearLayout linearLayout= new LinearLayout(getContext());
        final EditText universidad= new EditText(getContext());
        universidad.setHint("Universidad");
        universidad.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        universidad.setMinEms(10);
        linearLayout.addView(universidad);
        linearLayout.setPadding(10,10,10,10);

        builder.setView(linearLayout);

        builder.setPositiveButton("Cambiar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String universidadEditar = universidad.getText().toString().trim();
                viewModel.Editar("Universidad",universidadEditar,getContext());
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();

    }

    public void EditarSemestre(){
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        builder.setTitle("Editar Semestre");

        LinearLayout linearLayout= new LinearLayout(getContext());
        final EditText semestre= new EditText(getContext());
        semestre.setHint("Correo");
        semestre.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        semestre.setMinEms(10);
        linearLayout.addView(semestre);
        linearLayout.setPadding(10,10,10,10);

        builder.setView(linearLayout);

        builder.setPositiveButton("cambiar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String semestreEditar = semestre.getText().toString().trim();
                viewModel.Editar("Semestre",semestreEditar,getContext());
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();

    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return super.onContextItemSelected(item);
    }


}