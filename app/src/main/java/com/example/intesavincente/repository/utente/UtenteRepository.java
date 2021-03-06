package com.example.intesavincente.repository.utente;

import android.util.Log;

import com.example.intesavincente.model.Utente;
import com.example.intesavincente.utils.Constants;
import com.example.intesavincente.utils.FirebaseCallback;
import com.example.intesavincente.utils.ResponseCallback;
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
    private DatabaseReference dbGruppi;
    private ArrayList<String> listaNomi = new ArrayList<String>();


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
                        //keyNode.child("partite").child(String.valueOf(new int[1])).getRef().setValue(partitaID);
                        //Utente utente = (Utente) keyNode.getValue(Utente.class);
                        Log.d(TAG, "tipo Partite" + keyNode.child("partite").getValue().getClass());
                        String utenteID= FirebaseAuth.getInstance().getCurrentUser().getUid();
                        Log.d(TAG, "tipo Partite" + keyNode.child("idUtente").getClass());
                        String nickname= (String) keyNode.child("nickname").getValue();
                        String mail= (String)keyNode.child("mail").getValue();
                        String password= (String) keyNode.child("password").getValue();
                        ArrayList <String> partite=new ArrayList<>();
                        Log.d(TAG, "fuori if22" );
                        partite= (ArrayList<String>) keyNode.child("partite").getValue();
                        Log.d(TAG, "fuori if223" );
                        Utente u = new Utente(utenteID, nickname, mail, password);
                        Log.d(TAG, "fuori if224" );
                        if(partite.size()==1&&partite.get(0).equals("prova")){
                            Log.d(TAG, "dentro if22" );
                            partite.remove(0);
                            partite.add(0,partitaID);
                            u.setPartite(partite);
                        }
                        else{
                            Log.d(TAG, "dentro if223" );
                            u.setPartite(partite);
                            u.aggiungiPartita(partitaID);
                            }


                        Log.d(TAG, "valori utente"+ u.toString1());
                        Log.d(TAG, "valori utente"+ u.getPartite());
                        //String idPartita= keyNode.child("partite").child(String.valueOf()).getValue())
                        //utente.setPartite(partitaID);
                        String idUtente = keyNode.getKey().toString();
                        System.out.println(idUtente);
                        dbUtenti.child(idUtente).setValue(u);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getListaUtenti(FirebaseCallback callback){
        dbUtenti = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("utenti");
        dbGruppi = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("gruppi");
        ArrayList<String> componenti = new ArrayList<String>();
        dbGruppi.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    componenti.clear();

                    for (int i = 0; i < 3; i++) {
                        if (keyNode.child("componenti").child(String.valueOf(i)).getValue() != null) {
                            String componente = keyNode.child("componenti").child(String.valueOf(i)).getValue().toString();
                            componenti.add(componente);

                        }
                    }
                        Log.d(TAG, "componenti11"+componenti.toString()+" "+componenti.size());
                        dbUtenti.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                listaNomi.clear();
                                List<String> keys = new ArrayList<>();
                                for (DataSnapshot keyNode : snapshot.getChildren()) {
                                    keys.add(keyNode.getKey());
                                    for (int i = 0; i < componenti.size(); i++) {
                                        Log.d(TAG, "Dentro for idComponenti");
                                        if (keyNode.child("idUtente").getValue().equals(componenti.get(i))) {
                                            Log.d(TAG, "Dentro if idComponenti " + componenti.get(i));
                                            //Utente utente = (Utente) keyNode.getValue(Utente.class);
                                            String nomeUtente = (String) keyNode.child("nickname").getValue();
                                            Log.d(TAG, "Nome utente " + nomeUtente);
                                            listaNomi.add(nomeUtente);

                                            Log.d(TAG, "Lista nomi " + listaNomi.toString());
                                            callback.onResponse(listaNomi);
                                        }
                                    }

                                    Log.d(TAG, "Lista nomiOn " + listaNomi.toString());
                                }

                            }


                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        Log.d(TAG, "Lista nomiFuori1" + listaNomi.toString());
                    }
                Log.d(TAG, "Lista nomiFuori" + listaNomi.toString());

                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
