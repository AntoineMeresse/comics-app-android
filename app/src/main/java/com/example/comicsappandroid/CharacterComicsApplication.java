package com.example.comicsappandroid;

import android.app.Application;

public class CharacterComicsApplication extends Application {

    // Don't upload your api key on github
    // I'll init this file with an empty api_key and push it to github.
    // Then it will be ignore in .gitignore
    public static final String API_KEY = "";

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
