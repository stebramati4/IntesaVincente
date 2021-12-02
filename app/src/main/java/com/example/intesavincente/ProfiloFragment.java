package com.example.intesavincente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ProfiloFragment extends Fragment {

    private static final String TAG = "ProfiloFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profilo, container, false);

        Button buttonGiocaSquadre = v.findViewById(R.id.button_giocaSquadre);
        buttonGiocaSquadre.setOnClickListener(view -> {
            Navigation.findNavController(v).navigate(R.id.action_profilo_menu_to_creaUniscitiFragment2);
        });

        Button buttonAllenamento = v.findViewById(R.id.button_allenamento);
        buttonAllenamento.setOnClickListener(view -> {
            Navigation.findNavController(v).navigate(R.id.action_profilo_menu_to_creaUniscitiFragment2);
        });

        return v;
    }
}