package com.birdjunior.moviesearch.network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.birdjunior.moviesearch.ApplicationModel;
import com.birdjunior.moviesearch.MovieSearchApplication;
import com.birdjunior.moviesearch.models.Result;
import com.birdjunior.moviesearch.models.SearchResults;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

public class SearchStore {

    private DataListener listener;
    private ApiService apiService;
    private ApplicationModel applicationModel;
    private Subscription subscription;

    public SearchStore(DataListener listener, Context context) {
        this.listener = listener;
        applicationModel = MovieSearchApplication.getModel(context);
        apiService = applicationModel.getApiService();
    }

    public void performSearch(@NonNull String query) {
        // If an existing request is outstanding, don't process it
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        subscription = apiService.performSearch(query, 1)
                .observeOn(applicationModel.getObserveOnScheduler())
                .subscribeOn(applicationModel.getBackgroundScheduler())
                .subscribe(new Subscriber<SearchResults>() {
                    private SearchResults results;

                    @Override
                    public void onCompleted() {
                        if (results.getTotalResults() <= 0) {
                            listener.onNoResults();
                        } else {
                            listener.onItems(results.getSearchResults());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onNoResults();
                    }

                    @Override
                    public void onNext(SearchResults searchResults) {
                        this.results = searchResults;
                    }
                });
    }

    public interface DataListener {
        void onItems(@NonNull List<Result> results);

        void onNoResults();
    }
}
