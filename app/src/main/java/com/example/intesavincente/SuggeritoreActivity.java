package com.example.intesavincente;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SuggeritoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggeritore);
    }
    public void onResponse(String parola) {
        findViewById(R.id.parolaDaIndovinare);
    }
}