package com.example.comicsappandroid.data.repository.characterdisplay;

import com.example.comicsappandroid.data.api.models.Characters;
import com.example.comicsappandroid.data.repository.characterdisplay.remote.CharacterDisplayRemoteDS;

import io.reactivex.Single;

public class CharacterDisplayDataRepository implements CharacterDisplayRepository {

    private CharacterDisplayRemoteDS characterDisplayRemoteDS;

    public CharacterDisplayDataRepository(CharacterDisplayRemoteDS characterDisplayRemoteDS) {
        this.characterDisplayRemoteDS = characterDisplayRemoteDS;
    }

    @Override
    public Single<Characters> getSearchResponse() {
        return null;
    }
}
