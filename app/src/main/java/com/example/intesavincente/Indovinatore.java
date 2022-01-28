package com.example.intesavincente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.intesavincente.repository.words.IWordsRepository;
import com.example.intesavincente.repository.words.WordsRepository;
import com.example.intesavincente.utils.ResponseCallback;

public class Indovinatore extends AppCompatActivity implements ResponseCallback {

    private String parola;
    private IWordsRepository mIWordsRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indovinatore);

        mIWordsRepository = new WordsRepository(this.getApplication(), this);
        TextView parolaDaIndovinare = findViewById(R.id.parolaDaIndovinare);
        Button buzz = findViewById(R.id.buzz);
        buzz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIWordsRepository.fetchWords();
                //parolaDaIndovinare.setText();
            }
        });
    }

    public Indovinatore(){}

    @Override
    public void onResponse(String parola) {

    }

    @Override
    public void onFailure(String errorMessage) {

    }
}
