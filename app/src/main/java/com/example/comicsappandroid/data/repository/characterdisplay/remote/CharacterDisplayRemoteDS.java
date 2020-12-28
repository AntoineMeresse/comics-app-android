package com.example.comicsappandroid.data.repository.characterdisplay.remote;

import com.example.comicsappandroid.CharacterComicsApplication;
import com.example.comicsappandroid.data.api.ComicsDisplayService;
import com.example.comicsappandroid.data.api.models.Characters;

import io.reactivex.Single;

public class CharacterDisplayRemoteDS {

    private ComicsDisplayService comicsDisplayService;

    public CharacterDisplayRemoteDS(ComicsDisplayService comicsDisplayService) {
        this.comicsDisplayService = comicsDisplayService;
    }

    // 1 méthode par requête d'api dans ComicsDisplayService

    public Single<Characters> searchCharacters() {
        return this.comicsDisplayService.searchCharacters(CharacterComicsApplication.API_KEY);
    }
}
