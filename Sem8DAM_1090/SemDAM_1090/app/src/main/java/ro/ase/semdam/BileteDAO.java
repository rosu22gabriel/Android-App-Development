package ro.ase.semdam;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BileteDAO {

    @Insert
    void insert(BiletAvion biletAvion);

    @Insert
    void insert(List<BiletAvion> biletAvionList);

    @Query("select * from bilete")
    List<BiletAvion> getAll();

    @Query("delete from bilete")
    void deleteAll();

    @Delete
    void delete(BiletAvion biletAvion);

    @Update
    void update(BiletAvion biletAvion);
}
