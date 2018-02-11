package com.fcorcino.doit.extensions

import android.support.v4.app.Fragment
import android.widget.Toast

fun Fragment.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    activity.toast(message, length)
}
