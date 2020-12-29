package com.example.comicsappandroid.data.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterSearchResponse {

    @SerializedName("result")
    List<CharacterComics> characterList;

    public List<CharacterComics> getCharacterList() {
        return characterList;
    }
}
