package com.trends.gallery.di.data

import android.app.Application
import android.content.SharedPreferences
import android.location.Geocoder
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.trends.gallery.data.prefs.Prefs
import com.trends.gallery.data.prefs.PrefsImpl
import com.trends.gallery.data.qualifiers.BaseUrl
import com.trends.gallery.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

/**
 * Created by Hisham Sanimeh.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun providePrefs(sharedPreferences: SharedPreferences, gson: Gson): Prefs {
        return PrefsImpl(sharedPreferences, gson)
    }

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String {
        return BuildConfig.APP_API_BASE
    }


    @Provides
    @Singleton
    fun provideGeoCoder(application: Application): Geocoder {
        return Geocoder(application, Locale.getDefault())
    }


}
