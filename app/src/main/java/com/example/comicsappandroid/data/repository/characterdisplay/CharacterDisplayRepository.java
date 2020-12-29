package com.example.comicsappandroid.data.repository.characterdisplay;

import com.example.comicsappandroid.data.api.models.CharacterSearchResponse;

import io.reactivex.Single;

public interface CharacterDisplayRepository {
    // Remote
    Single<CharacterSearchResponse> getSearchResponse();
}
