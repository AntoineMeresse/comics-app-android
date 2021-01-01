package com.example.comicsappandroid.presentation.characterdisplay.search.mapper;

import com.example.comicsappandroid.data.api.models.CharacterComics;
import com.example.comicsappandroid.presentation.characterdisplay.search.adapter.CharacterViewItem;

import java.util.ArrayList;
import java.util.List;

public class MapperCharacterToViewModel {

    private CharacterViewItem characterViewItem;
    private List<CharacterViewItem> characterViewItemList;

    public CharacterViewItem map(CharacterComics character) {
        characterViewItem = new CharacterViewItem();
        characterViewItem.setCharacterID(character.getId().toString()); // Set Id
        characterViewItem.setCharacterName(character.getName()); // Set Name
        characterViewItem.setCharacterImage(character.getCharacterImage());
        characterViewItem.setFavorite(character.isFav());
        characterViewItem.setCharacterRealName(character.getRealName());
        characterViewItem.setDescription(character.getDeck());
        characterViewItem.setCharacterPageUrl(character.getSiteDetailUrl());
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
