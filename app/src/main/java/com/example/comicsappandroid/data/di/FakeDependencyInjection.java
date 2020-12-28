package com.example.comicsappandroid.data.di;

import android.content.Context;

import com.google.gson.Gson;

/**
 * This FakeDependencyInjection is inspired from our Android Class Tutorial.
 * This will be used for now and replace if I have time with real dependency injection framework.
 */
public class FakeDependencyInjection {

    private static Context context;
    private static Gson jsonSerializer;

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

}
