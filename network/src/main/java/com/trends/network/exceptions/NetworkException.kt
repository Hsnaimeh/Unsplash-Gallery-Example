package com.trends.gallery.network.exceptions

/**
 * Created by Hisham Sanimeh.
 */
abstract class NetworkException(
    throwable: Throwable,
    message: String = throwable.message ?: ""
) : RuntimeException(message, throwable)
