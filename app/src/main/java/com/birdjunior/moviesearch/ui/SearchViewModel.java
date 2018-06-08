package com.birdjunior.moviesearch.ui;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.birdjunior.moviesearch.BR;
import com.birdjunior.moviesearch.R;
import com.birdjunior.moviesearch.models.Result;
import com.birdjunior.moviesearch.network.SearchStore;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class SearchViewModel extends BaseObservable implements SearchStore.DataListener {

    // Custom interface to define search state
    @IntDef({NOT_EMPTY, SEARCH_START, SEARCH_NO_RESULTS, LOADING, ERROR})
    @Retention(RetentionPolicy.SOURCE)
    @interface EmptyState {
    }

    static final int NOT_EMPTY = 0;
    static final int SEARCH_START = 1;
    static final int SEARCH_NO_RESULTS = 2;
    static final int LOADING = 3;
    static final int ERROR = 4;

    private int emptyState;
    private Resources resources;
    private SearchStore searchStore;
    private SearchStore.DataListener listener;

    SearchViewModel(Context context, @EmptyState int state, SearchStore.DataListener listener) {
        resources = context.getResources();
        emptyState = state;
        this.listener = listener;
        searchStore = new SearchStore(this, context);
    }

    void setEmptyState(@EmptyState int emptyState) {
        this.emptyState = emptyState;
        notifyPropertyChanged(BR.hintText);
        notifyPropertyChanged(BR.hintTextVisibility);
        notifyPropertyChanged(BR.recyclerVisibility);
    }

    void performQuery(String query) {
        if (!TextUtils.isEmpty(query) && query.length() > 2) {
            searchStore.performSearch(query);
        } else {
            setEmptyState(SearchViewModel.SEARCH_START);
        }
    }

    @Bindable
    public int getHintTextVisibility() {
        switch (emptyState) {
            case SEARCH_NO_RESULTS:
            case SEARCH_START:
                return View.VISIBLE;

            default:
                return View.GONE;
        }
    }

    @Bindable
    public int getRecyclerVisibility() {
        switch (emptyState) {
            case NOT_EMPTY:
                return View.VISIBLE;

            default:
                return View.INVISIBLE;
        }
    }

    @Bindable
    public String getHintText() {
        switch (emptyState) {
            case SEARCH_START:
                return resources.getString(R.string.search_start_hint);

            case SEARCH_NO_RESULTS:
                return resources.getString(R.string.no_results);

            default:
                return null;
        }
    }

    @Override
    public void onItems(@NonNull List<Result> results) {
        setEmptyState(NOT_EMPTY);
        listener.onItems(results);
    }

    @Override
    public void onNoResults() {
        setEmptyState(SEARCH_NO_RESULTS);
        listener.onNoResults();
    }

}
