package com.example.dogfotosproject.domain.usecase

import com.example.dogfotosproject.data.db.entity.FavoriteDogEntity
import com.example.dogfotosproject.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritesUseCase(private val dogRepository: DogRepository) {
    operator fun invoke(userId: Int): Flow<List<FavoriteDogEntity>> {
        return dogRepository.getFavorites(userId)
    }
}