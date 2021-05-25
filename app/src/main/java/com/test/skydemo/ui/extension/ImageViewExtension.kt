package com.test.skydemo.ui.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun ImageView.loadRoundedImage(url: String) {
    Glide.with(context)
        .load(url)
        .override(100, 100)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}