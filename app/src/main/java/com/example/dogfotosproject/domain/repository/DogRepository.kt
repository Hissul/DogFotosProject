package com.example.dogfotosproject.domain.repository

import com.example.dogfotosproject.data.db.entity.FavoriteDogEntity
import kotlinx.coroutines.flow.Flow

interface DogRepository {
    suspend fun getRandomDogPhotos(): List<String>
    suspend fun addPhotoToFavorites(photoUrl: String, userId: Int): Boolean
    fun getFavorites(userId: Int): Flow<List<FavoriteDogEntity>>
}