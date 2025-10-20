package com.example.cinema;

import java.util.List;

public class Actor {
    private String nume;
    private int varsta;


    public Actor(int varsta, String nume) {
        this.varsta = varsta;
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }


    @Override
    public String toString() {
        return "Actor{" +
                "nume='" + nume + '\'' +
                ", varsta='" + varsta + '\'' +
                '}';
    }
}
