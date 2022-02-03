package com.example.intesavincente.repository.utente;

import android.util.Log;

import com.example.intesavincente.model.Utente;
import com.example.intesavincente.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class UtenteRepository {

    private static final String TAG = "UtenteRepository";

    private DatabaseReference db;
    private DatabaseReference dbUtenti;


    public void aggiungiIDPartita(String partitaID){
        Log.d(TAG, "aggiungiIDPartita");
        dbUtenti = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("utenti");
        db = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference();

        dbUtenti.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    if (keyNode.child("idUtente").getValue().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        Utente utente = (Utente) keyNode.getValue(Utente.class);
                        utente.setPartite(partitaID);
                        String idUtente = keyNode.getKey().toString();
                        System.out.println(idUtente);
                        dbUtenti.child(idUtente).setValue(utente);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
