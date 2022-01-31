package com.example.intesavincente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.intesavincente.utils.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfiloFragment extends Fragment {

    private static final String TAG = "ProfiloFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    Button buttonGiocaSquadre;
    Button buttonAllenamento;

    DatabaseReference db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profilo, container, false);

        buttonGiocaSquadre = v.findViewById(R.id.button_giocaSquadre);
        buttonAllenamento = v.findViewById(R.id.button_allenamento);

        db = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference();

        buttonGiocaSquadre.setOnClickListener(view -> {
            Navigation.findNavController(v).navigate(R.id.action_profilo_menu_to_creaUniscitiFragment2);
        });


        buttonAllenamento.setOnClickListener(view -> {
            Navigation.findNavController(v).navigate(R.id.action_profilo_menu_to_creaUniscitiFragment2);
        });

        return v;
    }


}