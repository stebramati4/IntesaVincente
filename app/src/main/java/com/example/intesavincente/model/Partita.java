package com.example.intesavincente.model;

import java.util.Objects;

public class Partita{
    private String gruppoID;
    private int passo;
    private int parole_indovinate;

    //prova
    public Partita(){
        this.passo = 3;
        this.parole_indovinate = 0;
    }
    public Partita(String gruppo) {
        this.gruppoID = gruppo;
        this.passo = 3;
        this.parole_indovinate = 0;
    }

    public String getGruppo() {
        return gruppoID;
    }

    public void setGruppo(String gruppo) {
        this.gruppoID = gruppo;
    }

    public int getPasso() {
        return passo;
    }

    public void setPasso(int passo) {
        this.passo = passo;
    }

    public int getParole_indovinate() {
        return parole_indovinate;
    }

    public void setParole_indovinate(int parole_indovinate) {
        this.parole_indovinate = parole_indovinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partita turno = (Partita) o;
        return passo == turno.passo && parole_indovinate == turno.parole_indovinate && gruppoID.equals(turno.gruppoID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gruppoID, passo, parole_indovinate);
    }

    @Override
    public String toString() {
        return "Turno{" +
                "gruppo='" + gruppoID + '\'' +
                ", passo=" + passo + '\'' +
                ", parole indovinate=" + parole_indovinate +
                '}';
    }
}
