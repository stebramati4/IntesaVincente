package com.example.intesavincente.MODEL;

import java.util.Arrays;

public class Gruppo {
    private String ID;
    private String nome;
    private Utente[] componenti=new Utente[3];

    public Gruppo(){

    }

    public Gruppo(String nome) {

        this.nome = nome;
    }

    public Gruppo(String ID, String  nome, Utente u) {
        this.ID=ID;
        this.nome=nome;
        for(int i=0;i<3;i++) {
            if (componenti[i] != null)
                componenti[i] = u;
            else
                componenti[i] = null;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Utente[] getComponenti() {

        return componenti;
    }

    public void setComponenti(Utente utente) {
        for(int i=0;i<3;i++){
            componenti[i]=utente;
        }
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
                ", componenti=" + Arrays.toString(componenti)+
                '}';
    }

    public String stampa(){     //Modificato in modo tale che prenda già i componenti del gruppo
        Utente[] componenti = getComponenti();
        String stampa = "";
        for(int i=0;i<3;i++){
            if(componenti[i] != null){
                if(i == 0)
                    stampa = componenti[i].toString1();
                else
                    stampa = stampa + ", " + componenti[i].toString1();
            }
            else
                return stampa;
        }
        return stampa;
    }

}

