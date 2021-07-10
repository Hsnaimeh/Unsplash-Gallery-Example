package com.trends.gallery.network.exceptions

import com.squareup.moshi.Json

/**
 * Created by Hisham Sanimeh.
 */
class ServerException(
    throwable: Throwable,
    val serverError: ServerError,
    val statusCode: Int,
    message: String
) : NetworkException(throwable, message)

data class ServerError(

    @Json(name = "status")
    val status: Boolean?,

    @Json(name = "message")
    val message: String?,

    @Json(name = "errors")
    val errors: List<String>
) {

    fun getErrorMessage() = if (errors.isEmpty()) {
        message ?: ""
    } else {
        errors.joinToString { it }
    }
}
