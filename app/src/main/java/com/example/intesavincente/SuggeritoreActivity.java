package com.example.intesavincente;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.intesavincente.utils.ResponseCallback;

public class SuggeritoreActivity extends AppCompatActivity implements ResponseCallback {

    TextView parolaDaIndovinare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggeritore);
    }

    @Override
    public void onResponse(String parola) {
        System.out.println("Prova " + parola);
        parolaDaIndovinare = findViewById(R.id.parolaDaIndovinare);
        parolaDaIndovinare.setText(parola);
    }

    @Override
    public void onFailure(String errorMessage) {

    }
}