package com.example.intesavincente.MODEL;

import java.util.Arrays;

public class Gruppo {
    public String nome;
    public Utente[] componenti;
    public String ciao;

    public Gruppo(String nome, Utente[] componenti) {
        this.nome = nome;
        this.componenti = componenti;
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

    @Override
    public String toString() {
        return "Gruppo{" +
                "nome='" + nome + '\'' +
                ", componenti=" + Arrays.toString(componenti) +
                '}';
    }
}
