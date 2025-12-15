package com.example.exemplucursfaradepreciate;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ComandaDAO {
    @Insert
    void insert(Comanda comanda);

    @Insert
    void insert(List<Comanda> comenzi); // util pentru import JSON

    @Update
    void update(Comanda comanda);

    @Delete
    void delete(Comanda comanda);

    @Query("SELECT * FROM comenzi")
    List<Comanda> getAll();

    @Query("SELECT * FROM comenzi ORDER BY pret DESC")
    List<Comanda> getAllSortedByPrice();


}
