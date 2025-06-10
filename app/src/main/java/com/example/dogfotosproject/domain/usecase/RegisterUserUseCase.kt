package com.example.dogfotosproject.domain.usecase

import com.example.dogfotosproject.data.db.entity.UserEntity
import com.example.dogfotosproject.data.repository.UserRepository

class RegisterUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(user: UserEntity) {
        repository.registerUser(user)
    }
}