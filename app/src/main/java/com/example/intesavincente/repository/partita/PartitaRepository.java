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

    private DatabaseReference dbPartite;
    private Button creaGruppoButton;
    private EditText nomeGruppo;
    private Snackbar snackbarCreaGruppo;
    private UtenteRepository mUtenteRepository = new UtenteRepository();

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
                        keysPartite.add(keyNode.getKey());
                        String chiave=keyNode.getKey();
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
                                        for (int i = 0; i < 3; i++) {
                                            if (keyNode.child("partite").child(String.valueOf(i)).getValue(String.class) == null) {
                                                Log.d(TAG, "partiteid " + keyNode.child("partite").child(String.valueOf(i)).getValue(String.class));
                                                keyNode.child("partite").child(String.valueOf(i)).getRef().setValue(chiave);
                                                break;
                                            }
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
