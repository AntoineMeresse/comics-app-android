package com.example.comicsappandroid.presentation.characterdisplay.favorite.adapter;

public class CharacterFavViewItem {

    private String characterID;

    // Set here all infos you want to display
    private String characterName;
    private String characterImageUrl;
    private String realName;
    private String description;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
