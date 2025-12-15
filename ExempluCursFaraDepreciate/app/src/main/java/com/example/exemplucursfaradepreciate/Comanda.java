package com.example.exemplucursfaradepreciate;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "comenzi ") // Numele tabelei in SQLite
public class Comanda implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id; // primary key pentru Room
    private String client;
    private String adresa;
    private float pret;
    private String metodaPlata;
    private String uid; // Pentru identificare unica in Firebase

    // 1. Constructorul folosit de Room
    public Comanda(int id, String client, String adresa, float pret, String metodaPlata, String uid) {
        this.id = id;
        this.client = client;
        this.adresa = adresa;
        this.pret = pret;
        this.metodaPlata = metodaPlata;
        this.uid = uid;
    }

    // 2. Constructorul folosit in aplicatie (fara id pentru ca se auto-genereaza)
    @Ignore
    public Comanda(String client, String adresa, float pret, String metodaPlata) {
        this.client = client;
        this.adresa = adresa;
        this.pret = pret;
        this.metodaPlata = metodaPlata;
    }

    // 3. Constructor gol - obligatoriu pentru Firebase
    @Ignore
    public Comanda() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public String getMetodaPlata() {
        return metodaPlata;
    }

    public void setMetodaPlata(String metodaPlata) {
        this.metodaPlata = metodaPlata;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "id=" + id +
                ", client='" + client + '\'' +
                ", adresa='" + adresa + '\'' +
                ", pret=" + pret +
                ", metodaPlata='" + metodaPlata + '\'' +
                '}';
    }
}
