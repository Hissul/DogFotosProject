package com.example.dogfotosproject.domain.usecase

import com.example.dogfotosproject.domain.repository.DogRepository

class GetRandomDogPhotosUseCase(private val repository: DogRepository) {
    suspend operator fun invoke(): List<String> {
        return repository.getRandomDogPhotos()
    }
}