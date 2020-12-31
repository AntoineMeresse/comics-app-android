package com.example.comicsappandroid.presentation.characterdisplay.favorite.adapter;

public class CharacterFavViewItem {

    private String characterID;

    // Set here all infos you want to display
    private String characterName;
    private String characterImageUrl;

    // Getters & Setters

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

    public String getCharacterImageUrl() {
        return characterImageUrl;
    }

    public void setCharacterImageUrl(String characterImageUrl) {
        this.characterImageUrl = characterImageUrl;
    }
}
