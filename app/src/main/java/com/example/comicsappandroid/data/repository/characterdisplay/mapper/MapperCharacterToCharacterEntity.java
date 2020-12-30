package com.example.comicsappandroid.data.repository.characterdisplay.mapper;

import com.example.comicsappandroid.data.api.models.CharacterComics;
import com.example.comicsappandroid.data.api.models.CharacterSearchResponse;
import com.example.comicsappandroid.data.database.CharacterEntity;

public class MapperCharacterToCharacterEntity {

    public static CharacterEntity convertCharacterToCharacterEntity(CharacterSearchResponse characterSearchResponse) {
        CharacterEntity res = new CharacterEntity();
        if(characterSearchResponse.getCharacterList().size() > 0) {
            CharacterComics character = characterSearchResponse.getCharacterList().get(0);
            res.setId(character.getId());
            res.setName(character.getName());
            res.setImageUrl(character.getCharacterImage().getMediumUrl());
        }
        return res;
    }
}
