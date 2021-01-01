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

    @SerializedName("real_name")
    private String realName;

    private String siteDetailUrl;
    private String deck;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSiteDetailUrl() {
        return siteDetailUrl;
    }

    public void setSiteDetailUrl(String siteDetailUrl) {
        this.siteDetailUrl = siteDetailUrl;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    // ----------------------------------- Favorite Char ------------------------------------------

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }
}
