package com.example.comicsappandroid.presentation.characterdisplay.favorite.mapper;

import com.example.comicsappandroid.data.database.CharacterEntity;
import com.example.comicsappandroid.presentation.characterdisplay.favorite.adapter.CharacterFavViewItem;

import java.util.ArrayList;
import java.util.List;

public class MapperCharacterEntityToCharacterFavViewItem {

    private CharacterFavViewItem characterFavViewItem;
    private List<CharacterFavViewItem> characterFavViewItemList;

    public CharacterFavViewItem map(CharacterEntity character) {
        characterFavViewItem = new CharacterFavViewItem();
        characterFavViewItem.setCharacterID(character.getId().toString());
        characterFavViewItem.setCharacterName(character.getName());
        characterFavViewItem.setCharacterImageUrl(character.getImageUrl());
        characterFavViewItem.setDescription(character.getDescription());
        characterFavViewItem.setRealName(character.getRealName());
        return characterFavViewItem;
    }

    public List<CharacterFavViewItem> map(List<CharacterEntity> characterEntities) {
        characterFavViewItemList = new ArrayList<>();
        for(CharacterEntity cc : characterEntities) {
            characterFavViewItemList.add(map(cc));
        }
        return characterFavViewItemList;
    }
}
