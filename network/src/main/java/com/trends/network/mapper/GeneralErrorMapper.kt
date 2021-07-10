package com.trends.gallery.network.mapper

import com.trends.gallery.network.exceptions.NetworkException
import com.trends.gallery.network.exceptions.UnexpectedException

/**
 * Created by Hisham Sanimeh.
 */
class GeneralErrorMapper : ErrorMapper() {

    override fun handle(throwable: Throwable): NetworkException {
        return UnexpectedException(throwable)
    }
}
