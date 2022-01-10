package com.example.intesavincente.MODEL;

import java.util.ArrayList;
import java.util.List;

public class Gruppo {
    private String ID;
    private String nome;
    private ArrayList<Utente> componenti = new ArrayList<Utente>();

    public Gruppo(){

    }

    public Gruppo(String nome) {
        this.nome = nome;
    }

    public Gruppo(String ID, String  nome, Utente utente) {
        this.ID=ID;
        this.nome=nome;
        componenti.add(utente);
    }

    public Gruppo(String ID, String  nome, ArrayList<Utente> componenti) {
        this.ID=ID;
        this.nome=nome;
        this.componenti = componenti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Utente> getComponenti() {
        return componenti;
    }

    public void setComponenti(Utente utente) {

    }

    //private boolean hasIndovinatore(Utente[] componenti){
    // int indovinatore = 0;
    // for(int i = 0; i<componenti.length; i++){
    // controlla se utente ha scelto indovinatore
    //if ha scelto indovinatore indovinatore++
    // if indovinatore == 1 return true
    //else retunrn false

    @Override
    public String toString() {
        return "Gruppo{" +
                "nome='" + nome + '\'' +
                ", componenti=" + stampaLista()+
                '}';
    }

    public String stampaLista(){     //Modificato in modo tale che prenda già i componenti del gruppo
        ArrayList<Utente> componenti = getComponenti();
        String stampa = "";
        for(int i=0;i<componenti.size();i++){
            if(componenti.get(i) != null){
                if(i == 0)
                    stampa = componenti.get(i).toString1();
                else
                    stampa = stampa + ", " + componenti.get(i).toString1();
            }
            else
                return stampa;
        }
        return stampa;
    }

}

