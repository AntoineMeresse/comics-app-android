package com.example.comicsappandroid.presentation.characterdisplay.fragments.search;

import com.example.comicsappandroid.data.api.models.CharacterComics;
import com.example.comicsappandroid.presentation.characterdisplay.fragments.search.adapter.CharacterViewItem;

import java.util.ArrayList;
import java.util.List;

public class MapperCharacterToViewModel {

    private CharacterViewItem characterViewItem;
    private List<CharacterViewItem> characterViewItemList;

    public CharacterViewItem map(CharacterComics character) {
        characterViewItem = new CharacterViewItem();
        characterViewItem.setCharacterID(character.getId().toString()); // Set Id
        characterViewItem.setCharacterName(character.getName()); // Set Name
        return characterViewItem;
    }

    public List<CharacterViewItem> map(List<CharacterComics> characterComicsList) {
        characterViewItemList = new ArrayList<>();
        for(CharacterComics cc : characterComicsList) {
            characterViewItemList.add(map(cc));
        }
        return characterViewItemList;
    }
}
