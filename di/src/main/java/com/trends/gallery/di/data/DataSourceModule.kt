package com.trends.gallery.di.data

import com.trends.gallery.data.datasource.RemoteImageDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Hisham Sanimeh.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteImageDataSource(retrofit: Retrofit): RemoteImageDataSource {
        return retrofit.create(RemoteImageDataSource::class.java)
    }

}
