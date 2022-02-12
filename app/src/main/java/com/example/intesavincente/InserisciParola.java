package com.example.intesavincente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.intesavincente.model.Partita;
import com.example.intesavincente.repository.partita.PartitaRepository;
import com.example.intesavincente.repository.partita.PartitaResponse;
import com.example.intesavincente.utils.Constants;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InserisciParola extends AppCompatActivity {
    private EditText parolaInserita;
    private Partita partita= new Partita();
    private SharedPreferences prefParola = MyApplication.getAppContext().getSharedPreferences("MyPrefSuggeritore", MODE_PRIVATE);
    private SharedPreferences.Editor editorParola = prefParola.edit();
    private String parola=null;
    private String partitaID=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserisci_parola);
        parolaInserita = findViewById(R.id.editText_InserisciParola);
        Button conferma= findViewById(R.id.button_conferma);
        //PartitaRepository mPartitaRepository = new PartitaRepository(this.getApplication(), this);
        //mPartitaRepository.trovaPartita();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            parola = extras.getString("parola");
            partitaID = extras.getString("partita");
            System.out.println("partitaid66" + partitaID);
            System.out.println("parolaInserita" + parolaInserita.getText().toString());
            System.out.println("parolaDB" + parola);
            System.out.println("parolaDB tipo" + parola.getClass());
        }
        conferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("parolaInserita1" + parolaInserita.getText().toString());
                System.out.println("parolaDB1" + parola);
                    if(parolaInserita.getText().toString().equals(parola)){
                        Snackbar parolaIndovinata = Snackbar.make(v, "PAROLA INDOVINATA!", Snackbar.LENGTH_SHORT);
                        parolaIndovinata.show();
                        //String partitaId= prefParola.getString("idpartita1", null);
                        System.out.println("partitaid33"+partitaID);
                        incrementaParole(partitaID);

                        finish();
                    }
                    else{
                        Snackbar parolaSbagliata = Snackbar.make(v, "PAROLA SBAGLIATA!", Snackbar.LENGTH_SHORT);
                        parolaSbagliata.show();
                        //da effettuare decremento parole indovinate
                        finish();
                    }


            }
        });


    }

    public void incrementaParole(String partitaid){
        DatabaseReference db = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("partite");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    if (keyNode.child("idPartita").getValue().equals(partitaid)){
                        int numero=keyNode.child("parole_indovinate").getValue(Integer.class);
                        System.out.println("numero parole"+numero);
                        numero++;
                        db.child(keyNode.getKey()).child("parole_indovinate").setValue(numero);
                    }

                }}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    /*public boolean parolaIndovinata(){
        public void onResponse(String parola) {
            if (parola.equals(parolaInserita)) {
                findViewById()
            }
    }
}*/

}