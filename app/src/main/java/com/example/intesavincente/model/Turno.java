package com.example.intesavincente.model;

import java.util.Objects;

public class Turno {
    private Gruppo gruppo;
    private int passo;
    private int parole_indovinate;

    //prova
    public Turno(Gruppo gruppo) {
        this.gruppo = gruppo;
        this.passo = 3;
        this.parole_indovinate = 0;
    }

    public Gruppo getGruppo() {
        return gruppo;
    }

    public void setGruppo(Gruppo gruppo) {
        this.gruppo = gruppo;
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
        Turno turno = (Turno) o;
        return passo == turno.passo && parole_indovinate == turno.parole_indovinate && gruppo.equals(turno.gruppo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gruppo, passo, parole_indovinate);
    }

    @Override
    public String toString() {
        return "Turno{" +
                "gruppo='" + gruppo + '\'' +
                ", passo=" + passo + '\'' +
                ", parole indovinate=" + parole_indovinate +
                '}';
    }
}
