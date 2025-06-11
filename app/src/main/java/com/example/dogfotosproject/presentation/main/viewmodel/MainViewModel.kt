package com.example.dogfotosproject.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dogfotosproject.domain.usecase.GetRandomDogPhotosUseCase
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch





class MainViewModel(private val getRandomDogPhotosUseCase: GetRandomDogPhotosUseCase) : ViewModel() {

    private val _photos = MutableLiveData<List<String>>()
    val photos: LiveData<List<String>> = _photos

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading



    init {
        loadPhotos()
    }

    fun loadPhotos() {
        if (_isLoading.value == true) return

        _isLoading.value = true

        viewModelScope.launch {
            try {
                val newPhotos = getRandomDogPhotosUseCase()
                val current = _photos.value ?: emptyList()
                _photos.postValue(current + newPhotos)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.postValue(false)
            }
        }


    }
}