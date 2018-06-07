package com.birdjunior.moviesearch;

import android.app.Application;

import com.birdjunior.moviesearch.network.ApiService;
import com.birdjunior.moviesearch.network.ApiServiceFactory;
import com.birdjunior.moviesearch.network.OkHttpFactory;

import okhttp3.OkHttpClient;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Application Model is used in lieu of keeping most code in the Application class itself so we can override some of
 * these things in our unit tests.
 */
@SuppressWarnings("WeakerAccess")
public class ApplicationModel {

    protected ApiService apiService;
    protected Scheduler backgroundScheduler;
    protected Scheduler observeOnScheduler;
    protected OkHttpClient okHttpClient;

    protected void initialize(final Application application) {
        // Create instance of OkHttpClient used for all network calls
        okHttpClient = OkHttpFactory.getClient(application);

        // Create schedulers used for Rx calls
        backgroundScheduler = Schedulers.io();
        observeOnScheduler = AndroidSchedulers.mainThread();

        // Create Retrofit instance used to make API calls
        apiService = ApiServiceFactory.create(okHttpClient, BuildConfig.API_BASE_URL);
    }

    public ApiService getApiService() {
        return apiService;
    }

    public Scheduler getBackgroundScheduler() {
        return backgroundScheduler;
    }

    public Scheduler getObserveOnScheduler() {
        return observeOnScheduler;
    }
}
