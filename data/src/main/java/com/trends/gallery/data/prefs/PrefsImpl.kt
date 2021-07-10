package com.trends.gallery.data.prefs

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson

/**
 * Created by Hisham Sanimeh.
 */
class PrefsImpl(private val sharedPreferences: SharedPreferences, private val gson: Gson) : Prefs {

    override var accessToken: String?
        get() = sharedPreferences.getString(PrefKey.ACCESS_TOKEN.name, null)
        set(value) {
            sharedPreferences.edit { putString(PrefKey.ACCESS_TOKEN.name, value) }
        }

    override var language: Language
        get() = Language.valueOf(
            sharedPreferences.getString(
                PrefKey.LANGUAGE.name,
                Language.ARABIC.name
            )!!
        )
        set(value) {
            sharedPreferences.edit { putString(PrefKey.LANGUAGE.name, value.name) }
        }


    enum class PrefKey {

        ACCESS_TOKEN,
        LANGUAGE

    }
}
