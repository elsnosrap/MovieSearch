package com.birdjunior.moviesearch.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.birdjunior.moviesearch.R;
import com.birdjunior.moviesearch.databinding.ResultBinding;
import com.birdjunior.moviesearch.models.Result;

import java.util.ArrayList;
import java.util.List;

public class ResultsArrayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Result> results;

    public ResultsArrayAdapter() {
        results = new ArrayList<>();
    }

    public void clear() {
        int size = getItemCount();
        results.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void add(List<Result> results) {
        this.results.addAll(results);
        notifyDataSetChanged();
    }

    public Result getItem(int position) {
        return results.get(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ResultBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.result,
                parent,
                false);
        binding.getRoot().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        return new ResultViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ResultViewHolder)holder).binding.setViewModel(new ResultViewModel(getItem(position)));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

}
