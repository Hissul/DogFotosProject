package com.example.dogfotosproject.presentation.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogfotosproject.data.db.entity.FavoriteDogEntity
import com.example.dogfotosproject.data.local.UserSessionManager
import com.example.dogfotosproject.domain.repository.UserRepository
import com.example.dogfotosproject.domain.usecase.GetFavoritesUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val userRepository: UserRepository,
    private val userSessionManager: UserSessionManager
) : ViewModel() {

    private val _favorites = MutableLiveData<List<FavoriteDogEntity>>()
    val favorites: LiveData<List<FavoriteDogEntity>> = _favorites

    fun loadFavorites() {
        viewModelScope.launch {
            val login = userSessionManager.getUserLogin().first()
            login?.let {
                val user = userRepository.getUserByLogin(it)
                user?.let { u ->
                    getFavoritesUseCase(u.id)
                        .collect { list ->
                            _favorites.postValue(list)
                        }
                }
            }
        }
    }
}