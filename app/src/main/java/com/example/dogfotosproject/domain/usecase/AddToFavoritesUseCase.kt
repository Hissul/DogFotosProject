package com.example.dogfotosproject.domain.usecase

import com.example.dogfotosproject.domain.repository.DogRepository

class AddToFavoritesUseCase(private val dogRepository: DogRepository) {
    suspend operator fun invoke(photoUrl: String, userId: Int): Boolean {
        return dogRepository.addPhotoToFavorites(photoUrl, userId)
    }
}