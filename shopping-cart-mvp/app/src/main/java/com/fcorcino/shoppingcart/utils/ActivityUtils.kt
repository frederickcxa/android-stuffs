package com.fcorcino.shoppingcart.utils

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.replaceFragment(container: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .commit()
}
