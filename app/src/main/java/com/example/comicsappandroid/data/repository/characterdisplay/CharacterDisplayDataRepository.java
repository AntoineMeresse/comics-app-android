package com.example.comicsappandroid.data.repository.characterdisplay;

import com.example.comicsappandroid.data.api.models.CharacterComics;
import com.example.comicsappandroid.data.api.models.CharacterSearchResponse;
import com.example.comicsappandroid.data.database.CharacterEntity;
import com.example.comicsappandroid.data.repository.characterdisplay.local.CharacterDisplayLocalDS;
import com.example.comicsappandroid.data.repository.characterdisplay.mapper.MapperCharacterSearchResponseToCharacterEntity;
import com.example.comicsappandroid.data.repository.characterdisplay.remote.CharacterDisplayRemoteDS;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class CharacterDisplayDataRepository implements CharacterDisplayRepository {

    private CharacterDisplayRemoteDS characterDisplayRemoteDS;
    private CharacterDisplayLocalDS characterDisplayLocalDS;

    public CharacterDisplayDataRepository(CharacterDisplayRemoteDS characterDisplayRemoteDS,
                                          CharacterDisplayLocalDS characterDisplayLocalDS) {
        this.characterDisplayRemoteDS = characterDisplayRemoteDS;
        this.characterDisplayLocalDS = characterDisplayLocalDS;
    }

    // ---------------------------------- Remote ------------------------------------------------

    /**
     * Method to get a single of CharacterSearchResponse
     * @param filter
     * @return Single<CharacterSearchResponse>
     */
    @Override
    public Single<CharacterSearchResponse> getSearchResponse(String filter) {
        return characterDisplayRemoteDS.searchCharacters(filter)
                .zipWith(characterDisplayLocalDS.getIdFavoriteCharacters(), new BiFunction<CharacterSearchResponse, List<Integer>, CharacterSearchResponse>() {
                    @Override
                    public CharacterSearchResponse apply(CharacterSearchResponse characterSearchResponse, List<Integer> integers) throws Exception {
                        // Loop in characters list
                        List<CharacterComics> characterComicsList = characterSearchResponse.getCharacterList();
                        for (CharacterComics characterComics : characterComicsList) {
                            if (integers.contains(characterComics.getId())) {
                                characterComics.setFav(true);
                            }
                        }
                        return characterSearchResponse;
                    }
                });
    }

    // ---------------------------------- Local ------------------------------------------------

    /**
     * Method to add a character to the local storage
     * @param id String
     * @return Completable
     */
    @Override
    public Completable insertCharacter(String id) {
        // step 1:
        Single<CharacterSearchResponse> characterSearchResponseSingle =
                characterDisplayRemoteDS.searchCharacters("id:"+id);

        // step 2:
        Single<CharacterEntity> characterEntitySingle = characterSearchResponseSingle.map(
                new Function<CharacterSearchResponse, CharacterEntity>() {
            @Override
            public CharacterEntity apply(CharacterSearchResponse characterSearchResponse) throws Exception {
                return MapperCharacterSearchResponseToCharacterEntity.convertCharacterToCharacterEntity(characterSearchResponse);
            }
        });

        // step 3:
        Completable characterCompletable = characterEntitySingle.flatMapCompletable(new Function <CharacterEntity, CompletableSource>(){
            @Override
            public CompletableSource apply(CharacterEntity characterEntity) throws Exception {
                return characterDisplayLocalDS.insertCharacter(characterEntity);
            }
        });

        return characterCompletable;
    }

    /**
     * Method to delete a character to the local storage
     * @param id String
     * @return Completable
     */
    @Override
    public Completable deleteCharacter(String id) {
        // step 1:
        Single<CharacterSearchResponse> characterSearchResponseSingle =
                characterDisplayRemoteDS.searchCharacters("id:"+id);

        // step 2:
        Single<CharacterEntity> characterEntitySingle = characterSearchResponseSingle.map(
                new Function<CharacterSearchResponse, CharacterEntity>() {
                    @Override
                    public CharacterEntity apply(CharacterSearchResponse characterSearchResponse) throws Exception {
                        return MapperCharacterSearchResponseToCharacterEntity.convertCharacterToCharacterEntity(characterSearchResponse);
                    }
                });

        // step 3:
        Completable characterCompletable = characterEntitySingle.flatMapCompletable(new Function <CharacterEntity, CompletableSource>(){
            @Override
            public CompletableSource apply(CharacterEntity characterEntity) throws Exception {
                return characterDisplayLocalDS.deleteCharacter(characterEntity);
            }
        });

        return characterCompletable;
    }

    /**
     * Method to get Favorite Characters from local storage
     * @return Flowable<List<CharacterEntity>>
     */
    @Override
    public Flowable<List<CharacterEntity>> getFavoriteCharacters() {
        return characterDisplayLocalDS.getFavoriteCharacters();
    }
}
