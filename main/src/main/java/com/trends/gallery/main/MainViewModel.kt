package com.trends.gallery.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.trends.gallery.data.repository.PhotoRepository

/**
 * Created by Hisham Sanimeh
 */
class MainViewModel @ViewModelInject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel(), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {

    }

}
