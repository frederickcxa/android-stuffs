package com.fcorcino.shoppingcart.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.fcorcino.shoppingcart.R

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun ImageView.load(url: String) {
    Glide.with(context)
            .load(url)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(this)
}

val View.isVisible
    get() = visibility == View.VISIBLE
