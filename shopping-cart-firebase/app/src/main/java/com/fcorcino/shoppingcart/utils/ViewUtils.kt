package com.fcorcino.shoppingcart.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.fcorcino.shoppingcart.R

fun View.show(display: Boolean = true) {
    visibility = if (display) View.VISIBLE else View.GONE
}

fun ImageView.load(url: String) {
    Glide.with(context)
            .load(url)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(this)
}
