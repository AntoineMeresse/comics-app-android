package com.example.comicsappandroid.presentation.characterdisplay.search.adapter;

/**
 * Character Action Interface
 */
public interface CharacterActionInterface {
    void onHeartClick(String characterID, boolean isFav);
    void startInfoActivity(CharacterViewItem characterViewItem);
}
