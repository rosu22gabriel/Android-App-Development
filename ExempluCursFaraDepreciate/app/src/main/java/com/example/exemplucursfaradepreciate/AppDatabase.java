package com.example.exemplucursfaradepreciate;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Specificam entitatile (tabelele) si versiunea bazei de date

// @Database spune librariei Room ca aceasta clasa este baza de date
// Aici trebuie enumerate toate clasele @Entity
@Database(entities = {Comanda.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static final String db_name = "comenzi_restaurant.db";

    // Singleton: Folosim static AppDatabase instanta pentru a ne asigura ca aplicatia
    // foloseste o singura instanta a bazei de date pe to parcursul rularii(economiseste memorie).
    private static AppDatabase instanta;


    // .allowMainThreadQueries() In mod normal, Android nu da voie bazei de date sa ruleze pe firul
    // principal (UI thread) pentru ca poate ingheta ecranul

    // .allowMainThreadQueries() Daca se modifica clasa Comanda( de exemplu, se
    // adauga un camp nou), Room va sterge vechea baza de date si va crea una nou in loc
    // sa dea eroare de versiune
    public static AppDatabase getInstanta(Context context) {
        if (instanta == null) {
            synchronized (AppDatabase.class) {
                if (instanta == null) {
                    instanta = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, db_name)
                            .allowMainThreadQueries() // folosit doar pentru examen/teste rapide
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instanta;
    }
    // Metoda abstracta care returneaza DAO-ul
    public abstract ComandaDAO getComandaDao();



}
