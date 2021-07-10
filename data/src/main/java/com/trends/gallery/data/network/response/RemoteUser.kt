package com.trends.gallery.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteUser(

    @Json(name = "id")
    val id: String,

    @Json(name = "updated_at")
    val updated_at: String?,

    @Json(name = "username")
    val username: String?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "first_name")
    val first_name: String?,

    @Json(name = "last_name")
    val last_name: String?,

    @Json(name = "instagram_username")
    val instagram_username: String?,

    @Json(name = "twitter_username")
    val twitter_username: String?,

    @Json(name = "portfolio_url")
    val portfolio_url: String?,

    @Json(name = "bio")
    val bio: String?,

    @Json(name = "total_likes")
    val total_likes: Int?,

    @Json(name = "total_photos")
    val total_photos: Int?,

    @Json(name = "total_collections")
    val total_collections: Int?,

    @Json(name = "profile_image")
    val profile_image: RemoteProfileImage?,

    @Json(name = "links")
    val links: RemoteLinks?,

    )



