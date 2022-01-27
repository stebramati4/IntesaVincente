package com.example.intesavincente;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.intesavincente.MODEL.Gruppo;
import com.example.intesavincente.MODEL.Utente;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ScegliRuoloFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private static final String TAG ="ScegliRuoloFragment" ;
    Button avantiButton;
    DatabaseReference db;
    DatabaseReference dbGruppi;
    DatabaseReference dbUtenti;
    Button indovinatore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_scegli_ruolo, container, false);
       avantiButton = v.findViewById(R.id.AvantiButton);

       //indovinatore=v.findViewById(R.id.radioGroup2.getCheckedRadioButtonId());
        //RadioButton checkedRadioButton = (RadioButton)v.findViewById(R.id.radioGroup2.getCheckedRadioButtonId());
        //RadioButton checkedRadioButton = (RadioButton)v.findViewById(radioGroup2.getCheckedRadioButtonId());
        RadioButton radioButton;
        RadioGroup radioGroup2 = (RadioGroup) v.findViewById(R.id.radioGroup2);
        Button btnDisplay = (Button) v.findViewById(R.id.AvantiButton);
        Log.d(TAG, "radiogroup" + radioGroup2);
        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGroup2.getCheckedRadioButtonId();
                Log.d(TAG, "selectedID" + selectedId);
                if(selectedId==2131230727){
                    Log.d(TAG, "indovinatore" + selectedId);
                    db = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference();
                    dbGruppi = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("gruppi");
                    dbGruppi.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            List<String> keysGruppi = new ArrayList<>();
                            for (DataSnapshot keyNode : snapshot.getChildren()) {
                                Log.d(TAG, "KeyNode " + keyNode);
                                keysGruppi.add(keyNode.getKey());
                                if (keyNode.child("id").getValue().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                                    keysGruppi.add(keyNode.getKey());
                                    Gruppo gruppo = (Gruppo) keyNode.getValue(Gruppo.class);
                                    Log.d(TAG, "gruppo " + gruppo);
                                    Log.d(TAG, "Reference " + dbGruppi.getRef());
                                    dbGruppi.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            ArrayList<Utente> listaComponenti = new ArrayList<Utente>();
                                            List<String> keysGruppi = new ArrayList<>();
                                            for (DataSnapshot keyNodeGruppi : dataSnapshot.getChildren()) {
                                                keysGruppi.add(keyNodeGruppi.getKey());
                                                Log.d(TAG, "KeyNode " + keyNodeGruppi);
                                                Log.d(TAG, "Class KeyNode " + keyNodeGruppi.getValue().getClass());
                                                Log.d(TAG, "GruppoID " + keyNodeGruppi.child("componenti").getValue().getClass());
                                                String gruppoID=keyNode.getKey();
                                                if(gruppo.getID().equals(keyNodeGruppi.getKey())){
                                                    Boolean isInserito = false;

                                                    for (int i = 0; i < 3; i++) {
                                                        if (keyNodeGruppi.child("componenti").child(String.valueOf(i)).getValue(Utente.class) != null) {
                                                            Utente componente = keyNodeGruppi.child("componenti").child(String.valueOf(i)).getValue(Utente.class);
                                                            //listaComponenti.add(componente);

                                                        }
                                                    }

                                                    for(int i = 0; i < listaComponenti.size(); i++){
                                                        String numero= String.valueOf(i);
                                                        Log.d(TAG, "Componente123 " + listaComponenti.get(i).getNickname());
                                                        if(listaComponenti.get(i).idUtente.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                                                            db.child("gruppi").child(gruppoID).child(numero).child("idUtente").setValue(false);
                                                            Log.d(TAG, "Componente1234 " + listaComponenti.get(i).getNickname());
                                                        }


                                                    }

                                                }
                                                Log.d(TAG, "GruppoID " + listaComponenti);
                                            }


                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                } else {
                                    // Toast.makeText(this, "Devi inserire un nome", Toast.LENGTH_LONG).show();
                                }

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }


            }

        });




       return v;
    }
}