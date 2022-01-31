package com.example.intesavincente.repository.gruppo;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.example.intesavincente.R;
import com.example.intesavincente.model.Gruppo;
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

public class GruppoRepository {
    DatabaseReference db1;
    Button creaGruppoButton;
    EditText nomeGruppo;
    Snackbar snackbarCreaGruppo;

    public void inserisciGruppo(String gruppoID,String nome) {
        db1 = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("utenti");
        DatabaseReference db = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference();
        String TAG ="CreaGruppoFragment" ;
        db1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //arrayGruppi.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    if (keyNode.child("idUtente").getValue().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        keys.add(keyNode.getKey());
                        Utente utente = (Utente) keyNode.getValue(Utente.class);
                        //Log.d(TAG, " utente " + keyNode.getValue(Utente.class));
                        Log.d(TAG, "Utente stampa query if " + keyNode.child("idUtente"));
                        Log.d(TAG, "Utente chiave  if " + keyNode.getKey());
                        Log.d(TAG, "Utente nome if" + keyNode.child("nickname").getValue());
                        Log.d(TAG, "Utente indovinatore if" + keyNode.child("indovinatore").getValue());
                        Log.d(TAG, "Utente stampa query if " + keyNode.child("gruppi").child("utenti"));
                        Log.d(TAG, " gruppo id" + gruppoID);
                        Log.d(TAG, " nome" + nome);
                        Log.d(TAG, " utente " + utente.toString1());

                        Gruppo gruppo = new Gruppo(gruppoID, nome, utente);
                        Log.d(TAG, "componenti del gruppo ");
                        //gruppo.setComponenti(utente);
                        Log.d(TAG, "componenti del gruppo ");
                        Log.d(TAG, "componenti del gruppo " + gruppo.stampaLista());              //Non è più gruppo.getComponenti().stampa()
                        //db1.child("gruppi").child(gruppoID).setValue(utente);

                        //System.out.println("gruppoid " + db.push().getKey());
                        db.child("gruppi").child(gruppoID).setValue(gruppo);


                        //snackbarCreaGruppo = Snackbar.make(v, "GRUPPO " + gruppo.getNome() + " CREATO", Snackbar.LENGTH_SHORT);
                       // snackbarCreaGruppo.show();
                        //Navigation.findNavController(v).navigate(R.id.action_creaGruppoFragment_to_scegliRuoloFragment);

                    }

                }


                //Utente u=new Utente(email,false,FirebaseAuth.getInstance().getCurrentUser().getUid());
                // db.child("gruppi").child(gruppoID).child("utenti").setValue(utente);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }






}