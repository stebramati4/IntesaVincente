package com.example.intesavincente.repository.partita;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.intesavincente.model.Gruppo;
import com.example.intesavincente.model.Partita;
import com.example.intesavincente.model.Utente;
import com.example.intesavincente.repository.user.UserRepository;
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
    DatabaseReference dbPartite;
    Button creaGruppoButton;
    EditText nomeGruppo;
    Snackbar snackbarCreaGruppo;
    UserRepository mUserRepository;

    public void inserisciGruppoInPartita(String gruppoID) {
        Partita p=new Partita(gruppoID);
        dbPartite = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("partite");
        String TAG ="CreaGruppoFragment" ;
        String partitaID=dbPartite.push().getKey();
        dbPartite.child(partitaID).setValue(p);
        mUserRepository.aggiungiIDPartita(partitaID);
    }
}
