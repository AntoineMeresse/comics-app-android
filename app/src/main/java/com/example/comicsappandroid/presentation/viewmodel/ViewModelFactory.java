package com.example.comicsappandroid.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.comicsappandroid.data.repository.characterdisplay.CharacterDisplayRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final CharacterDisplayRepository characterDisplayRepository;

    public ViewModelFactory(CharacterDisplayRepository characterDisplayRepository){
        this.characterDisplayRepository = characterDisplayRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(characterDisplayRepository);
        }
        throw new IllegalArgumentException();
    }
}
