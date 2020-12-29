package com.example.comicsappandroid.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.comicsappandroid.data.api.models.CharacterComics;
import com.example.comicsappandroid.data.api.models.CharacterSearchResponse;
import com.example.comicsappandroid.data.repository.characterdisplay.CharacterDisplayRepository;
import com.example.comicsappandroid.presentation.characterdisplay.fragments.search.MapperCharacterToViewModel;
import com.example.comicsappandroid.presentation.characterdisplay.fragments.search.adapter.CharacterViewItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchViewModel extends ViewModel {

    private CharacterDisplayRepository characterDisplayRepository;

    private CompositeDisposable compositeDisposable;
    private MapperCharacterToViewModel mapperCharacterToViewModel;

    public SearchViewModel(CharacterDisplayRepository characterDisplayRepository){
        this.characterDisplayRepository = characterDisplayRepository;
        compositeDisposable = new CompositeDisposable();
        mapperCharacterToViewModel = new MapperCharacterToViewModel();
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

    // Search Character

    public void searchCharacters() {
        isDataLoading.postValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(characterDisplayRepository.getSearchResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(
                        new DisposableSingleObserver<CharacterSearchResponse>() {

                            @Override
                            public void onSuccess(CharacterSearchResponse characterSearchResponse) {
                                List<CharacterComics> characterComics = characterSearchResponse.getCharacterList();
                                characters.setValue(mapperCharacterToViewModel.map(characterComics));
                                isDataLoading.postValue(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                System.out.println(e.toString());
                                isDataLoading.postValue(false);
                            }
                        }
                )
        );
    }

    public void cancelSubscription() {
        compositeDisposable.clear();
        isDataLoading.setValue(false);
    }
}