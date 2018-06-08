package com.birdjunior.moviesearch.ui;

import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.IntDef;

import com.birdjunior.moviesearch.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SearchViewModel extends BaseObservable {

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

    public SearchViewModel(Resources resources, @EmptyState int state) {
        this.resources = resources;
        emptyState = state;
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
}