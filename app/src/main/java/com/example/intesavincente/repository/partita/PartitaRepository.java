package com.example.intesavincente.repository.partita;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.intesavincente.model.Gruppo;
import com.example.intesavincente.model.Partita;
import com.example.intesavincente.model.Utente;
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
    DatabaseReference db1;
    Button creaGruppoButton;
    EditText nomeGruppo;
    Snackbar snackbarCreaGruppo;

    public void inserisciGruppoInPartita(String gruppoID) {
        Partita p=new Partita(gruppoID);
        db1 = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference();
        String TAG ="CreaGruppoFragment" ;
        String partitaID=db1.push().getKey();
        db1.child("partite").child(partitaID).setValue(p);
    }
}
