package com.example.comicsappandroid.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CharacterDAO {

    @Query("SELECT * FROM characters_entity")
    Flowable<List<CharacterEntity>> getFavoriteCharacters();

    @Insert
    Completable insertCharacter(CharacterEntity characterEntity);

    @Delete
    Completable deleteCharacter(CharacterEntity characterEntity);

    @Query("SELECT id from characters_entity")
    Single<List<Integer>> getIdFavoriteCharacters();
}
