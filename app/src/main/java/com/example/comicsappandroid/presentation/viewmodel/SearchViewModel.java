package com.example.comicsappandroid.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.comicsappandroid.data.repository.characterdisplay.CharacterDisplayRepository;
import com.example.comicsappandroid.presentation.characterdisplay.fragments.search.adapter.CharacterViewItem;

import java.util.List;

public class SearchViewModel extends ViewModel {

    private CharacterDisplayRepository characterDisplayRepository;

    public SearchViewModel(CharacterDisplayRepository characterDisplayRepository){
        this.characterDisplayRepository = characterDisplayRepository;
    }

    // Mutable Live Data
    private MutableLiveData<List<CharacterViewItem>> characters = new MutableLiveData<>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<>();

    // Getters

    public MutableLiveData<Boolean> getIsDataLoading() {
        return this.isDataLoading;
    }

    public MutableLiveData<List<CharacterViewItem>> getCharacters(){
        return this.characters;
    }

}