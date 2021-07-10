package com.trends.gallery.data.utils

import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Hisham Sanimeh.
 */
const val DEBOUNCE_SEARCH_DELAY = 500L
const val CLICK_DISABLE_DELAY = 1000L

inline fun SearchView.doOnDebounceQueryChange(
    lifecycleOwner: LifecycleOwner,
    crossinline action: (query: String?) -> Unit
) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {

        private var searchFor = ""

        override fun onQueryTextSubmit(query: String?): Boolean {
            action(query)
            clearFocus()
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            val searchText = newText.toString().trim()
            if (searchText == searchFor && searchFor.isNotEmpty()) {
                return true
            }

            searchFor = searchText

            lifecycleOwner.lifecycleScope.launch {
                delay(DEBOUNCE_SEARCH_DELAY)
                if (searchText != searchFor)
                    return@launch
                action(newText)
            }
            return true
        }
    })
}

inline fun EditText.doOnDebounceQueryChange(
    lifecycleOwner: LifecycleOwner,
    crossinline action: (query: String) -> Unit
) {
    var searchFor = ""
    doOnTextChanged { newText, _, _, _ ->
        val searchText = newText.toString().trim()
        if (searchText == searchFor && searchFor.isNotEmpty()) {
            return@doOnTextChanged
        }

        searchFor = searchText

        lifecycleOwner.lifecycleScope.launch {
            delay(DEBOUNCE_SEARCH_DELAY)
            if (searchText != searchFor)
                return@launch
            action(newText.toString())
        }
    }
}



inline fun View.doOnClick(crossinline onClickListener: (View) -> Unit) {
    setOnClickListener {
        isEnabled = false
        onClickListener(it)
        postDelayed({ isEnabled = true }, CLICK_DISABLE_DELAY)
    }
}
