package com.example.comicsappandroid.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.comicsappandroid.data.repository.characterdisplay.CharacterDisplayRepository;

public class SearchViewModel extends ViewModel {

    private CharacterDisplayRepository characterDisplayRepository;

    public SearchViewModel(CharacterDisplayRepository characterDisplayRepository){
        this.characterDisplayRepository = characterDisplayRepository;
    }

}