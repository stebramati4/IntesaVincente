package com.example.intesavincente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
            CreaUniscitiFragment creaUniscitiFragment = new CreaUniscitiFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.NavigationFragmentContainerView, creaUniscitiFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        Button buttonAllenamento = v.findViewById(R.id.button_allenamento);
        buttonAllenamento.setOnClickListener(view -> {
            CreaUniscitiFragment creaUniscitiFragment = new CreaUniscitiFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.NavigationFragmentContainerView, creaUniscitiFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        return v;
    }
}