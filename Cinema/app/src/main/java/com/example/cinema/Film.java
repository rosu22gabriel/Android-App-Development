package com.example.cinema;

import java.util.List;

public class Film {
    private String denumire;

    private float durata;

    private List<Actor> actori;

    private String numeRegizor;

    public Film(String denumire, float durata, List<Actor> actori, String numeRegizor) {
        this.denumire = denumire;
        this.durata = durata;
        this.actori = actori;
        this.numeRegizor = numeRegizor;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public float getDurata() {
        return durata;
    }

    public void setDurata(float durata) {
        this.durata = durata;
    }

    public List<Actor> getActori() {
        return actori;
    }

    public void setActori(List<Actor> actori) {
        this.actori = actori;
    }

    public String getNumeRegizor() {
        return numeRegizor;
    }

    public void setNumeRegizor(String numeRegizor) {
        this.numeRegizor = numeRegizor;
    }

    @Override
    public String toString() {
        return "Film{" +
                "denumire='" + denumire + '\'' +
                ", durata=" + durata +
                ", actori=" + actori +
                ", numeRegizor='" + numeRegizor + '\'' +
                '}';
    }
}
