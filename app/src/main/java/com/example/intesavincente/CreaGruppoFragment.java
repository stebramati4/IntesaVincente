package com.example.intesavincente;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.intesavincente.MODEL.Gruppo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
            addGroup();
            //cambia activity
        });

        return v;
    }

    //crea il gruppo e lo aggiunge al database
    private void addGroup() {
        String nome = nomeGruppo.getText().toString();

        if(!TextUtils.isEmpty(nome)){
            String gruppoID = db.push().getKey();
            Gruppo gruppo = new Gruppo(nome);
            db.child("gruppi").child(gruppoID).setValue(gruppo);

        }
        else{
           // Toast.makeText(this, "Devi inserire un nome", Toast.LENGTH_LONG).show();
        }

    }
    // Write a message to the database

   /* FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = mdatabase.getReference("message");
    public void writeNewUser(String gruppoId, String nomeGruppo) {


        mDatabase.child("users").child(userId).setValue(user);
    }*/
}