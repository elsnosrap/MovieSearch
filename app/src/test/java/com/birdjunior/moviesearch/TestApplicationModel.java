package com.birdjunior.moviesearch;

import android.app.Application;
import android.support.annotation.NonNull;

import com.birdjunior.moviesearch.network.ApiServiceFactory;
import com.birdjunior.moviesearch.network.OkHttpFactory;

import rx.schedulers.Schedulers;

public class TestApplicationModel extends ApplicationModel {

    @Override
    protected void initialize(Application application) {
        // Create instance of OkHttpClient
        okHttpClient = OkHttpFactory.getClient(application);

        // Make schedulers run immediately
        backgroundScheduler = Schedulers.immediate();
        observeOnScheduler = Schedulers.immediate();
    }

    public void initializeApiServiceFactory(@NonNull String baseUrl) {
        // Initialize the ApiServiceFactory with a given URL.
        apiService = ApiServiceFactory.create(okHttpClient, baseUrl);
    }
}
