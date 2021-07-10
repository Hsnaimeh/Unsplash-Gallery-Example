package com.trends.gallery.data.dto

import com.trends.gallery.data.model.Urls
import com.trends.gallery.data.network.response.RemoteUrls

/**
 * Created by Hisham Sanimeh.
 */

fun RemoteUrls.toUrls(): Urls {
    return Urls(
        raw = raw,
        full = full,
        regular = regular,
        small = small,
        thumb = thumb
    )
}

