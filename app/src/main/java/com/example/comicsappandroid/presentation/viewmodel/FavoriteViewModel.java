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

    /**
     * Method to add a character to favorite
     * @param id character id
     */
    public void addToFavorite(String id){
        compositeDisposable.add(characterDisplayRepository.insertCharacter(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.d("ADDFAVORITE", "onComplete: True");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString());
                    }
                })
        );
    }

    /**
     * Method to remove a character from favorite
     * @param id character id
     */
    public void deleteFromFavorite(String id){
        compositeDisposable.add(characterDisplayRepository.deleteCharacter(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.d("DELFAVORITE", "onComplete: True");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString());
                    }
                })
        );
    }
}