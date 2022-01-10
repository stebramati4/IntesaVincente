package com.example.intesavincente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //FirebaseDatabase database = FirebaseDatabase.getInstance("https://intesavincente-e720b-default-rtdb.europe-west1.firebasedatabase.app/");
        //DatabaseReference myRef = database.getReference("message");
        //myRef.setValue("Hello, World!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.NavigationFragmentContainerView);
        NavController navController = navHostFragment.getNavController();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        Toolbar toolbar = findViewById(R.id.toolbar_logo);

        ImageButton imageButtonSettings = findViewById(R.id.tastoSettings);
        imageButtonSettings.setOnClickListener(view -> {
            Log.d(TAG, "Bottone IMPOSTAZIONI premuto");
        });

    ImageButton tasto= (ImageButton) findViewById(R.id.tastoSettings);
        tasto.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View arg0) {
            // definisco l'intenzione
            Intent modificaProfilo = new Intent(MainActivity.this,modificaProfilo.class);
            // passo all'attivazione dell'activity Pagina.java
            startActivity(modificaProfilo);
        }
    });
        ImageButton crea= (ImageButton) findViewById(R.id.tastoSettings);
        tasto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                // definisco l'intenzione
                Intent modificaProfilo = new Intent(MainActivity.this,modificaProfilo.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(modificaProfilo);
            }
        });

    }
}