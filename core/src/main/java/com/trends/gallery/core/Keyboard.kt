package com.trends.gallery.core

import android.app.Activity
import android.view.inputmethod.InputMethodManager

/**
 * Created by Hisham Sanimeh.
 */
fun Activity.hideKeyboard() {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
}
