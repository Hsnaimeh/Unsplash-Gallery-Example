package com.trends.gallery.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Hisham Sanimeh.
 */
@JsonClass(generateAdapter = true)
data class RemoteUrls(

    @Json(name = "raw")
    val raw: String,

    @Json(name = "full")
    val full: String,

    @Json(name = "regular")
    val regular: String,

    @Json(name = "small")
    val small: String,

    @Json(name = "thumb")
    val thumb: String

)