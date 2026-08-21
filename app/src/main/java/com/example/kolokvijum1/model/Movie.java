package com.example.kolokvijum1.model;

public class Movie {

    private String naziv;
    private float ocena;
    private boolean odgledano;

    public Movie(String naziv, float ocena, boolean odgledano) {
        this.naziv = naziv;
        this.ocena = ocena;
        this.odgledano = odgledano;
    }

    public String getNaziv() { return naziv; }
    public float getOcena() { return ocena; }
    public boolean isOdgledano() { return odgledano; }

}
