package com.example.comicsappandroid;

import android.app.Application;

import com.example.comicsappandroid.data.di.FakeDependencyInjection;
import com.facebook.stetho.Stetho;

/**
 * Class to initialize fake dependency injection and setup stetho to inspect http request
 * and local database.
 */
public class CharacterComicsApplication extends Application {

    public static final String API_KEY = "5a80f16fd8a7c44adfa84596f9fa2fd0df94858c";

    // URL
    public static final String API_URL = "https://comicvine.gamespot.com/api/";
    public static final String API_FORMAT = "json";

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection.setContext(this);
    }
}