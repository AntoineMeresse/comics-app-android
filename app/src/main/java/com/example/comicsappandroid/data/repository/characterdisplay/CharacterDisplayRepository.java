package com.example.comicsappandroid.data.repository.characterdisplay;

import com.example.comicsappandroid.data.api.models.Characters;

import io.reactivex.Single;

public interface CharacterDisplayRepository {
    // Remote
    Single<Characters> getSearchResponse();
}
