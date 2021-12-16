package com.example.intesavincente.MODEL;

public class Utente {
    public String nickname;
    private String mail;
    public String image;
    private String password;

    public Utente(String nickname, String mail, String image, String password) {
        this.nickname = nickname;
        this.mail = mail;
        this.image = image;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
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
