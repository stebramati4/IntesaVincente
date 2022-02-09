package com.example.intesavincente.repository.partita;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.intesavincente.model.Gruppo;
import com.example.intesavincente.model.Partita;
import com.example.intesavincente.model.Utente;
import com.example.intesavincente.repository.user.UserRepository;
import com.example.intesavincente.repository.utente.UtenteRepository;
import com.example.intesavincente.utils.Constants;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PartitaRepository {

    private static final String TAG = "PartitaRepository";
    DatabaseReference dbGruppi;
    private DatabaseReference dbPartite;
    private Button creaGruppoButton;
    private EditText nomeGruppo;
    private Snackbar snackbarCreaGruppo;
    private UtenteRepository mUtenteRepository = new UtenteRepository();
    public void PartitaRepository(){

    }
    public void inserisciGruppoInPartita(String gruppoID) {
        Partita p=new Partita(gruppoID);
        dbPartite = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("partite");
        String TAG ="CreaGruppoFragment" ;
        String partitaID=dbPartite.push().getKey();
        dbPartite.child(partitaID).setValue(p);
        mUtenteRepository.aggiungiIDPartita(partitaID);
    }
    public void inserisciPartitaInUtente(String gruppoID){
        DatabaseReference dbPartite = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("partite");
        dbPartite.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> keysPartite = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    Log.d(TAG, "KeyNode " + keyNode);
                    keysPartite.add(keyNode.getKey());
                    if (keyNode.child("gruppoID").getValue().equals(gruppoID)) {
                        String chiave=keyNode.getKey();
                        keysPartite.add(keyNode.getKey());
                        Log.d(TAG, "chiave " + chiave);
                        DatabaseReference dbUtenti = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("utenti");
                        dbUtenti.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                List<String> keysUtenti = new ArrayList<>();
                                for (DataSnapshot keyNode : snapshot.getChildren()) {
                                    Log.d(TAG, "KeyNode " + keyNode);
                                    keysUtenti.add(keyNode.getKey());
                                    if (keyNode.child("idUtente").getValue().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                                        keysUtenti.add(keyNode.getKey());
                                        Log.d(TAG, "KeysPartite " + keysPartite.size());
                                        for (int i = 0; i < keysPartite.size(); i++) {
                                            //if(!keysPartite.get(i).equals(chiave)) {
                                               // if (keyNode.child("partite").child(String.valueOf(i)).getValue(String.class) == null) {
                                                    if(!keyNode.child("partite").child(String.valueOf(i)).getValue(String.class).equals(chiave)){
                                                        //if (keysPartite.get(i) == null) {
                                                        String utenteID= FirebaseAuth.getInstance().getCurrentUser().getUid();
                                                        Log.d(TAG, "tipo Partite" + keyNode.child("idUtente").getClass());
                                                        String nickname= (String) keyNode.child("nickname").getValue();
                                                        String mail= (String)keyNode.child("mail").getValue();
                                                        String password= (String) keyNode.child("password").getValue();
                                                        ArrayList <String> partite=new ArrayList<>();
                                                        partite= (ArrayList<String>) keyNode.child("partite").getValue();
                                                        Utente u=new Utente(utenteID,nickname,mail,password);
                                                        if(partite.size()==1&&partite.get(0).equals("prova")){
                                                            Log.d(TAG, "dentro if22" );
                                                            partite.remove(0);
                                                            partite.add(0,chiave);
                                                            u.setPartite(partite);
                                                        }
                                                        else{
                                                            Log.d(TAG, "dentro if223");
                                                            u.setPartite(partite);
                                                            u.aggiungiPartita(chiave);
                                                        }
                                                        Log.d(TAG, "valori utente"+ u.toString1());
                                                        Log.d(TAG, "valori utente"+ u.getPartite());
                                                        String idUtente = keyNode.getKey().toString();
                                                        System.out.println(idUtente);
                                                        dbUtenti.child(idUtente).setValue(u);



                                                   // Utente utente = (Utente) keyNode.getValue(Utente.class);
                                                    //utente.setPartite(chiave);
                                                    //String idUtente = keyNode.getKey().toString();
                                                    //dbUtenti.child(idUtente).setValue(utente);
                                                   //Log.d(TAG, "partiteid " + keyNode.child("partite").child(String.valueOf(i)).getValue(String.class));
                                                    //keyNode.child("partite").child(String.valueOf(i)).getRef().setValue(chiave);
                                                  //  keyNode.child("partite").child(String.valueOf(i)).setValue(chiave);
                                                    break;
                                                    // }
                                            }
                                            //}


                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void trovaPartita() { //tipo void momentaneamente per far andare l'app, sarebbe tipo Partita
        String TAG ="Indovinatore" ;
        Log.d(TAG,"trovaPartita1" );

        Partita p = null;
        dbGruppi = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("gruppi");
        dbPartite = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("partite");
        ArrayList<String> chiaveGruppo = new ArrayList<>();
        ArrayList <Partita> pa=new ArrayList<>();
        dbGruppi.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> keys = new ArrayList<>();
                String singolachiave = null;
                Log.d(TAG,"trovaPartita2" );
                //Partita p = null;
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Log.d(TAG,"trovaPartita3"+keyNode.getKey());
                    for (int i = 0; i < 3; i++) {
                        if (keyNode.child("componenti").child(String.valueOf(i)).getValue(String.class).equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                            chiaveGruppo.add(keyNode.getKey());
                            singolachiave = keyNode.getKey();
                            String finalSingolachiave = singolachiave;
                            Log.d(TAG, "chiave gruppo a cui appartiene l'utente"+singolachiave );
                            Log.d(TAG, "chiave gruppo a cui appartiene l'utente1"+finalSingolachiave );
                            dbPartite.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    Log.d(TAG, "dbpartite");
                                    List<String> keys = new ArrayList<>();
                                    for (DataSnapshot keyNode : snapshot.getChildren()) {
                                        keys.add(keyNode.getKey());
                                        Log.d(TAG, "fuori if");
                                        if ((keyNode.child("gruppoID").equals(finalSingolachiave))) {
                                            // && (keyNode.child("attiva").equals("true"))
                                            Log.d(TAG, "partitaEsiste");
                                            Partita po = (Partita) keyNode.getValue(Partita.class);
                                           // return po;
                                            pa.add(po);
                                            Log.d(TAG, "partita " + pa.get(0).toString());
                                        }

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //return pa.get(0);
    //return p;
    }





    public void chiudiPartita(String idPartita){
        Log.d(TAG, "aggiungiIDPartita");
        dbPartite = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("partite");

        dbPartite.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    if (keyNode.getKey().toString().equals(idPartita)) {
                        Partita p = (Partita) keyNode.getValue(Partita.class);
                        p.setAttiva(false);
                        dbPartite.child(idPartita).setValue(p);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
