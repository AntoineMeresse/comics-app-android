package com.example.comicsappandroid.data.di;

import android.content.Context;

import androidx.room.Room;

import com.example.comicsappandroid.CharacterComicsApplication;
import com.example.comicsappandroid.data.api.ComicsDisplayService;
import com.example.comicsappandroid.data.database.CharacterDatabase;
import com.example.comicsappandroid.data.repository.characterdisplay.CharacterDisplayDataRepository;
import com.example.comicsappandroid.data.repository.characterdisplay.CharacterDisplayRepository;
import com.example.comicsappandroid.data.repository.characterdisplay.local.CharacterDisplayLocalDS;
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
 * It allows us to fake the usage of a Dependency injection such as Dagger2
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

    /**
     * Method to get a retrofit instance
     * @return Retrofit
     */
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

    /**
     * Method to get the ComicsDisplayService
     * @return ComicsDisplayService
     */
    public static ComicsDisplayService getComicsDisplayService() {
        if (comicsDisplayService == null) comicsDisplayService = getRetrofit().create(ComicsDisplayService.class);
        return comicsDisplayService;
    }

    /**
     * Method to get the CharacterDisplayRepository
     * @return CharacterDisplayRepository
     */
    public static CharacterDisplayRepository getCharacterDisplayRepository(){
        if (characterDisplayRepository == null) {
            characterDisplayRepository = new CharacterDisplayDataRepository(
                    new CharacterDisplayRemoteDS(getComicsDisplayService()),
                    new CharacterDisplayLocalDS(getCharacterDatabase())
            );
        }
        return characterDisplayRepository;
    }

    /**
     * Method to get the ViewModelFactory
     * @return ViewModelFactory
     */
    public static ViewModelFactory getViewModelFactory(){
        if (viewModelFactory == null) viewModelFactory = new ViewModelFactory(getCharacterDisplayRepository());
        return viewModelFactory;
    }

    /**
     * Method to get the CharacterDatabase
     * @return CharacterDatabase
     */
    public static CharacterDatabase getCharacterDatabase() {
        if (characterDatabase == null) {
            characterDatabase = Room.databaseBuilder(context, CharacterDatabase.class, "character-database").build();
        }
        return characterDatabase;
    }

}
