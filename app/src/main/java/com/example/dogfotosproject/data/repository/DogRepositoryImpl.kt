package com.example.dogfotosproject.data.repository

import com.example.dogfotosproject.data.api.DogApiService
import com.example.dogfotosproject.domain.repository.DogRepository

class DogRepositoryImpl( private val apiService: DogApiService) : DogRepository {
    override suspend fun getRandomDogPhotos(): List<String> {
        val response = apiService.getRandomDogPhotos()
        if (response.status == "success") {
            return response.message
        } else {
            throw Exception("Ошибка загрузки фотографий")
        }
    }
}