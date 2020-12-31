package com.example.comicsappandroid.data.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Character Model
 * https://comicvine.gamespot.com/api/documentation
 */
public class CharacterComics {

    private String name;
    private Integer id;
    private boolean isFav;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @SerializedName("image")
    private CharacterImage characterImage;

    public CharacterImage getCharacterImage() {
        return characterImage;
    }

    public void setCharacterImage(CharacterImage characterImage) {
        this.characterImage = characterImage;
    }

    // ----------------------------------- Favorite Char ------------------------------------------

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }
}
