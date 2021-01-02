package com.example.comicsappandroid.data.repository.characterdisplay.remote;

import com.example.comicsappandroid.CharacterComicsApplication;
import com.example.comicsappandroid.data.api.ComicsDisplayService;
import com.example.comicsappandroid.data.api.models.CharacterSearchResponse;

import io.reactivex.Single;

public class CharacterDisplayRemoteDS {

    private ComicsDisplayService comicsDisplayService;

    public CharacterDisplayRemoteDS(ComicsDisplayService comicsDisplayService) {
        this.comicsDisplayService = comicsDisplayService;
    }

    // 1 méthode par requête d'api dans ComicsDisplayService

    /**
     * Method that use the service to search characters.
     * @param filter String
     * @return Single<CharacterSearchResponse>
     */
    public Single<CharacterSearchResponse> searchCharacters(String filter) {
        return this.comicsDisplayService.searchCharacters(CharacterComicsApplication.API_KEY, CharacterComicsApplication.API_FORMAT, filter);
    }
}
