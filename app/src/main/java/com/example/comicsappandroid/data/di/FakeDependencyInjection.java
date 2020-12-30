package com.example.comicsappandroid.data.di;

import android.content.Context;

import androidx.room.Room;

import com.example.comicsappandroid.CharacterComicsApplication;
import com.example.comicsappandroid.data.api.ComicsDisplayService;
import com.example.comicsappandroid.data.database.CharacterDatabase;
import com.example.comicsappandroid.data.repository.characterdisplay.CharacterDisplayDataRepository;
import com.example.comicsappandroid.data.repository.characterdisplay.CharacterDisplayRepository;
import com.example.comicsappandroid.data.repository.characterdisplay.remote.CharacterDisplayRemoteDS;
import com.example.comicsappandroid.presentation.viewmodel.ViewModelFactory;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This FakeDependencyInjection is inspired from our Android Class Tutorial.
 * This will be used for now and replace if I have time with real dependency injection framework.
 */
public class FakeDependencyInjection {

    private static Context context;
    private static Gson jsonSerializer;

    private static ComicsDisplayService comicsDisplayService;
    private static Retrofit retrofit;

    private static CharacterDisplayRepository characterDisplayRepository;
    private static ViewModelFactory viewModelFactory;

    private static CharacterDatabase characterDatabase;

    /**
     * Method to set a context to the FakeDependencyInjection
     * @param c Context
     */
    public static void setContext(Context c) {
        context = c;
    }

    /**
     * Method to get a JSON serializer. In this app we use GSON
     * @return Gson
     */
    public static Gson getJsonSerializer() {
        if (jsonSerializer == null) {
            // Init
            jsonSerializer = new Gson();
        }
        return jsonSerializer;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(CharacterComicsApplication.API_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getJsonSerializer()))
                    .build();
        }
        return retrofit;
    }

    public static ComicsDisplayService getComicsDisplayService() {
        if (comicsDisplayService == null) comicsDisplayService = getRetrofit().create(ComicsDisplayService.class);
        return comicsDisplayService;
    }

    public static CharacterDisplayRepository getCharacterDisplayRepository(){
        if (characterDisplayRepository == null) {
            characterDisplayRepository = new CharacterDisplayDataRepository(
                    new CharacterDisplayRemoteDS(getComicsDisplayService())
            );
        }
        return characterDisplayRepository;
    }

    public static ViewModelFactory getViewModelFactory(){
        if (viewModelFactory == null) viewModelFactory = new ViewModelFactory(getCharacterDisplayRepository());
        return viewModelFactory;
    }

    public static CharacterDatabase getCharacterDatabase() {
        if (characterDatabase == null) {
            characterDatabase = Room.databaseBuilder(context, CharacterDatabase.class, "character-database").build();
        }
        return characterDatabase;
    }

}
