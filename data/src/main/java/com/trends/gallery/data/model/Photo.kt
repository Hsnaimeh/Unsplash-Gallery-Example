package com.trends.gallery.data.model

import com.trends.gallery.data.network.response.RemoteUser


data class Photo(

    val id: String,

    val createdAt: String?,

    val updatedAt: String?,

    val width: Int?,

    val height: Int?,

    val color: String?,

    val likes: Int?,

    val description: String?,

    val urls: Urls,

    val user: User?

)
