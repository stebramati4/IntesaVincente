package com.example.intesavincente.MODEL;

public class Statistica {
    public int partiteGiocate;
    public int partiteVinte;
    public int partitePerse;
    public int punteggioMax;
    public int nTotParoleIndovinate;


    public Statistica() {
        this.partiteGiocate = 0;
        this.partiteVinte = 0;
        this.partitePerse = 0;
        this.punteggioMax = 0;
        this.nTotParoleIndovinate = 0;
    }

    public Statistica(int partiteGiocate, int partiteVinte, int partitePerse, int punteggioMax, int nTotParoleIndovinate) {
        this.partiteGiocate = partiteGiocate;
        this.partiteVinte = partiteVinte;
        this.partitePerse = partitePerse;
        this.punteggioMax = punteggioMax;
        this.nTotParoleIndovinate = nTotParoleIndovinate;
    }

    public int getPartiteGiocate() {
        return partiteGiocate;
    }

    public void setPartiteGiocate(int partiteGiocate) {
        this.partiteGiocate = partiteGiocate;
    }

    public int getPartiteVinte() {
        return partiteVinte;
    }

    public void setPartiteVinte(int partiteVinte) {
        this.partiteVinte = partiteVinte;
    }

    public int getPartitePerse() {
        return partitePerse;
    }

    public void setPartitePerse(int partitePerse) {
        this.partitePerse = partitePerse;
    }

    public int getPunteggioMax() {
        return punteggioMax;
    }

    public void setPunteggioMax(int punteggioMax) {
        this.punteggioMax = punteggioMax;
    }

    public int getnTotParoleIndovinate() {
        return nTotParoleIndovinate;
    }

    public void setnTotParoleIndovinate(int nTotParoleIndovinate) {
        this.nTotParoleIndovinate = nTotParoleIndovinate;
    }

    @Override
    public String toString() {
        return "Statistica{" +
                "partiteGiocate=" + partiteGiocate +
                ", partiteVinte=" + partiteVinte +
                ", partitePerse=" + partitePerse +
                ", punteggioMax=" + punteggioMax +
                ", nTotParoleIndovinate=" + nTotParoleIndovinate +
                '}';
    }
}
