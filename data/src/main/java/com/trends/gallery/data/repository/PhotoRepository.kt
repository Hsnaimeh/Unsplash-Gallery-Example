package com.trends.gallery.data.repository

import com.trends.gallery.data.datasource.RemoteImageDataSource
import com.trends.gallery.data.dto.toPhoto
import com.trends.gallery.data.model.Photo
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Hisham Sanimeh.
 */
@Singleton
class PhotoRepository @Inject constructor(
    private val remoteDataSource: RemoteImageDataSource
) {

    suspend fun getPhotos(
        page: Int,
        size: Int,
        orderBy: String = "latest",
        keyword: String = "car"
    ): List<Photo> {
        val query = mapOf(
            "page" to page,
            "per_page" to size,
            "order_by" to orderBy,
            "query" to keyword
        )

        return remoteDataSource.getPhotos(query).map { it.toPhoto() }
    }


}