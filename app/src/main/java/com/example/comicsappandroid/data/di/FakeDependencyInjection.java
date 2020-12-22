package com.example.comicsappandroid.data.di;

import android.content.Context;

/**
 * This FakeDependencyInjection is inspired from our Android Class Tutorial.
 * This will be used for now and replace if I have time with real dependency injection framework.
 */
public class FakeDependencyInjection {

    private static Context context;

    /**
     * Method to set a context to the FakeDependencyInjection
     * @param c Context
     */
    public static void setContext(Context c) {
        context = c;
    }

}
