package com.fcorcino.doit.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

fun View.show(display: Boolean = true) {
    visibility = if (display) View.VISIBLE else View.GONE
}

val EditText.value
    get() = text.toString()

fun EditText.clear() {
    text.clear()
}

fun EditText.onTextChange(callback: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            callback(s.toString())
        }
    })
}