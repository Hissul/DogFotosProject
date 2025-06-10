package com.example.dogfotosproject.domain.repository

interface DogRepository {
    suspend fun getRandomDogPhotos(): List<String>
}