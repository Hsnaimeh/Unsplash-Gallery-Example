package com.trends.gallery.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Hisham Sanimeh.
 */
@JsonClass(generateAdapter = true)
data class RemoteLinks(

    @Json(name = "self")
    val self: String,

    @Json(name = "html")
    val html: String,

    @Json(name = "download")
    val download: String?,

    @Json(name = "download_location")
    val download_location: String?
)