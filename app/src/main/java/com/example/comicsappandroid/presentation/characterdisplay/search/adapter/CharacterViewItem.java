package com.example.comicsappandroid.presentation.characterdisplay.search.adapter;

import com.example.comicsappandroid.data.api.models.CharacterImage;

public class CharacterViewItem {

    private String characterID;
    private String characterName;
    private CharacterImage characterImage;
    private boolean isFavorite;

    private String characterRealName;
    private String description;
    private String characterPageUrl;

    public String getCharacterID() {
        return characterID;
    }

    public void setCharacterID(String characterID) {
        this.characterID = characterID;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public CharacterImage getCharacterImage() {
        return characterImage;
    }

    public void setCharacterImage(CharacterImage characterImage) {
        this.characterImage = characterImage;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getCharacterRealName() {
        return characterRealName;
    }

    public void setCharacterRealName(String characterRealName) {
        this.characterRealName = characterRealName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCharacterPageUrl() {
        return characterPageUrl;
    }

    public void setCharacterPageUrl(String characterPageUrl) {
        this.characterPageUrl = characterPageUrl;
    }
}
