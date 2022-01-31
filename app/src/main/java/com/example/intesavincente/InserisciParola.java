package com.example.intesavincente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.intesavincente.model.Partita;

import java.util.List;

public class InserisciParola extends AppCompatActivity {
    EditText parolaInserita;
    Partita partita= new Partita();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserisci_parola);
        parolaInserita = findViewById(R.id.editText_InserisciParola);
    }
    /*public boolean parolaIndovinata(){
        public void onResponse(String parola) {
            if (parola.equals(parolaInserita)) {
                findViewById()
            }
    }
}*/

}