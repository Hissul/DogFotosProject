package com.example.dogfotosproject.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dogfotosproject.domain.usecase.GetRandomDogPhotosUseCase
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MainViewModel( private val getPhotosUseCase: GetRandomDogPhotosUseCase) : ViewModel() {

    private val _photos = MutableLiveData<List<String>>()
    val photos: LiveData<List<String>> get() = _photos

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun loadPhotos() {
        viewModelScope.launch {
            try {
                _photos.value = getPhotosUseCase()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}