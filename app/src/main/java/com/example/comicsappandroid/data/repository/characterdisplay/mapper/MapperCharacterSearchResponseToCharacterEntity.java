package com.example.comicsappandroid.data.repository.characterdisplay.mapper;

import com.example.comicsappandroid.data.api.models.CharacterComics;
import com.example.comicsappandroid.data.api.models.CharacterSearchResponse;
import com.example.comicsappandroid.data.database.CharacterEntity;

/**
 * Mapper to map CharacterSearchResponse infos to a CharacterEntity object.
 */
public class MapperCharacterSearchResponseToCharacterEntity {

    public static CharacterEntity convertCharacterToCharacterEntity(CharacterSearchResponse characterSearchResponse) {
        CharacterEntity res = new CharacterEntity();
        if(characterSearchResponse.getCharacterList().size() > 0) {
            CharacterComics character = characterSearchResponse.getCharacterList().get(0);
            res.setId(character.getId());
            res.setName(character.getName());
            res.setImageUrl(character.getCharacterImage().getMediumUrl());
            res.setRealName(character.getRealName());
            res.setDescription(character.getDeck());
        }
        return res;
    }
}
