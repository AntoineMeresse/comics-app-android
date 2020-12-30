package com.example.comicsappandroid.data.repository.characterdisplay.mapper;

import com.example.comicsappandroid.data.api.models.CharacterComics;
import com.example.comicsappandroid.data.database.CharacterEntity;

public class MapperCharacterToCharacterEntity {

    public static CharacterEntity convertCharacterToCharacterEntity(CharacterComics character) {
        CharacterEntity res = new CharacterEntity();
        res.setId(character.getId());
        res.setName(character.getName());
        res.setImageUrl(character.getCharacterImage().getMediumUrl());
        return res;
    }
}
