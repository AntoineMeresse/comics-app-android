package com.example.comicsappandroid.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CharacterEntity.class}, version = 1)
public abstract class CharacterDatabase extends RoomDatabase {

    public abstract CharacterDAO characterDAO();

}
