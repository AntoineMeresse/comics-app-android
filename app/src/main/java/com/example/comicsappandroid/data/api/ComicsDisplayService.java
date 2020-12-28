package com.example.comicsappandroid.data.api;

import com.example.comicsappandroid.data.api.models.Characters;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ComicsDisplayService {
    @GET("characters")
    Single<Characters> searchCharacters(@Query("api_key") String apiKey);
}
