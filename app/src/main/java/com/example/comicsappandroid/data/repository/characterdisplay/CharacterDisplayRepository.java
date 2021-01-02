package com.example.comicsappandroid.data.repository.characterdisplay;

import com.example.comicsappandroid.data.api.models.CharacterSearchResponse;
import com.example.comicsappandroid.data.database.CharacterEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Character Display Repository interface
 */
public interface CharacterDisplayRepository {
    // Remote
    Single<CharacterSearchResponse> getSearchResponse(String filter);

    // Local

    // Completable
    Completable insertCharacter(String id);
    Completable deleteCharacter(String id);

    // Flowable
    Flowable<List<CharacterEntity>> getFavoriteCharacters();
}
