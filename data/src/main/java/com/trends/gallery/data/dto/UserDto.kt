package com.trends.gallery.data.dto

import com.trends.gallery.data.model.User
import com.trends.gallery.data.network.response.RemoteUser

/**
 * Created by Hisham Sanimeh.
 */
fun RemoteUser.toUser(): User {
    return User(
        id = id,
        username = username,
        profile_image = profile_image
    )

}