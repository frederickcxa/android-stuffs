package com.fcorcino.shoppingcart.utils

import android.app.Activity
import android.support.v4.app.Fragment
import android.widget.Toast

fun Float.asPrice(digits: Int = 2): String = java.lang.String.format("$%,.${digits}f", this)

fun Activity.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun Fragment.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    activity.toast(message, length)
}
