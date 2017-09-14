package com.fcorcino.shoppingcart.utils

import android.support.v4.app.Fragment

fun Fragment.runOnUI(block: () -> Unit) {
    if (isAdded) activity.runOnUiThread(block)
}
