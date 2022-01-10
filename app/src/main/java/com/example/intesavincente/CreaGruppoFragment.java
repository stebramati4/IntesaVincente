package com.example.intesavincente;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.intesavincente.MODEL.Gruppo;
import com.example.intesavincente.MODEL.Utente;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreaGruppoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreaGruppoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG ="CreaGruppoFragment" ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreaGruppoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreaGruppoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreaGruppoFragment newInstance(String param1, String param2) {
        CreaGruppoFragment fragment = new CreaGruppoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    Button creaGruppoButton;
    EditText nomeGruppo;

    DatabaseReference db;
    DatabaseReference db1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_crea_gruppo, container, false);

        creaGruppoButton = v.findViewById(R.id.crea);
        nomeGruppo = v.findViewById(R.id.campoNick);

        db = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference();

        creaGruppoButton.setOnClickListener(view -> {
           // addGroup();
            //cambia activity





    //crea il gruppo e lo aggiunge al database
    //private void addGroup() {
        db = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference();
        String nome = nomeGruppo.getText().toString();

        if(!TextUtils.isEmpty(nome)) {
            String gruppoID = db.push().getKey();

            //FirebaseAuth.getInstance().getCurrentUser()
            // String email=FirebaseAuth.getInstance().getCurrentUser().getEmail();

            //String uID=FirebaseAuth.getInstance().getCurrentUser().getUid();

            db1 = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("utenti");

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

                            Log.d(TAG, "Utente stampa query if " + keyNode.child("idUtente"));
                            Log.d(TAG, "Utente chiave  if " + keyNode.getKey());
                            Log.d(TAG, "Utente nome if" + keyNode.child("nickname").getValue());
                            Log.d(TAG, "Utente indovinatore if" + keyNode.child("indovinatore").getValue());
                            Log.d(TAG, "Utente stampa query if " + keyNode.child("gruppi").child("utenti"));
                            Log.d(TAG, " gruppo id"+gruppoID);
                            Log.d(TAG, " nome"+nome);
                            Gruppo gruppo = new Gruppo(gruppoID,nome,utente);
                            Log.d(TAG, "componenti del gruppo ");
                            //gruppo.setComponenti(utente);
                            Log.d(TAG, "componenti del gruppo ");
                            Log.d(TAG, "componenti del gruppo " +gruppo.stampa());              //Non è più gruppo.getComponenti().stampa()
                            //db1.child("gruppi").child(gruppoID).setValue(utente);


                            //System.out.println("gruppoid " + db.push().getKey());
                            db.child("gruppi").child(gruppoID).setValue(gruppo);
                        } else {
                            // Toast.makeText(this, "Devi inserire un nome", Toast.LENGTH_LONG).show();
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
    });
        return v;
}


}

    // Write a message to the database

   /* FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = mdatabase.getReference("message");
    public void writeNewUser(String gruppoId, String nomeGruppo) {


        mDatabase.child("users").child(userId).setValue(user);
    }*/

