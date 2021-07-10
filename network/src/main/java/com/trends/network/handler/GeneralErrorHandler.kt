package com.trends.gallery.network.handler

import android.content.Context
import com.trends.gallery.network.exceptions.NoConnectivityException
import com.trends.gallery.network.exceptions.ServerException
import com.trends.gallery.network.mapper.GeneralErrorMapper
import com.trends.gallery.network.R

/**
 * Created by Hisham Sanimeh.
 */
class GeneralErrorHandler(private val context: Context, private val errorMapper: GeneralErrorMapper) {

    fun getMessage(throwable: Throwable): String {
        return when (val mapped = errorMapper.map(throwable)) {
            is NoConnectivityException -> {
                context.getString(R.string.message_no_internet_connection)
            }
            is ServerException -> {
                mapped.serverError.getErrorMessage()
            }
            else -> {
                context.getString(R.string.message_something_went_wrong)
            }
        }
    }

    fun getTitle(throwable: Throwable): String {
        return when (errorMapper.map(throwable)) {
            is NoConnectivityException -> {
                context.getString(R.string.title_no_internet_connection)
            }
            else -> {
                context.getString(R.string.title_something_went_wrong)
            }
        }
    }
}
