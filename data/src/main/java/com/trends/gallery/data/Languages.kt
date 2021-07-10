package com.trends.gallery.data

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.trends.gallery.data.prefs.Language
import com.trends.gallery.data.prefs.PrefsImpl
import java.util.*

object Languages {

    fun setLocale(context: Context?): Context? {
        return updateResources(context, getLanguage(context).value)
    }

    fun setNewLocale(context: Context?, language: String): Context? {
        return updateResources(context, language)
    }

    fun getLanguage(c: Context?): Language {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(c)
        val prefs = PrefsImpl(sharedPreferences, Gson())
        return prefs.language
    }

    private fun updateResources(context: Context?, language: String): Context? {
        var result = context
        val locale = Locale(language)
        Locale.setDefault(locale)
        val res = context?.resources
        val config = Configuration(res?.configuration)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale)
            result = context?.createConfigurationContext(config)
        } else {
            config.locale = locale
            res?.updateConfiguration(config, res.displayMetrics)
        }
        return result
    }
}
