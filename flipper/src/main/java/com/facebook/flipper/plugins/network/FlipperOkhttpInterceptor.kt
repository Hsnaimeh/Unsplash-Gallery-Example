package com.facebook.flipper.plugins.network

import okhttp3.Interceptor
import okhttp3.Response

class FlipperOkhttpInterceptor(networkFlipperPlugin: NetworkFlipperPlugin?, boolean: Boolean) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}
