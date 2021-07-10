package com.trends.gallery.data.network.interceptor

import android.app.Application
import com.trends.gallery.data.BuildConfig
import com.trends.gallery.data.prefs.Prefs
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by Hisham Sanimeh.
 */
class AppInterceptor @Inject constructor(
    private val application: Application,
    private val prefs: Prefs
) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        builder.addHeader("os", "Android")
        builder.addHeader("X-Accept-Version", "1.0")
        builder.addHeader("Authorization","Client-ID ${BuildConfig.UNSPLASH_ACCESS_KEY}")

        return chain.proceed(builder.build())
    }
}
