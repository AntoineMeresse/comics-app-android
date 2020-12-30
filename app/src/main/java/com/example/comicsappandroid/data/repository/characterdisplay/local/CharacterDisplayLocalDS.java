package com.example.comicsappandroid.data.repository.characterdisplay.local;

import com.example.comicsappandroid.data.database.CharacterDAO;
import com.example.comicsappandroid.data.database.CharacterDatabase;
import com.example.comicsappandroid.data.database.CharacterEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class CharacterDisplayLocalDS {

    private CharacterDAO characterDAO;
    private CharacterDatabase characterDatabase;

    public CharacterDisplayLocalDS(CharacterDatabase characterDatabase) {
        this.characterDatabase = characterDatabase;
        this.characterDAO = characterDatabase.characterDAO();
    }

    /**
     * Method to get favorite characters
     * @return Flowable<List<CharacterEntity>>
     */
    public Flowable<List<CharacterEntity>> getFavoriteCharacters(){
        return this.characterDAO.getFavoriteCharacters();
    }

    /**
     * Method to add a Character to the local database
     * @param characterEntity
     * @return Completable
     */
    public Completable insertCharacter(CharacterEntity characterEntity) {
        return this.characterDAO.insertCharacter(characterEntity);
    }

    /**
     * Method to delete a Character from the local database
     * @param characterEntity
     * @return Completable
     */
    public Completable deleteCharacter(CharacterEntity characterEntity) {
        return this.characterDAO.deleteCharacter(characterEntity);
    }

    /**
     * Method to get ids of all local characters
     * @return Single
     */
    public Single<List<Integer>> getIdFavoriteCharacters() {
        return this.characterDAO.getIdFavoriteCharacters();
    }

}
