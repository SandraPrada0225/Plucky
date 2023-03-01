package com.example.plucky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plucky.ViewModel.ViewModelIngreso;
import com.example.plucky.ViewModel.ViewModelRegistro;
import com.example.plucky.ui.mundo.MundoFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

public class Ingreso extends AppCompatActivity {
    private static final int RC_SIGN_IN=100;
    GoogleSignInClient getSignInIntent;
    EditText TxtCorreoIngreso,TxtClaveIngreso;
    Button Bingreso;
    SignInButton googlebutton;
    TextView recuperarClaveView;

    private ViewModelIngreso viewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);
        TxtClaveIngreso=findViewById(R.id.claveRegistrada);
        TxtCorreoIngreso=findViewById(R.id.correoRegistrado);
        Bingreso=findViewById(R.id.ingreso);
        googlebutton=findViewById(R.id.ingresoGoogle);
        recuperarClaveView=findViewById(R.id.recuperarcontraseña);

        //ingreso por google
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        getSignInIntent= GoogleSignIn.getClient(this,gso);

        recuperarClaveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarClave();
            }
        });


        viewModel = new ViewModelProvider(this).get(ViewModelIngreso.class);

        Bingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=TxtCorreoIngreso.getText().toString();
                String clave=TxtClaveIngreso.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    TxtCorreoIngreso.setError("Coreo no valido");
                    TxtCorreoIngreso.setFocusable(true);
                }else if (clave.length()<6){
                    TxtClaveIngreso.setError("la contraseña debe ser mayor a 6");
                    TxtClaveIngreso.setFocusable(true);
                }else{
                    viewModel.IngresoJugador(email,clave);

                }

            }
        });

        final Observer<FirebaseUser> observer= new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {

                if(firebaseUser!=null){
                    Intent intent=new Intent (Ingreso.this, Menu.class);
                    startActivity(intent);
                }
            }
        };

        viewModel.getUserMutableLiveData().observe(this,observer);

        googlebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signInIntent = getSignInIntent.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);


            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                viewModel.IngresoJugadorGoogle(account.getIdToken(),this);

            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                // ...
            }
        }
    }
    private void recuperarClave(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Recuperar Contraseña");

        LinearLayout linearLayout= new LinearLayout(this);
        final EditText correo= new EditText(this);
        correo.setHint("Correo");
        correo.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        correo.setMinEms(10);
        linearLayout.addView(correo);
        linearLayout.setPadding(10,10,10,10);

        builder.setView(linearLayout);

        builder.setPositiveButton("Recordar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String correoRecuperar = correo.getText().toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(correoRecuperar).matches()) {
                    correo.setError("Coreo no valido");
                    correo.setFocusable(true);
                } else {
                    viewModel.RecuperarClave(correoRecuperar, Ingreso.this);
                }
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




}