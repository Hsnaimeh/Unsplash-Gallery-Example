package com.trends.gallery.data.model

import com.squareup.moshi.JsonClass
import com.trends.gallery.data.network.response.RemoteProfileImage

@JsonClass(generateAdapter = true)
data class User(

    val id: String,

    val username: String?,

    val profile_image: RemoteProfileImage?,

    )



