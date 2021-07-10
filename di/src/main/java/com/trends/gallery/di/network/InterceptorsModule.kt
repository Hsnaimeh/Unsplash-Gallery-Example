package com.trends.gallery.di.network

import com.trends.gallery.data.network.interceptor.AppInterceptor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.Interceptor

/**
 * Created by Hisham Sanimeh.
 */
@Module
@InstallIn(SingletonComponent::class)
interface InterceptorsModule {

    @Binds
    @IntoSet
    fun bindAppInterceptor(interceptor: AppInterceptor): Interceptor

}
