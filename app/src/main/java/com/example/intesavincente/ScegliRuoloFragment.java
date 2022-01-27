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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScegliRuoloFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private static final String TAG ="ScegliRuoloFragment" ;
    Button avantiButton;
    DatabaseReference db;
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

                    db.child("gruppi").child(gruppoID).child(indovinatore).setValue(false);

                }


                //radioButton = (RadioButton) v.findViewById(selectedId);

            }

        });





     //  avantiButton.setOnClickListener(view -> {
       //    Navigation.findNavController(v).navigate(R.id.);
      // });

       return v;
    }
}