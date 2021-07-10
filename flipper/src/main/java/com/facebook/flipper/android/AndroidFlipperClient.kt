package com.facebook.flipper.android

import android.content.Context
import com.facebook.flipper.core.FlipperClient
import com.facebook.flipper.core.FlipperPlugin

class AndroidFlipperClient: FlipperClient {

    override fun addPlugin(plugin: FlipperPlugin) {
        // No impl
    }

    override fun start() {
        // No impl
    }

    override fun <T : FlipperPlugin> getPlugin(id: String): T? {
        return null
    }

    companion object {

        @JvmStatic
        fun getInstance(context: Context): AndroidFlipperClient {
            return AndroidFlipperClient()
        }
    }
}
