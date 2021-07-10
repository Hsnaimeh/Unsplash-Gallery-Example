package com.trends.gallery.network.handler

/**
 * Created by Hisham Sanimeh.
 */
class ErrorHandler {

    fun handle(throwable: Throwable): ErrorCatcher {
        return ErrorCatcher(throwable)
    }
}
