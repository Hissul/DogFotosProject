package com.example.dogfotosproject.data.repository

import android.util.Log
import com.example.dogfotosproject.data.api.DogApiService
import com.example.dogfotosproject.data.db.entity.FavoriteDogEntity
import com.example.dogfotosproject.domain.repository.DogRepository
import com.example.dogfotosproject.data.db.dao.*

class DogRepositoryImpl(private val apiService: DogApiService, private val dogDao: DogDao) :
    DogRepository {

    override suspend fun getRandomDogPhotos(): List<String> {
        val response = apiService.getRandomDogPhotos()
        if (response.status == "success") {
            return response.message
        } else {
            throw Exception("Ошибка загрузки фотографий")
        }
    }

    override suspend fun addPhotoToFavorites(photoUrl: String, userId: Int): Boolean {
        val result = dogDao.insertFavorite(FavoriteDogEntity(imageUrl = photoUrl, userId = userId))
        return result != -1L
    }
}