package com.example.comicsappandroid.data.repository.characterdisplay;

import com.example.comicsappandroid.data.repository.characterdisplay.remote.CharacterDisplayRemoteDS;

public class CharacterDisplayDataRepository implements CharacterDisplayRepository {

    private CharacterDisplayRemoteDS characterDisplayRemoteDS;

    public CharacterDisplayDataRepository(CharacterDisplayRemoteDS characterDisplayRemoteDS) {
        this.characterDisplayRemoteDS = characterDisplayRemoteDS;
    }
}
