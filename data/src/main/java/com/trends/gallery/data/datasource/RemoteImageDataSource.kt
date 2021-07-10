package com.trends.gallery.data.datasource

import com.trends.gallery.data.network.response.RemotePhoto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Created by Hisham Sanimeh
 */
interface RemoteImageDataSource {

    @GET("photos")
    suspend fun getPhotos(@QueryMap query: Map<String, @JvmSuppressWildcards Any>?): List<RemotePhoto>


    @GET("photos/{id}")
    suspend fun getPhoto(@Path("id") id: String): RemotePhoto
}
