package com.trends.gallery.data.network.moshi.color

import android.graphics.Color
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.trends.gallery.data.network.moshi.MoshiAdapter
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Hisham Sanimeh.
 */
@Suppress("MagicNumber")
@Singleton
class HexColorAdapter @Inject constructor() : MoshiAdapter {

    @ToJson
    fun toJson(@HexColor color: Int): String {
        return "#%06X".format(0xFFFFFF and color)
    }

    @FromJson
    @HexColor
    fun fromJson(hexColor: String): Int {
        return if (hexColor.startsWith("##")) {
            Color.parseColor(hexColor.removePrefix("#"))
        } else {
            Color.parseColor(hexColor)
        }
    }
}
