package com.birdjunior.moviesearch.utils;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.birdjunior.moviesearch.R;
import com.bumptech.glide.Glide;

public final class ImageBindingUtil {

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView imageView, final String url) {
        if (!TextUtils.isEmpty(url)) {
            Uri uri = Uri.parse(url);
            Glide.with(imageView.getContext())
                    .load(uri)
                    .placeholder(R.drawable.placeholder)
                    .into(imageView);
        }
    }
}
