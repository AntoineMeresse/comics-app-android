package com.example.comicsappandroid.data.repository.characterdisplay;

import com.example.comicsappandroid.data.api.models.CharacterSearchResponse;
import com.example.comicsappandroid.data.database.CharacterEntity;
import com.example.comicsappandroid.data.repository.characterdisplay.local.CharacterDisplayLocalDS;
import com.example.comicsappandroid.data.repository.characterdisplay.remote.CharacterDisplayRemoteDS;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class CharacterDisplayDataRepository implements CharacterDisplayRepository {

    private CharacterDisplayRemoteDS characterDisplayRemoteDS;
    private CharacterDisplayLocalDS characterDisplayLocalDS;

    public CharacterDisplayDataRepository(CharacterDisplayRemoteDS characterDisplayRemoteDS,
                                          CharacterDisplayLocalDS characterDisplayLocalDS) {
        this.characterDisplayRemoteDS = characterDisplayRemoteDS;
        this.characterDisplayLocalDS = characterDisplayLocalDS;
    }

    // ---------------------------------- Remote ------------------------------------------------

    @Override
    public Single<CharacterSearchResponse> getSearchResponse(String filter) {
        return characterDisplayRemoteDS.searchCharacters(filter);
    }

    // ---------------------------------- Local ------------------------------------------------

    @Override
    public Completable insertCharacter(String id) {
        return null;
    }

    @Override
    public Completable deleteCharacter(String id) {
        return null;
    }

    @Override
    public Flowable<List<CharacterEntity>> getFavoriteCharacters() {
        return null;
    }
}
