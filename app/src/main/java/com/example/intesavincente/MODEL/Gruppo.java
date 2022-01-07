package com.example.intesavincente.MODEL;

import java.util.Arrays;

public class Gruppo {
    private String ID;
    private String nome;
    private Utente[] componenti;

    public Gruppo(){

    }

    public Gruppo(String nome) {
        this.nome = nome;
    }

    public Gruppo(String ID, String  nome, Utente[] componenti) {
        this.ID=ID;
        this.nome=nome;
        this.componenti=componenti;
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

    public void setComponenti(Utente[] componenti) {
        this.componenti = componenti;
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
                ", componenti=" + Arrays.toString(componenti) +
                '}';
    }


}

