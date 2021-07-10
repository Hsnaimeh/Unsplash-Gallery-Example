package com.trends.gallery.di.network

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.trends.gallery.data.BuildConfig
import com.trends.gallery.data.network.moshi.MoshiAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


/**
 * Created by Hisham Sanimeh.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_MINUTES = 1L

    @Provides
    @Singleton
    fun provideOkHttp(
        interceptors: Set<@JvmSuppressWildcards Interceptor>,
        application: Application
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(TIMEOUT_MINUTES, TimeUnit.MINUTES)
            .writeTimeout(TIMEOUT_MINUTES, TimeUnit.MINUTES)
            .connectTimeout(TIMEOUT_MINUTES, TimeUnit.MINUTES)

        interceptors.forEach { builder.addInterceptor(it) }

        val networkFlipperPlugin = AndroidFlipperClient.getInstance(application)
            .getPlugin<NetworkFlipperPlugin>(NetworkFlipperPlugin.ID)
        builder.addInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin, true))
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.APP_API_BASE)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(moshi: Moshi): Converter.Factory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun provideMoshi(adapters: Set<@JvmSuppressWildcards MoshiAdapter>): Moshi {
        val builder = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
        adapters.forEach { builder.add(it) }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideIoDispatcher(): CoroutineContext {
        return Dispatchers.IO
    }
}
