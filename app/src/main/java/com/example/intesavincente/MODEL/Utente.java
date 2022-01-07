package com.example.intesavincente.MODEL;

import com.google.firebase.auth.FirebaseAuth;

public class Utente {
    public String nickname;
    public String idUtente;
    private String mail;
    public String image;
    private String password;
    public Statistica statistica;
    public boolean indovinatore;

    public Utente(String mail, boolean indovinatore, String idUtente) {
        this.mail = mail;
        this.indovinatore=indovinatore;
        this.idUtente=idUtente;
    }
    public void setId(String idUtente){
        FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
    public Utente(String idUtente,String nickname, String mail, String image, String password, boolean indovinatore) {
        this.idUtente = idUtente;
        this.nickname = nickname;
        this.mail = mail;
        this.image = image;
        this.password = password;
        this.indovinatore=indovinatore;
    }
    public Boolean isIndovinatore(){
        return indovinatore;
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

    public String getImage() {
        return image;
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

    public void setImage(String image) {
        this.image = image;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "nickname='" + nickname + '\'' +
                ", mail='" + mail + '\'' +
                ", image='" + image + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
