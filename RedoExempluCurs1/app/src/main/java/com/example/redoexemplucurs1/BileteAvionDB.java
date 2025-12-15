package com.example.redoexemplucurs1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {BiletAvion.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class BileteAvionDB extends RoomDatabase {
    public static final String DB_NAME = "bilete.db";
    private static BileteAvionDB instanta;

    public static BileteAvionDB getInstanta(Context context){
        if(instanta==null)
            instanta = Room.databaseBuilder(context, BileteAvionDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        return instanta;
    }

    public abstract BileteDAO getBileteDAO();
}
