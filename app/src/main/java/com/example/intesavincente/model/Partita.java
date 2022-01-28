package com.example.intesavincente.model;

import java.util.Arrays;
import java.util.Objects;

public class Partita {
    private Turno[] turni;
    private Gruppo vincitore;

    public Partita() {
        this.turni = turni;
        this.vincitore = vincitore;
    }

    public Partita(Turno[] turni) {
        this.turni = turni;
    }

    public Turno[] getTurni() {
        return turni;
    }

    public void setTurni(Turno[] turni) {
        this.turni = turni;
    }

    public Gruppo getVincitore() {
        return vincitore;
    }

    public void setVincitore(Gruppo vincitore) {
        this.vincitore = vincitore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partita partita = (Partita) o;
        return Arrays.equals(turni, partita.turni) && Objects.equals(vincitore, partita.vincitore);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(vincitore);
        result = 31 * result + Arrays.hashCode(turni);
        return result;
    }

    @Override
    public String toString() {
        return "Partita{" +
                "turni=" + Arrays.toString(turni) +
                ", vincitore=" + vincitore +
                '}';
    }
}
