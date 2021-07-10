package com.trends.gallery.di.network

import com.trends.gallery.data.network.moshi.MoshiAdapter
import com.trends.gallery.data.network.moshi.color.HexColorAdapter
import com.trends.gallery.data.network.moshi.date.DateAdapter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

/**
 * Created by Hisham Sanimeh.
 */
@Module
@InstallIn(SingletonComponent::class)
interface MoshiAdaptersModule {

    @Binds
    @IntoSet
    fun bindDateAdapter(adapter: DateAdapter): MoshiAdapter

    @Binds
    @IntoSet
    fun bindHexColorAdapter(adapter: HexColorAdapter): MoshiAdapter

}
