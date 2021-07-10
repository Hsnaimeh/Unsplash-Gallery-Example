package com.facebook.flipper.plugins.crashreporter

import com.facebook.flipper.core.FlipperPlugin

object CrashReporterPlugin : FlipperPlugin {

    @JvmStatic
    fun getInstance(): CrashReporterPlugin {
        return CrashReporterPlugin
    }
}
