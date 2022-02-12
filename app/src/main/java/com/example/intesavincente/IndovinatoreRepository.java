package com.example.intesavincente;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.intesavincente.model.Partita;
import com.example.intesavincente.repository.partita.PartitaRepository;
import com.example.intesavincente.repository.partita.PartitaResponse;
import com.example.intesavincente.repository.words.IWordsRepository;
import com.example.intesavincente.repository.words.WordsRepository;
import com.example.intesavincente.utils.Constants;
import com.example.intesavincente.utils.ResponseCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class IndovinatoreRepository implements ResponseCallback, PartitaResponse, IIndovinatore {
    private Application mApplication;
    private String parola;
    private IWordsRepository mIWordsRepository;
    private PartitaRepository mPartitaRepository;
    public static IndovinatoreResponse mIndovinatoreResponse;
    private IIndovinatore mIIndovinatoreRepository;
    private static Partita partita=new Partita();
    public Activity activity;

    public IndovinatoreRepository(Application mApplication, IndovinatoreResponse mIndovinatoreResponse) {
        this.mApplication = mApplication;
        this.mIndovinatoreResponse = mIndovinatoreResponse;
    }
    public IndovinatoreRepository(Application mApplication) {
        this.mApplication = mApplication;
    }

    public IndovinatoreRepository(Activity activity){
        this.activity=activity;
    }
    //public IndovinatoreRepository(){

    //}
    interface Listener {
        public void takePartita(Partita partita);
    }

    private Listener mListener;

    public void setListener(Listener listener) {
        mListener = listener;
    }
    public void prendiParola(){
        mIWordsRepository = new WordsRepository((Application) MyApplication.getAppContext(), this);
        //mIWordsRepository = new WordsRepository(activity.getApplication(), this);
        //mIndovinatoreResponse.saveParola("pippo");
        System.out.println("indovinatoreResp");
        mIWordsRepository.fetchWords();
    }

    public void prendiPartita(){
        mPartitaRepository = new PartitaRepository((Application) MyApplication.getAppContext(), this);
        mPartitaRepository.trovaPartita();
    }





    @Override
    public void salva() {
        //prendiParola();
        System.out.println("salvaMethod");
        mIWordsRepository = new WordsRepository((Application) MyApplication.getAppContext(), this);
        mIWordsRepository.fetchWords();
        System.out.println("salvaMethod1");
    }

    @Override
    public void onDataFound(Partita partitaTrue) {
        System.out.println("partita12213"+partitaTrue);
        mListener.takePartita(partitaTrue);
        this.partita=partitaTrue;
    }

    @Override
    public void onResponse(String parola) {
        System.out.println("parolaApi"+parola);

        System.out.println("indovinatoreResp1");
        //TextView parolaDaIndovinare = findViewById(R.id.parolaDaIndovinare);
        //parolaDaIndovinare.setText(parola);
        //new IndovinatoreRepository((Application) MyApplication.getAppContext(), this.mIndovinatoreResponse);


    }

    @Override
    public void onFailure(String errorMessage) {

    }
}
