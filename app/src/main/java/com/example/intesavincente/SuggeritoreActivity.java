package com.example.intesavincente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.SharedPreferences;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.widget.TextView;

import com.example.intesavincente.model.Partita;
import com.example.intesavincente.repository.partita.PartitaRepository;
import com.example.intesavincente.repository.partita.PartitaResponse;
import com.example.intesavincente.repository.words.IWordsRepository;
import com.example.intesavincente.repository.words.WordsRepository;
import com.example.intesavincente.utils.Constants;
import com.example.intesavincente.utils.ResponseCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SuggeritoreActivity extends AppCompatActivity implements PartitaResponse {


    private IWordsRepository mIWordsRepository;
    private IIndovinatore mIIndovinatore;
    private PartitaRepository mPartitaRepository;
    SharedPreferences prefSuggeritore = MyApplication.getAppContext().getSharedPreferences("MyPrefSuggeritore", MODE_PRIVATE);
    SharedPreferences.Editor editorSuggeritore = prefSuggeritore.edit();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggeritore);
        System.out.println("prova1");
        TextView parolaDaIndovinare;
        parolaDaIndovinare=findViewById(R.id.parolaDaIndovinare);
        mPartitaRepository = new PartitaRepository(this.getApplication(), this);
        mPartitaRepository.trovaPartita();

        String idPartita=prefSuggeritore.getString("idpartita1", null);
        System.out.println("idpartita2"+idPartita);
        DatabaseReference db = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("partite");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    if (keyNode.child("idPartita").getValue().equals(idPartita)){

                        String par= keyNode.child("parola").getValue().toString();
                        System.out.println("partitaIf"+par);
                        parolaDaIndovinare.setText(par);
                    }

                }}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onDataFound(Partita partita) {
        System.out.println("idPartita12"+partita.getIdPartita());
        editorSuggeritore.putString("idpartita1", partita.getIdPartita());
        editorSuggeritore.apply();
    }
}