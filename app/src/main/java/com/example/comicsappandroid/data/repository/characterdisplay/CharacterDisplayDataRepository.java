package com.example.comicsappandroid.data.repository.characterdisplay;

import com.example.comicsappandroid.data.api.models.CharacterSearchResponse;
import com.example.comicsappandroid.data.database.CharacterEntity;
import com.example.comicsappandroid.data.repository.characterdisplay.local.CharacterDisplayLocalDS;
import com.example.comicsappandroid.data.repository.characterdisplay.mapper.MapperCharacterToCharacterEntity;
import com.example.comicsappandroid.data.repository.characterdisplay.remote.CharacterDisplayRemoteDS;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
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

    @Override
    public Single<CharacterSearchResponse> getSearchResponse(String filter) {
        return characterDisplayRemoteDS.searchCharacters(filter);
    }

    // ---------------------------------- Local ------------------------------------------------

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
                return MapperCharacterToCharacterEntity.convertCharacterToCharacterEntity(characterSearchResponse);
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

    @Override
    public Completable deleteCharacter(String id) {
        return null;
    }

    @Override
    public Flowable<List<CharacterEntity>> getFavoriteCharacters() {
        return null;
    }
}
