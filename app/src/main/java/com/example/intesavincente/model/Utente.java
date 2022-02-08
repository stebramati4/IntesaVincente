package com.example.intesavincente.model;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Utente {
    public String nickname;
    public String idUtente;
    private String mail;
    private String password;
    public Statistica statistica;
    private ArrayList<String> partite = new ArrayList<String>();

    public Utente(){

    }

    public Utente(String idUtente,String nickname, String mail, String password) {
        this.idUtente = idUtente;
        this.nickname = nickname;
        this.mail = mail;
        this.password = password;
        this.partite = null;
    }
    public void setId(String idUtente){
        FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public Utente(String idUtente,String nickname, String mail, String idPartita, String password) {
        this.idUtente = idUtente;
        this.nickname = nickname;
        this.mail = mail;
        partite.add(idPartita);
        this.password = password;
    }
    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public Statistica getStatistica() {
        return statistica;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getPartite() {
        return partite;
    }

    public void setPartite(String partitaID) {
        partite.add(partitaID);
    }

    //@Override
    public String toString1() {
        return "Utente{" +
                "nickname='" + nickname + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
