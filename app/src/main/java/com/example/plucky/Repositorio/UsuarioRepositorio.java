 package com.example.plucky.Repositorio;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.plucky.Clases.Amigos;
import com.example.plucky.Clases.Bloque;
import com.example.plucky.Clases.Clan;
import com.example.plucky.Clases.Habilidad;
import com.example.plucky.Clases.Jugador;
import com.example.plucky.Clases.Mensaje;
import com.example.plucky.Clases.Notificacion;
import com.example.plucky.Clases.Room;
import com.example.plucky.Clases.Solicitudes;
import com.example.plucky.Clases.Usuario;
import com.example.plucky.NavesJuego.gameObjects.Player;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

 //En este repositiorio se almacenarán todos los datos que cambian dentro del sistema: jugador, clan,
public class UsuarioRepositorio {
    Application aplication;

    private  FirebaseAuth firebaseAuth;
    FirebaseUser user;
    Usuario usuario;
    List<Jugador> jugadores;
    Clan clan;
    Room room;
    List<Usuario> usuarioList;
    List<Clan> clanList;
    List<Room> roomsList;

    Mensaje mensaje;
    Notificacion notificacion; 
    Solicitudes solicitud;
    List<Mensaje> mensajesClan;
    List<Amigos> listaAmigos;
    List<Bloque> listabloques;
    Amigos amigos;
    Jugador jugador;
    Bloque bloque;
    List<Notificacion> listNotificaciones;
    List<Solicitudes> listSolicitudes;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference Jugadores;
    DatabaseReference Grupos;
    DatabaseReference Rooms;


    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Usuario> usuarioMutableLiveData;
    private MutableLiveData<List<Notificacion>> notificacionMutableLiveData;
    private MutableLiveData<List<Amigos>> amigosMutableLiveData;
    private MutableLiveData<List<Jugador>> jugadoresMutableLiveData;
    private MutableLiveData<List<Bloque>>  bloquesMutableLiveData;
    private MutableLiveData<List<Solicitudes>> solicitudesMutableLiveData;
    private MutableLiveData<Usuario> usuariosPerfilMutableLiveData;
    private MutableLiveData<String> correoMutableLiveData;
    private MutableLiveData<String[]> mundoMutableLiveData;
    private MutableLiveData<List<Usuario>> PuntajesMutableLiveData;
    private MutableLiveData<String[]> mostrarEditarMutableLiveData;
    private MutableLiveData<String> idClanMutableLiveData;
    private MutableLiveData<String> idUsuarioMutabeLiveData;
    private MutableLiveData<String> verfificarClanMutableLiveData;
    private MutableLiveData<List<Clan>> listaClanMutableLiveData;
    private MutableLiveData<List<Room>> listaRoomMutableLiveData;
    private MutableLiveData<Clan> clanMutableLiveData;
    private MutableLiveData<List<Mensaje>> mensajesClanMutableLivedata;
    private MutableLiveData<String[]>  usuarioClanMutableLiveData;


    public UsuarioRepositorio() {
        amigos = new Amigos();
        jugador= new Jugador();
        bloque= new Bloque();
        usuario= new Usuario();
        clan= new Clan();
        room= new Room();
        mensajesClan=new ArrayList<>();
        jugadores=new ArrayList<>();
        listaAmigos= new ArrayList<>();
        listabloques= new ArrayList<>();
        listNotificaciones=new ArrayList<>();
        listSolicitudes= new ArrayList<>();
        usuarioList=new ArrayList<>();
        clanList= new ArrayList<>();
        roomsList= new ArrayList<>();
        aplication=new Application();
        amigosMutableLiveData= new MutableLiveData<>();
        jugadoresMutableLiveData= new MutableLiveData<>();
        bloquesMutableLiveData= new MutableLiveData<>();
        notificacionMutableLiveData= new MutableLiveData<>();
        solicitudesMutableLiveData= new MutableLiveData<>();
        usuariosPerfilMutableLiveData= new MutableLiveData<>();
        usuarioMutableLiveData=new MutableLiveData<>();
        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
        usuarioClanMutableLiveData= new MutableLiveData<>();
        mensajesClanMutableLivedata= new MutableLiveData<>();
        clanMutableLiveData= new MutableLiveData<>();
        mostrarEditarMutableLiveData= new MutableLiveData<>();
        idClanMutableLiveData= new MutableLiveData<>();
        idUsuarioMutabeLiveData= new MutableLiveData<>();
        verfificarClanMutableLiveData= new MutableLiveData<>();
        userMutableLiveData= new MutableLiveData<>();
        mundoMutableLiveData= new MutableLiveData<>();
        correoMutableLiveData= new MutableLiveData<>();
        PuntajesMutableLiveData= new MutableLiveData<>();
        firebaseDatabase=FirebaseDatabase.getInstance();
        usuarioMutableLiveData= new MutableLiveData<>();
        listaClanMutableLiveData= new MutableLiveData<>();
        listaRoomMutableLiveData= new MutableLiveData<>();
        Jugadores=firebaseDatabase.getReference("BaseJugadores");
        Grupos= firebaseDatabase.getReference("Clanes");
        Rooms= firebaseDatabase.getReference("Rooms");
    }



//el usuario se registra con normalidad
    public  void RegistrarJugador(Usuario usuario){

        firebaseAuth.createUserWithEmailAndPassword(usuario.getCorreo(),usuario.getClave()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user= firebaseAuth.getCurrentUser();
                    userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                    assert user != null;
                    String uidString = user.getUid();
                    HashMap<Object,Object> DatosJugador=new HashMap<>();

                    DatosJugador.put("Uid",uidString);
                    DatosJugador.put("Correo",usuario.getCorreo());
                    DatosJugador.put("Clave",usuario.getClave());
                    DatosJugador.put("Nombre",usuario.getNombre());
                    DatosJugador.put("Universidad",usuario.getUniversidad());
                    DatosJugador.put("Semestre",usuario.getSemestre());
                    DatosJugador.put("Imagen","");
                    DatosJugador.put("Fecha",usuario.getFecha());
                    DatosJugador.put("Nivel",usuario.getNivel());
                    DatosJugador.put("Monedas",usuario.getMonedas());
                    DatosJugador.put("Gemas", usuario.getGemas());
                    DatosJugador.put("Arma","0");
                    DatosJugador.put("Mascota","0");
                    DatosJugador.put("Escudo","0");
                    DatosJugador.put("Clan","0");


                    FirebaseDatabase database=FirebaseDatabase.getInstance();//instancia
                    DatabaseReference reference=database.getReference("BaseJugadores");//nombre de la base de datos
                    reference.child(uidString).setValue(DatosJugador);
                }else{

                }
            }
        })//si falla el registro
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }

//el usuario se registra e ingresa por google
    public void firebaseAuthWithGoogle(String idToken, Context context) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                            String uidString = user.getUid();
                            HashMap<Object,Object> DatosJugador=new HashMap<>();

                            DatosJugador.put("Uid",uidString);
                            DatosJugador.put("Correo",user.getEmail());
                            DatosJugador.put("Clave","");
                            DatosJugador.put("Nombre","");
                            DatosJugador.put("Universidad","");
                            DatosJugador.put("Semestre","");
                            DatosJugador.put("Imagen","");
                            DatosJugador.put("Fecha","");
                            DatosJugador.put("Nivel",1);
                            DatosJugador.put("Monedas",0);
                            DatosJugador.put("Gemas", 0);
                            DatosJugador.put("Clan",0);

                            FirebaseDatabase database=FirebaseDatabase.getInstance();//instancia
                            DatabaseReference reference=database.getReference("BaseJugadores");//nombre de la base de datos
                            reference.child(uidString).setValue(DatosJugador);
                            //updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            //   Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //updateUI(null);
                        }
                        // ...
                    }
                });
    }



// El usuario Ingresó normal y verificamos correo y clave
    public void VerificarJugador(String email, String clave){

        firebaseAuth.signInWithEmailAndPassword(email,clave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                }else{
   //                 Toast.makeText(aplication, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
    //            Toast.makeText(aplication, ""+e.getMessage(), Toast.LENGTH_SHORT).show()
            }
        });
    }

    //El usuario olvido su clave y le proporcionamos la opcion de recuperarla
    public void IniciaRecuperarClave(String correo, Context context){

        firebaseAuth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(context, "Mensaje enviado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "upss Ha ocurrudo un error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    //Verificamos que el usuario esté en linea
    public void UsuarioJugando(){
        userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
    }


    //Consultamos el clan del usuario
    public void VerificarClan(){
        Query query=Jugadores.orderByChild("Correo").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String clan = "" + ds.child("Clan").getValue();
                    verfificarClanMutableLiveData.postValue(clan);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //    Toast.makeText(aplication, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //El usuario ha salido, cerramos sesion
    public void CerrarSesion(){
        firebaseAuth.signOut();
    }




    public String Correo(){
        String correo=user.getEmail();
        return correo;
    }
    public void consultarId(){
        Query query=Jugadores.orderByChild("Correo").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String id= ("" + ds.child("Uid").getValue()).toString();
                    idUsuarioMutabeLiveData.postValue(id);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //    Toast.makeText(aplication, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
    }




 //Consultamos el perfil del usuario
    public void Perfil(){
        Query query=Jugadores.orderByChild("Correo").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String Uid = ("" + ds.child("Uid").getValue()).toString();
                    String nombre = "" + ds.child("Nombre").getValue();
                    String correo = "" + ds.child("Correo").getValue();
                    String imagen = "" + ds.child("Imagen").getValue();
                    String universidad = "" + ds.child("Universidad").getValue();
                    String semestre = "" + ds.child("Semestre").getValue();
                    String fecha = "" + ds.child("Fecha").getValue();
                    String monedasB = "" + ds.child("Monedas").getValue();
                    String gemasB = "" + ds.child("Gemas").getValue();
                    String nivelB = "" + ds.child("Nivel").getValue();
                    String clanBd= ""+ds.child("Clan").getValue();
                    List<Amigos> amigos;
                    amigos= new ArrayList<Amigos>();
                    List<Notificacion> notificacions;
                    notificacions= new ArrayList<Notificacion>();
                    List<Solicitudes> solicitudes;
                    solicitudes= new ArrayList<Solicitudes>();
                    int monedas=Integer.parseInt(monedasB);
                    int gemas=Integer.parseInt(gemasB);
                    int nivel=Integer.parseInt(nivelB);
                    usuarioMutableLiveData.postValue(new Usuario(Uid,correo, "", nombre, universidad, semestre, fecha, monedas, gemas, nivel,imagen,clanBd,amigos,notificacions,solicitudes));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            //    Toast.makeText(aplication, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Consultamos el perfil de otros usuarios
    public void PerfilUsuarios(String correoUsuario){
        Query query=Jugadores.orderByChild("Correo").equalTo(correoUsuario);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String Uid = ("" + ds.child("Uid").getValue()).toString();
                    String nombre = "" + ds.child("Nombre").getValue();
                    String correo = "" + ds.child("Correo").getValue();
                    String imagen = "" + ds.child("Imagen").getValue();
                    String universidad = "" + ds.child("Universidad").getValue();
                    String semestre = "" + ds.child("Semestre").getValue();
                    String fecha = "" + ds.child("Fecha").getValue();
                    String monedasB = "" + ds.child("Monedas").getValue();
                    String gemasB = "" + ds.child("Gemas").getValue();
                    String nivelB = "" + ds.child("Nivel").getValue();
                    String clanBd= ""+ds.child("Clan").getValue();
                    List<Amigos> amigos;
                    amigos= new ArrayList<Amigos>();
                    List<Notificacion> notificacions;
                    notificacions= new ArrayList<Notificacion>();
                    List<Solicitudes> solicitudes;
                    solicitudes= new ArrayList<Solicitudes>();
                    int monedas=Integer.parseInt(monedasB);
                    int gemas=Integer.parseInt(gemasB);
                    int nivel=Integer.parseInt(nivelB);

                    usuariosPerfilMutableLiveData.postValue(new Usuario(Uid,correo, "", nombre, universidad, semestre, fecha, monedas, gemas, nivel,imagen,clanBd,amigos,notificacions,solicitudes));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //    Toast.makeText(aplication, ""+error, Toast.LENGTH_SHORT).show();
            }
        });

    }


    //Consultamos el usuario y el clan
    public void ConsultarUsuarioClan(){
        Query query=Jugadores.orderByChild("Correo").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String Uid = ("" + ds.child("Uid").getValue()).toString();
                    String clanBd= ""+ds.child("Clan").getValue();
                    String[]Datos ={Uid,clanBd };
                    usuarioClanMutableLiveData.postValue(Datos);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //    Toast.makeText(aplication, ""+error, Toast.LENGTH_SHORT).show();

            }
        });

    }

    //consultamos las habilidades del usuario
    public void consultarHabilidades(){
        Query query=Jugadores.orderByChild("Correo").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String arma = "" + ds.child("Arma").child("imagenseleccion").getValue();
                    String mascota = "" + ds.child("Mascota").child("imagenseleccion").getValue();
                    String escudo = "" + ds.child("Escudo").child("imagenseleccion").getValue();
                    String[]Datos ={arma, mascota,escudo};
                    mundoMutableLiveData.postValue(Datos);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //    Toast.makeText(aplication, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
    }


     public void consultarHabilidadesid(){
         Query query=Jugadores.orderByChild("Correo").equalTo(user.getEmail());
         query.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 for (DataSnapshot ds : snapshot.getChildren()) {
                     String id=""+ds.child("Uid").getValue();
                     String arma = "" + ds.child("Arma").child("idHabilidad").getValue();
                     String mascota = "" + ds.child("Mascota").child("idHabilidad").getValue();
                     String escudo = "" + ds.child("Escudo").child("idHabilidad").getValue();
                     String[]Datos ={id,arma, mascota,escudo};
                     mundoMutableLiveData.postValue(Datos);
                 }
             }
             @Override
             public void onCancelled(@NonNull DatabaseError error) {
                 //    Toast.makeText(aplication, ""+error, Toast.LENGTH_SHORT).show();
             }
         });
     }
    //consultamos las monedas gemas y nivel presentados en el fragment de Mundo
    public void DatosMundo(){
            Query query=Jugadores.orderByChild("Correo").equalTo(user.getEmail());
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String nombre = "" + ds.child("Nombre").getValue();
                        String monedasB = "" + ds.child("Monedas").getValue();
                        String gemasB = "" + ds.child("Gemas").getValue();
                        String nivelB = "" + ds.child("Nivel").getValue();
                        String imagen= "" +ds.child("Imagen").getValue();
                        String[]Datos ={nombre, nivelB,monedasB, gemasB,imagen, };
                        mundoMutableLiveData.postValue(Datos);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    //    Toast.makeText(aplication, ""+error, Toast.LENGTH_SHORT).show();
                }
            });
    }


    //Se consultan los datos para editar perfil
    public void DatosMostrarEditar(){
        Query query=Jugadores.orderByChild("Correo").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String nombre = "" + ds.child("Nombre").getValue();
                    String universidad = "" + ds.child("Universidad").getValue();
                    String  semestre= "" + ds.child("semestre").getValue();
                    String imagen = "" + ds.child("Imagen").getValue();
                    String[]Datos ={nombre, universidad,semestre, imagen, };
                    mostrarEditarMutableLiveData.postValue(Datos);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }



    //Editamos los datos del perfil usuario
    public void Editar(String campo,String value,Context context){

        HashMap<String,Object> result= new HashMap<>();
        result.put(campo,value);
        Jugadores.child(user.getUid()).updateChildren(result).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
           //     Toast.makeText(Menu.this, "Datos actualizado correctamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
           //     Toast.makeText(Menu.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void EditarHabilidad(String campo, Habilidad value, Context context){

        HashMap<String,Object> result= new HashMap<>();
        result.put(campo,value);
        Jugadores.child(user.getUid()).updateChildren(result).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //     Toast.makeText(Menu.this, "Datos actualizado correctamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //     Toast.makeText(Menu.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    //Consultamos la lista de usuarios
    public void Puntajes(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();//instancia
        DatabaseReference reference=database.getReference("BaseJugadores");//nombre de la base de datos
        Query query=reference.orderByChild("Correo");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usuarioList.clear();
                usuarioList=new ArrayList<>();
                Usuario usuarionuevo=new Usuario();

                for(DataSnapshot ds: snapshot.getChildren() ){
                    usuarionuevo=ds.getValue(Usuario.class);
                    usuarioList.add(usuarionuevo);
                }
                PuntajesMutableLiveData.postValue(usuarioList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        System.out.println("aqui no hay nada que ver ");
    }

    //consultamos la lista de Clan existentes
    public void BuscarClan(){
         Grupos.orderByChild("Nombre").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clanList.clear();

                for(DataSnapshot ds: snapshot.getChildren() ){
                    clan=ds.getValue(Clan.class);
                    clanList.add(clan);
                }
                listaClanMutableLiveData.postValue(clanList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    //Creamos un nuevo clan
    public void CrearClan(Clan clan){
        HashMap<Object,Object> DatosClan=new HashMap<>();
        DatosClan.put("Nombre",clan.getNombre());
        DatosClan.put("imagen",clan.getImagen());
        DatosClan.put("Descripcion",clan.getDescripcion());
        DatosClan.put("tipo",clan.getEstado());
        DatosClan.put("Integrantes",clan.getIntegrantes());
        DatosClan.put("Mensajes",clan.getMensajes());
        FirebaseDatabase database=FirebaseDatabase.getInstance();//instancia
        DatabaseReference reference=database.getReference("Clanes");//nombre de la base de datos
        reference.child(clan.getNombre()).setValue(DatosClan);
        idClanMutableLiveData.postValue(String.valueOf(clan.getNombre()));
    }

    //Creamos una nueva solicitud
    public void CrearSolicitud(Solicitudes solicitud){
        HashMap<Object,Object> DatosSolicitud=new HashMap<>();
        DatosSolicitud.put("idSolicitud",solicitud.getIdSolicitud());
        DatosSolicitud.put("usuarioEnviar",solicitud.getUsuarioEnviar());
        DatosSolicitud.put("usuarioRecibir",solicitud.getUsuarioRecibir());
        DatosSolicitud.put("tiempo",solicitud.getTiempo());
        FirebaseDatabase database=FirebaseDatabase.getInstance();//instancia
        DatabaseReference reference=database.getReference("BaseJugadores");//nombre de la base de datos
        reference.child(solicitud.getUsuarioRecibir()).child("Solicitudes").child(solicitud.getIdSolicitud()).setValue(DatosSolicitud);
    }

    //Creamos una nueva notificacion
    public void CrearNotificacion(Notificacion notificacion){
        FirebaseUser user= firebaseAuth.getCurrentUser();
        HashMap<Object,Object> DatosNotificacion=new HashMap<>();
        DatosNotificacion.put("idNotificacion",notificacion.getIdNotificacion());
        DatosNotificacion.put("usuario",notificacion.getUsuario());
        DatosNotificacion.put("clan",notificacion.getClan());
        DatosNotificacion.put("imagenClan",notificacion.getImagenClan());
        DatosNotificacion.put("usuarioEmisor",notificacion.getUsuarioEmisor());
        DatosNotificacion.put("imagenUsuario",notificacion.getImagenUsuario());
        DatosNotificacion.put("tiempo",notificacion.getTiempo());
        FirebaseDatabase database=FirebaseDatabase.getInstance();//instancia
        DatabaseReference reference=database.getReference("BaseJugadores");//nombre de la base de datos
        reference.child(notificacion.getUsuario()).child("Notificaciones").child(notificacion.getIdNotificacion()).setValue(DatosNotificacion);

    }

    public void ConsultarNotificaciones(){
        Query query=Jugadores.orderByChild("Correo").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Jugadores.child(user.getUid()).child("Notificaciones").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            listNotificaciones.clear();
                            for(DataSnapshot dsd: snapshot.getChildren() ) {
                                notificacion = dsd.getValue(Notificacion.class);
                                listNotificaciones.add(notificacion);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    notificacionMutableLiveData.postValue(listNotificaciones);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    //Consultar solicitud
    public void ConsultarSolicitudes(){
        Query query=Jugadores.orderByChild("Correo").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Jugadores.child(user.getUid()).child("Solicitudes").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            listSolicitudes.clear();
                            for(DataSnapshot dsd: snapshot.getChildren() ) {
                                solicitud = dsd.getValue(Solicitudes.class);
                                System.out.println(solicitud.getUsuarioEnviar().getNombre());
                                listSolicitudes.add(solicitud);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    solicitudesMutableLiveData.postValue(listSolicitudes);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void EliminarNotificacion(String idNotificacion){
        FirebaseDatabase database=FirebaseDatabase.getInstance();//instancia
        DatabaseReference reference=database.getReference("BaseJugadores");//nombre de la base de datos
        reference.child(user.getUid()).child("Notificaciones").child(idNotificacion).removeValue();
    }
    public void eliminarSolicitud(String idSolicitud){
        FirebaseDatabase database=FirebaseDatabase.getInstance();//instancia
        DatabaseReference reference=database.getReference("BaseJugadores");//nombre de la base de datos
        reference.child(user.getUid()).child("Solicitudes").child(idSolicitud).removeValue();
    }



    //Mostramos el clan seleccionado y sus integrantes y mensajes
    public void MostrarClan(String nombreClan){
        Query query=Grupos.orderByChild("Nombre").equalTo(nombreClan);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String nombre = "" + ds.child("Nombre").getValue();
                    String descripcion = "" + ds.child("Descripcion").getValue();
                    String  estado= "" + ds.child("Estado").getValue();
                    String imagen = "" + ds.child("imagen").getValue();
                    int imagenClan= Integer.parseInt(imagen);

                    Grupos.child(nombreClan).child("Integrantes").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            usuarioList.clear();
                            for(DataSnapshot dsd: snapshot.getChildren() ){
                                usuario=dsd.getValue(Usuario.class);
                                System.out.println("vamos a ver que pasa"+usuario.getNombre());
                                usuarioList.add(usuario);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    Grupos.child(nombreClan).child("Mensajes").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            mensajesClan.clear();
                            for(DataSnapshot dsd: snapshot.getChildren() ) {
                                mensaje = dsd.getValue(Mensaje.class);
                                System.out.println("vamos a ver que pasa"+mensaje.getMensaje());
                                mensajesClan.add(mensaje);
                            }

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    clanMutableLiveData.postValue(new Clan(nombre, imagenClan,descripcion, estado, usuarioList, mensajesClan));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    //el usuario ha enviado un mensaje
    public void InsertarMensajesClan(Mensaje mensaje,String clan){

        HashMap<Object,Object> DatosMensaje=new HashMap<>();
        DatosMensaje.put("IdMensaje",mensaje.getIdMensaje());
        DatosMensaje.put("mensaje",mensaje.getMensaje());
        DatosMensaje.put("idemisor",mensaje.getIdemisor());
        DatosMensaje.put("tipo",mensaje.getTipo());
        DatosMensaje.put("tiempo",mensaje.getTiempo());
        FirebaseDatabase database=FirebaseDatabase.getInstance();//instancia
        DatabaseReference reference=database.getReference("Clanes");//nombre de la base de datos
        reference.child(clan).child("Mensajes").child(mensaje.getIdMensaje()).setValue(DatosMensaje);
    }


    //Consultamos los mensajes enviados
    public void ConsultarMensajesClan(String nombreClan){
        Query query=Grupos.orderByChild("Nombre").equalTo(nombreClan);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Grupos.child(nombreClan).child("Mensajes").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            mensajesClan.clear();
                            for(DataSnapshot dsd: snapshot.getChildren() ) {
                                mensaje = dsd.getValue(Mensaje.class);
                                System.out.println("vamos a ver que pasa"+mensaje.getMensaje());
                                mensajesClan.add(mensaje);
                            }

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    mensajesClanMutableLivedata.postValue(mensajesClan);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    // consultamos los amigos
    public void consultarAmigos(){
        Query query=Jugadores.orderByChild("Correo").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Jugadores.child(user.getUid()).child("Amigos").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            listaAmigos.clear();
                            for(DataSnapshot dsd: snapshot.getChildren() ) {
                                amigos = dsd.getValue(Amigos.class);
                                listaAmigos.add(amigos);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                    amigosMutableLiveData.postValue(listaAmigos);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    // consultamos los amigos
    public void agregarAmigos(Amigos amigos, Amigos amigos2,String uid1,String uid2){

        HashMap<Object,Object> DatosAmigo=new HashMap<>();
        DatosAmigo.put("Amigo", amigos.getAmigo());
        DatosAmigo.put("Tiempo", amigos.getTiempo());
        FirebaseDatabase database=FirebaseDatabase.getInstance();//instancia
        DatabaseReference reference=database.getReference("BaseJugadores");//nombre de la base de datos
        reference.child(uid1).child("Amigos").child(amigos.getTiempo()).setValue(DatosAmigo);

        HashMap<Object,Object> DatosAmigo2=new HashMap<>();
        DatosAmigo2.put("Amigo", amigos2.getAmigo());
        DatosAmigo2.put("Tiempo", amigos2.getTiempo());
        FirebaseDatabase database2=FirebaseDatabase.getInstance();//instancia
        DatabaseReference reference2=database2.getReference("BaseJugadores");//nombre de la base de datos
        reference2.child(uid2).child("Amigos").child(amigos2.getTiempo()).setValue(DatosAmigo2);

    }

    //consultamos los rooms existentes
    public void BuscarRoom(){
        Rooms.orderByChild("id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                roomsList.clear();

                for(DataSnapshot ds: snapshot.getChildren() ){
                    room=ds.getValue(Room.class);
                    roomsList.add(room);
                }
                listaRoomMutableLiveData.postValue(roomsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

     //Creamos un nuevo room
     public void CrearRoom(Room room){
         HashMap<Object,Object> DatosRoom=new HashMap<>();
         DatosRoom.put("id",room.getId());
         FirebaseDatabase database=FirebaseDatabase.getInstance();//instancia
         DatabaseReference reference=database.getReference("Rooms");//nombre de la base de datos
         reference.child(room.getId()).setValue(DatosRoom);
     }

     //añadir jugador a la sala
     public void agregarJugadorRoom(Jugador player, String room){
         HashMap<Object,Object> DatosJugador=new HashMap<>();
         DatosJugador.put("id",player.getId());
         DatosJugador.put("mensaje",player.getTiempo());
         DatosJugador.put("arma",player.getArma());
         DatosJugador.put("escudo",player.getEscudo());
         DatosJugador.put("mascota",player.getMascota());
         FirebaseDatabase database=FirebaseDatabase.getInstance();//instancia
         DatabaseReference reference=database.getReference("Rooms");//nombre de la base de datos
         reference.child(room).child("Jugadores").child(player.getId()).setValue(DatosJugador);
     }

     //editar jugador Room
     public void EditarJugadorRoom(String id, List<Bloque> bloques, String room){
         HashMap<String,Object> result= new HashMap<>();
         result.put("Bloques",bloques);
         Rooms.child(room).child("Jugadores").child(id).updateChildren(result).addOnSuccessListener(new OnSuccessListener<Void>() {
             @Override
             public void onSuccess(Void aVoid) {
                 //     Toast.makeText(Menu.this, "Datos actualizado correctamente", Toast.LENGTH_SHORT).show();
             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 //     Toast.makeText(Menu.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
             }
         });
     }

     public void consultarBloques(String id,String room){
         Rooms.child(room).child("Jugadores").child(id).child("Bloques").addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 listabloques.clear();
                 for(DataSnapshot dsd: snapshot.getChildren() ) {
                     bloque= dsd.getValue(Bloque.class);
                     listabloques.add(bloque);
                 }
                 bloquesMutableLiveData.postValue(listabloques);

             }
             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
     }


     //Consultamos los jugadores
     public void consultarJugadores(String idRoom){

         Rooms.child(idRoom).child("Jugadores").addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 jugadores.clear();
                 for(DataSnapshot dsd: snapshot.getChildren() ) {
                     jugador = dsd.getValue(Jugador.class);
                     jugadores.add(jugador);
                 }
                 jugadoresMutableLiveData.postValue(jugadores);

             }
             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });

     }





     public MutableLiveData<Usuario> getUsuarioMutableLiveData() {
        return usuarioMutableLiveData;
    }

    public MutableLiveData<String> getCorreoMutableLiveData() {
        return correoMutableLiveData;
    }
 
    public MutableLiveData<String[]> getMundoMutableLiveData() {
        return mundoMutableLiveData;
    }



    public MutableLiveData<List<Usuario>> getPuntajesMutableLiveData() {
        return PuntajesMutableLiveData;
    }

     public MutableLiveData<String> getIdUsuarioMutabeLiveData() {
         return idUsuarioMutabeLiveData;
     }

     public MutableLiveData<String> getIdClanMutableLiveData() {
        return idClanMutableLiveData;
    }

    public MutableLiveData<String> getVerfificarClanMutableLiveData() {
        return verfificarClanMutableLiveData;
    }

    public MutableLiveData<List<Clan>> getListaClanMutableLiveData() {
        return listaClanMutableLiveData;
    }

    public MutableLiveData<Clan> getClanMutableLiveData() {
        return clanMutableLiveData;
    }

    public MutableLiveData<List<Mensaje>> getMensajesClanMutableLivedata() {
        return mensajesClanMutableLivedata;
    }

    public MutableLiveData<String[]> getUsuarioClanMutableLiveData() {
        return usuarioClanMutableLiveData;
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<String[]> getMostrarEditarMutableLiveData() {
        return mostrarEditarMutableLiveData;
    }

    public MutableLiveData<Usuario> getUsuariosPerfilMutableLiveData() {
        return usuariosPerfilMutableLiveData;
    }

    public MutableLiveData<List<Notificacion>> getNotificacionMutableLiveData() {
        return notificacionMutableLiveData;
    }

    public MutableLiveData<List<Solicitudes>> getSolicitudesMutableLiveData() {
        return solicitudesMutableLiveData;
    }

    public MutableLiveData<List<Amigos>> getAmigosMutableLiveData() {
        return amigosMutableLiveData;
    }

     public MutableLiveData<List<Room>> getListaRoomMutableLiveData() {
         return listaRoomMutableLiveData;
     }

     public MutableLiveData<List<Jugador>> getJugadoresMutableLiveData() {
         return jugadoresMutableLiveData;
     }

     public MutableLiveData<List<Bloque>> getBloquesMutableLiveData() {
         return bloquesMutableLiveData;
     }
 }
