package com.example.comicsappandroid.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.comicsappandroid.data.database.CharacterEntity;
import com.example.comicsappandroid.data.repository.characterdisplay.CharacterDisplayRepository;
import com.example.comicsappandroid.presentation.characterdisplay.favorite.adapter.CharacterFavViewItem;
import com.example.comicsappandroid.presentation.characterdisplay.favorite.mapper.MapperCharacterEntityToCharacterFavViewItem;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class FavoriteViewModel extends ViewModel {

    private CharacterDisplayRepository characterDisplayRepository;
    private CompositeDisposable compositeDisposable;

    private MapperCharacterEntityToCharacterFavViewItem mapperCharacterEntityToCharacterFavViewItem;

    /**
     * Constructor
     * @param characterDisplayRepository CharacterDisplayRepository
     */
    public FavoriteViewModel(CharacterDisplayRepository characterDisplayRepository) {
        this.characterDisplayRepository = characterDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        mapperCharacterEntityToCharacterFavViewItem = new MapperCharacterEntityToCharacterFavViewItem();
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

    // Mutable LiveData

    private MutableLiveData<List<CharacterFavViewItem>> favs;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();

    /**
     * Method to get favorite characters
     * @return MutableLiveData<List<CharacterFavViewItem>>
     */
    public MutableLiveData<List<CharacterFavViewItem>> getFavs(){
        isLoading.setValue(true);
        if (favs == null) {
            favs = new MutableLiveData<>();
            compositeDisposable.add(characterDisplayRepository.getFavoriteCharacters()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new ResourceSubscriber<List<CharacterEntity>>() {
                                       @Override
                                       public void onNext(List<CharacterEntity> characterEntities) {
                                           isLoading.setValue(false);
                                           favs.setValue(mapperCharacterEntityToCharacterFavViewItem
                                                   .map(characterEntities));
                                       }

                                       @Override
                                       public void onError(Throwable t) {
                                           System.out.println(t.toString());
                                           isLoading.setValue(false);
                                       }

                                       @Override
                                       public void onComplete() {
                                            isLoading.setValue(false);
                                       }
                                   }
              ));
        }
        return favs;
    }
}