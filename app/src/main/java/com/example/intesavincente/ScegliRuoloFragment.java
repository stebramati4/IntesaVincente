package com.example.intesavincente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.intesavincente.model.Partita;
import com.example.intesavincente.repository.partita.PartitaRepository;
import com.example.intesavincente.repository.partita.PartitaResponse;
import com.google.firebase.database.DatabaseReference;

public class ScegliRuoloFragment extends Fragment implements PartitaResponse {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private static final String TAG ="ScegliRuoloFragment" ;
    private PartitaRepository mPartitaRepository;
    Button avantiButton;
    RadioGroup radioGroup;
    RadioButton radioButton;
    RadioButton r;
    DatabaseReference db;
    DatabaseReference dbGruppi;
    DatabaseReference dbUtenti;
    Button indovinatore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_scegli_ruolo, container, false);

        avantiButton = v.findViewById(R.id.avantiButton);
        radioGroup = v.findViewById(R.id.radioGroupScelta);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                Log.d(TAG, "ID radio button selezionato " + radioButtonID);
                radioButton = v.findViewById(radioButtonID);

                int idx = radioGroup.indexOfChild(radioButton);
                Log.d(TAG, "idx " + idx);

                r = (RadioButton) radioGroup.getChildAt(idx);
                Log.d(TAG, "Selezionato" + r);

                String selectedText = r.getText().toString();


                avantiButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //mPartitaRepository = new PartitaRepository(requireContext(), this);
                        //mPartitaRepository.trovaPartita();
                        if(selectedText.equals("SUGGERITORE")) {
                            Navigation.findNavController(v).navigate(R.id.action_scegliRuoloFragment_to_suggeritoreActivity);
                        }
                        else if(selectedText.equals("INDOVINATORE")) {
                            Navigation.findNavController(v).navigate(R.id.action_scegliRuoloFragment_to_indovinatore);
                        }
                    }
                });
            }
        });

       return v;
    }

    @Override
    public void onDataFound(Partita partita) {
        partita.setAttiva(true);
    }
}