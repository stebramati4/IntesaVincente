package com.example.intesavincente;

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

import com.example.intesavincente.model.Gruppo;
import com.example.intesavincente.model.Utente;
import com.example.intesavincente.repository.gruppo.GruppoRepository;
import com.example.intesavincente.repository.partita.PartitaRepository;
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
    Snackbar snackbarCreaGruppo;

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
            Log.d(TAG,"fuori chiamata1");
            GruppoRepository g= new GruppoRepository();
            g.inserisciGruppo(gruppoID,nome);
            Log.d(TAG,"fuori chiamata2");
            snackbarCreaGruppo = Snackbar.make(v, "GRUPPO " + nome + " CREATO", Snackbar.LENGTH_SHORT);
            snackbarCreaGruppo.show();

            PartitaRepository p =new PartitaRepository();
            p.inserisciGruppoInPartita(gruppoID);
            Log.d(TAG,"fuori chiamata3");
            p.inserisciPartitaInUtente(gruppoID);
            Log.d(TAG,"fuori chiamata4");
            Navigation.findNavController(v).navigate(R.id.action_creaGruppoFragment_to_scegliRuoloFragment);


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

    // piccola modifica inutile 2