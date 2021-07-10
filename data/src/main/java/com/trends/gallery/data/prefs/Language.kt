package com.trends.gallery.data.prefs

import com.trends.gallery.data.R

/**
 * Created by Hisham Sanimeh.
 */
enum class Language(val value: String, val icon: Int) {

    ARABIC("ar", R.drawable.ic_baseline_language_en),

    ENGLISH("en", R.drawable.ic_baseline_language_ar);

    companion object {

        @JvmStatic
        fun fromValue(value: String): Language {
            return values().firstOrNull { it.value == value } ?: ARABIC
        }
    }
}
