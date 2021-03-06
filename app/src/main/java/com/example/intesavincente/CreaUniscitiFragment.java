package com.example.intesavincente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CreaUniscitiFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crea_unisciti, container, false);

        Button creaGruppo = v.findViewById(R.id.crea_gruppo_button);
        creaGruppo.setOnClickListener(view -> {
            Navigation.findNavController(v).navigate(R.id.action_creaUniscitiFragment2_to_creaGruppoFragment);
        });

        Button unisciti = v.findViewById(R.id.unisciti_button);
        unisciti.setOnClickListener(view -> {
            Navigation.findNavController(v).navigate(R.id.action_creaUniscitiFragment2_to_listaGruppiFragment);
        });
        return v;
    }
}