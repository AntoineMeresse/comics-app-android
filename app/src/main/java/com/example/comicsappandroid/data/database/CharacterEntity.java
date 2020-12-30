package com.example.comicsappandroid.data.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "characters_entity")
public class CharacterEntity {

    @PrimaryKey
    @NonNull
    private Integer id;

    private String name;

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }
}
