package com.birdjunior.moviesearch.ui;

import android.databinding.BaseObservable;
import android.text.TextUtils;
import android.view.View;

import com.birdjunior.moviesearch.models.Result;

public class ResultViewModel extends BaseObservable {
    private Result result;

    ResultViewModel(Result result) {
        this.result = result;
    }

    public String getTitle() {
        return result.getTitle();
    }

    public int getTitleVisibility() {
        return TextUtils.isEmpty(result.getTitle()) ? View.GONE : View.VISIBLE;
    }
}
