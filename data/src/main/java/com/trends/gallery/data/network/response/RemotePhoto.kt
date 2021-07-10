package com.trends.gallery.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemotePhoto(

    @Json(name = "id")
    val id: String,

    @Json(name = "created_at")
    val createdAt: String?,

    @Json(name = "updated_at")
    val updatedAt: String?,

    @Json(name = "width")
    val width: Int?,

    @Json(name = "height")
    val height: Int?,

    @Json(name = "color")
    val color: String?,

    @Json(name = "blur_hash")
    val blurHash: String?,

    @Json(name = "likes")
    val likes: Int?,

    @Json(name = "liked_by_user")
    var likedByUser: Boolean?,

    @Json(name = "description")
    val description: String?,

    @Json(name = "alt_description")
    val altDescription: String?,

    @Json(name = "urls")
    val urls: RemoteUrls,

    @Json(name = "links")
    val links: RemoteLinks?,

    @Json(name = "user")
    val user: RemoteUser?

)



