package com.trends.gallery.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.trends.gallery.data.model.Photo
import com.trends.gallery.data.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Hisham Sanimeh
 */
class HomeViewModel @ViewModelInject constructor(
    private val photoRepository: PhotoRepository,
) : ViewModel(), LifecycleObserver {

    val photos = mutableListOf<Photo>()
    private var isRefresh = false
    private var pageNumber = 1
    var isLoading: Boolean = false
    var hasLoadedAllItems: Boolean = false

    private val _progressLiveData = MutableLiveData<Boolean>()
    val progressLiveData: LiveData<Boolean> = _progressLiveData

    private val _successLiveData = MutableLiveData<List<Photo>>()
    val successLiveData: LiveData<List<Photo>> = _successLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> = _errorLiveData

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        getPhotos()
    }

    private fun getPhotos() {
        isLoading = true
        viewModelScope.launch(Dispatchers.IO) {
            _progressLiveData.postValue(true)
            try {
                val photos = photoRepository.getPhotos(pageNumber, 10)
                pageNumber++
                if (isRefresh) {
                    this@HomeViewModel.photos.clear()
                    isRefresh = false
                }
                this@HomeViewModel.photos.addAll(photos)
                _successLiveData.postValue(photos)
                hasLoadedAllItems = photos.isEmpty()
            } catch (throwable: Throwable) {
                _errorLiveData.postValue(throwable)
            }
            _progressLiveData.postValue(false)
            isLoading = false
        }
    }

    fun refreshPhotos() {
        pageNumber = 1
        isRefresh = true
        getPhotos()
    }

    fun onLoadMore() {
        getPhotos()
    }


}
