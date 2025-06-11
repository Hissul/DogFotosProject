package com.example.dogfotosproject.domain.repository

interface DogRepository {
    suspend fun getRandomDogPhotos(): List<String>
    suspend fun addPhotoToFavorites(photoUrl: String, userId: Int): Boolean
}