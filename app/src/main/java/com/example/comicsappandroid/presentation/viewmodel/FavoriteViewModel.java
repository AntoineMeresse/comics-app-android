package com.example.comicsappandroid.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.comicsappandroid.data.repository.characterdisplay.CharacterDisplayRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class FavoriteViewModel extends ViewModel {

    private CharacterDisplayRepository characterDisplayRepository;
    private CompositeDisposable compositeDisposable;

    public FavoriteViewModel(CharacterDisplayRepository characterDisplayRepository) {
        this.characterDisplayRepository = characterDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
    }
}