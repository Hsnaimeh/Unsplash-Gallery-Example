package com.trends.gallery.data.dto

import com.trends.gallery.data.model.Photo
import com.trends.gallery.data.network.response.RemotePhoto

/**
 * Created by Hisham Sanimeh.
 */

fun RemotePhoto.toPhoto(): Photo {
    return Photo(
        id = id,
        createdAt = createdAt,
        updatedAt = updatedAt,
        width = width,
        height = height,
        color = color,
        likes = likes,
        description = description,
        urls = urls.toUrls(),
        user = user?.toUser()
    )
}
