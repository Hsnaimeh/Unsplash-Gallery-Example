package com.facebook.flipper.core

/**
 * Created by Hisham Sanimeh.
 */
interface FlipperClient {

    fun addPlugin(plugin: FlipperPlugin)

    fun start()

    fun <T : FlipperPlugin> getPlugin(id: String): T?
}
