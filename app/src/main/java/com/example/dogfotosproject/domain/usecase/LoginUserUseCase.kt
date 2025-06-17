package com.example.dogfotosproject.domain.usecase

import com.example.dogfotosproject.data.db.entity.UserEntity
import com.example.dogfotosproject.domain.repository.UserRepository

class LoginUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(login: String, password: String): UserEntity? {
        return repository.loginUser(login, password)
    }
}