package com.example.intesavincente;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.intesavincente.model.Partita;
import com.example.intesavincente.repository.partita.PartitaRepository;
import com.example.intesavincente.repository.partita.PartitaResponse;
import com.example.intesavincente.repository.words.IWordsRepository;
import com.example.intesavincente.repository.words.WordsRepository;
import com.example.intesavincente.utils.ResponseCallback;

public class IndovinatoreRepository implements ResponseCallback, PartitaResponse, IIndovinatore {
    private Application mApplication;
    private String parola;
    private IWordsRepository mIWordsRepository;
    private PartitaRepository mPartitaRepository;
    private IndovinatoreResponse mIndovinatoreResponse;
    private IIndovinatore mIIndovinatoreRepository;
    private Partita partita=new Partita();
    public Activity activity;

    public IndovinatoreRepository(Application mApplication, IndovinatoreResponse mIndovinatoreResponse) {
        this.mApplication = mApplication;
        this.mIndovinatoreResponse = mIndovinatoreResponse;
    }
    public IndovinatoreRepository(Application mApplication) {
        this.mApplication = mApplication;
    }

    //public IndovinatoreRepository(Activity activity){
      //  this.activity=activity;
    //}
    //public IndovinatoreRepository(){

    //}
    public void prendiParola(){
        mIWordsRepository = new WordsRepository((Application) MyApplication.getAppContext(), this);
    }
    public void prendiPartita(){
        mPartitaRepository = new PartitaRepository((Application) MyApplication.getAppContext(), this);
        mPartitaRepository.trovaPartita();
    }




    @Override
    public void salva() {
        //prendiParola();
        mIWordsRepository.fetchWords();
    }

    @Override
    public Partita onDataFound(Partita partitaTrue) {
        System.out.println("par1221"+partitaTrue);
        this.partita=partitaTrue;
        return partitaTrue;
    }

    @Override
    public void onResponse(String parola) {
        System.out.println("par223 "+parola);
        mIndovinatoreResponse.saveParola(parola);
    }

    @Override
    public void onFailure(String errorMessage) {

    }
}
