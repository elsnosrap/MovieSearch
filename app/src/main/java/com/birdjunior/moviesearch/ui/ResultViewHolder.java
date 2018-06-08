package com.birdjunior.moviesearch.ui;

import android.support.v7.widget.RecyclerView;

import com.birdjunior.moviesearch.databinding.ResultBinding;

public class ResultViewHolder extends RecyclerView.ViewHolder {

    ResultBinding binding;

    ResultViewHolder(ResultBinding binding) {
        super(binding.resultView);
        this.binding = binding;
    }
}
