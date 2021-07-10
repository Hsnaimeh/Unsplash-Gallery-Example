package com.trends.gallery.network.mapper

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.trends.gallery.network.exceptions.NetworkException
import com.trends.gallery.network.exceptions.NoConnectivityException
import com.trends.gallery.network.exceptions.ServerError
import com.trends.gallery.network.exceptions.ServerException
import com.trends.gallery.network.exceptions.TimeoutException
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by Hisham Sanimeh.
 */
abstract class ErrorMapper {

    private val moshi by lazy { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }
    private val errorsJsonAdapter by lazy { moshi.adapter(ServerError::class.java) }

    fun map(throwable: Throwable): NetworkException {
        return when (throwable) {
            is HttpException -> {
//                FirebaseCrashlytics.getInstance().recordException(throwable)
                val json = throwable.response()?.errorBody()?.string()
                getServerError(throwable, json)
            }
            is SocketTimeoutException -> {
                TimeoutException(throwable)
            }
            is IOException -> {
                NoConnectivityException(throwable)
            }
            else -> {
                if (throwable !is CancellationException) {
//                    FirebaseCrashlytics.getInstance().recordException(throwable)
                }
                handle(throwable)
            }
        }
    }

    private fun getServerError(throwable: HttpException, json: String?): NetworkException {
        return if (json != null) {
            try {
                val errors = errorsJsonAdapter.fromJson(json)!!
                ServerException(throwable, errors, throwable.code(), throwable.message())
            } catch (ignored: Throwable) {
                ServerException(throwable, ServerError(false, "", listOf()), throwable.code(), throwable.message())
            }
        } else {
            ServerException(throwable, ServerError(false, "", listOf()), throwable.code(), throwable.message())
        }
    }

    protected abstract fun handle(throwable: Throwable): NetworkException
}
