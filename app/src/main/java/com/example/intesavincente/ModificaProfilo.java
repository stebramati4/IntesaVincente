package com.example.intesavincente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.intesavincente.ui.AuthenticationActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ModificaProfilo extends AppCompatActivity {

    private static final String TAG ="ModificaProfilo";

    Button mButtonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_profilo);

        mButtonLogout = findViewById(R.id.logout_button);

        mButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // disconnessione dell'utente
                FirebaseAuth.getInstance().signOut();

                // definisco l'intenzione
                Intent backAutenticazione = new Intent(ModificaProfilo.this, AuthenticationActivity.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(backAutenticazione);
            }
        });

    }

}