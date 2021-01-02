package com.example.comicsappandroid.data.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * CharacterSearchResponse store the list of characters infos that the api return
 */
public class CharacterSearchResponse {

    @SerializedName("results")
    List<CharacterComics> characterList;

    public List<CharacterComics> getCharacterList() {
        return characterList;
    }
}
