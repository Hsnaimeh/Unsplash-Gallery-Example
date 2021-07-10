package com.trends.gallery.data.network.moshi.date

import com.trends.gallery.data.network.moshi.MoshiAdapter
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Hisham Sanimeh.
 */
@Singleton
class DateAdapter @Inject constructor() : MoshiAdapter {

    @ToJson
    fun toJson(@ToDate value: Date?): String? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH)
        return if (value != null) {
            simpleDateFormat.format(value)
        } else {
            null
        }
    }

    @FromJson
    @ToDate
    fun fromJson(value: String?): Date? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH)
        return if (value != null) {
            simpleDateFormat.parse(value)
        } else {
            null
        }
    }
}
