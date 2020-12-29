package com.example.comicsappandroid.data.repository.characterdisplay;

import com.example.comicsappandroid.data.api.models.CharacterSearchResponse;
import com.example.comicsappandroid.data.repository.characterdisplay.remote.CharacterDisplayRemoteDS;

import io.reactivex.Single;

public class CharacterDisplayDataRepository implements CharacterDisplayRepository {

    private CharacterDisplayRemoteDS characterDisplayRemoteDS;

    public CharacterDisplayDataRepository(CharacterDisplayRemoteDS characterDisplayRemoteDS) {
        this.characterDisplayRemoteDS = characterDisplayRemoteDS;
    }

    @Override
    public Single<CharacterSearchResponse> getSearchResponse(String filter) {
        return characterDisplayRemoteDS.searchCharacters(filter);
    }
}
