package com.example.comicsappandroid.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.comicsappandroid.data.api.models.CharacterComics;
import com.example.comicsappandroid.data.api.models.CharacterSearchResponse;
import com.example.comicsappandroid.data.repository.characterdisplay.CharacterDisplayRepository;
import com.example.comicsappandroid.presentation.characterdisplay.favorite.adapter.CharacterFavViewItem;
import com.example.comicsappandroid.presentation.characterdisplay.search.mapper.MapperCharacterToViewModel;
import com.example.comicsappandroid.presentation.characterdisplay.search.adapter.CharacterViewItem;

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

    /**
     * Construcotr
     * @param characterDisplayRepository CharacterDisplayRepository
     */
    public SearchViewModel(CharacterDisplayRepository characterDisplayRepository){
        this.characterDisplayRepository = characterDisplayRepository;
        compositeDisposable = new CompositeDisposable();
        mapperCharacterToViewModel = new MapperCharacterToViewModel();
    }

    // Mutable Live Data
    private MutableLiveData<List<CharacterViewItem>> characters = new MutableLiveData<>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<>();

    // Getters

    /**
     * Method to get isDataLoading
     * @return MutableLiveData<Boolean>
     */
    public MutableLiveData<Boolean> getIsDataLoading() {
        return this.isDataLoading;
    }

    /**
     * Method to get Characters
     * @return MutableLiveData<List<CharacterViewItem>>
     */
    public MutableLiveData<List<CharacterViewItem>> getCharacters(){
        return this.characters;
    }

    // Search Character

    /**
     * Method to search Characters
     * @param filter String
     */
    public void searchCharacters(String filter) {
        isDataLoading.postValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(characterDisplayRepository.getSearchResponse(filter)
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

    /**
     * Method to cancel the subscription.
     * Inspired from Android course
     */
    public void cancelSubscription() {
        compositeDisposable.clear();
        isDataLoading.setValue(false);
    }

    /**
     * Method to get an Empty list for the recycler view
     */
    public void getEmptyList(){
        characters.setValue(new ArrayList<CharacterViewItem>());
    }
}