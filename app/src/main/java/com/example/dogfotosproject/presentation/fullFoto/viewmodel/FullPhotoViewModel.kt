package com.example.dogfotosproject.presentation.fullFoto.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogfotosproject.data.local.UserSessionManager
import com.example.dogfotosproject.domain.repository.UserRepository
import com.example.dogfotosproject.domain.usecase.AddToFavoritesUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class FullPhotoViewModel(
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val userRepository: UserRepository,
    private val userSessionManager: UserSessionManager
) : ViewModel() {

    private val _favoriteState = MutableLiveData<Boolean>()
    val favoriteState: LiveData<Boolean> = _favoriteState

    fun addToFavorites(photoUrl: String) {
        viewModelScope.launch {
            val login = userSessionManager.getUserLogin().first()

            if (login != null) {
                val user = userRepository.getUserByLogin(login)
                user?.let {
                    addToFavoritesUseCase(photoUrl, it.id)
                    _favoriteState.postValue(true)
                }
            }
        }
    }
}