package com.example.intesavincente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
        View v = inflater.inflate(R.layout.fragment_profilo, container, false);

        Button creaGruppo = v.findViewById(R.id.crea_gruppo_button);
        creaGruppo.setOnClickListener(view -> {
            CreaCreaGruppoFragment creaGruppoFragment = new CreaCreaGruppoFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.NavigationFragmentContainerView, CreaCreaGruppoFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
        return v;
    }
}