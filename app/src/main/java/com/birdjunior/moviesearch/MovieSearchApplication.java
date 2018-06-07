package com.birdjunior.moviesearch;

import android.app.Application;
import android.content.Context;

public class MovieSearchApplication extends Application {

    protected ApplicationModel model;

    @Override
    public void onCreate() {
        super.onCreate();

        model = getModel();
        model.initialize(this);
    }

    /**
     * Static method to simplify getting the ApplicationModel instance
     * @param context A context used to get the application class
     * @return The ApplicationModel instance
     */
    public static ApplicationModel getModel(Context context) {
        return ((MovieSearchApplication)context.getApplicationContext()).model;
    }

    /**
     * Protected so unit test code can override and provide a custom ApplicationModel instance specific to testing.
     * @return The ApplicationModel instance to be used as the singleton instance
     */
    protected ApplicationModel getModel() {
        return new ApplicationModel();
    }
}
