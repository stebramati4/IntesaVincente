package com.example.intesavincente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ScegliRuoloFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    Button avantiButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_scegli_ruolo, container, false);
       avantiButton = v.findViewById(R.id.AvantiButton);
     //  avantiButton.setOnClickListener(view -> {
       //    Navigation.findNavController(v).navigate(R.id.);
      // });

       return v;
    }
}