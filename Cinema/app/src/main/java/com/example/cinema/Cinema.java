package com.example.cinema;

import java.util.List;
import java.util.stream.Collectors;

public class Cinema {
    private String nume;
    private String adresa;
    private List<Film> tabelaFilme;

    public Cinema(String nume, String adresa, List<Film> tabelaFilme) {
        this.nume = nume;
        this.adresa = adresa;
        this.tabelaFilme = tabelaFilme;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public List<Film> getTabelaFilme() {
        return tabelaFilme;
    }

    public void setTabelaFilme(List<Film> tabelaFilme) {
        this.tabelaFilme = tabelaFilme;
    }

    public List<String> getTabelaNumeFilme() {
        return tabelaFilme.stream().map(Film::getDenumire).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", tabelaFilme=" + tabelaFilme +
                '}';
    }
}
