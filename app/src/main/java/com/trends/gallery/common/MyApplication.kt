package com.trends.gallery.common

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import com.trends.gallery.BuildConfig
import com.trends.gallery.core.EnableBackButton
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application(), Application.ActivityLifecycleCallbacks {

    override fun onCreate() {
        super.onCreate()
        initFlipper()
        initActivityLifecycle()
    }

    private fun initFlipper() {
        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.addPlugin(NetworkFlipperPlugin())
            client.addPlugin(DatabasesFlipperPlugin(this))
            client.addPlugin(SharedPreferencesFlipperPlugin(this))
            client.addPlugin(CrashReporterPlugin.getInstance())
            client.start()
        }
    }

    private fun initActivityLifecycle() {
        registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityPaused(activity: Activity) {
        // No impl
    }

    override fun onActivityStarted(activity: Activity) {
        // No impl
    }

    override fun onActivityDestroyed(activity: Activity) {
        // No impl
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // No impl
    }

    override fun onActivityStopped(activity: Activity) {
        // No impl
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        if (activity::class.java.getAnnotation(EnableBackButton::class.java) != null) {
            val appCompatActivity = (activity as? AppCompatActivity?)
            val actionBar = appCompatActivity?.supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onActivityResumed(activity: Activity) {
        // No impl
    }

}
