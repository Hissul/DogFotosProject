package com.example.dogfotosproject.data.repository

import com.example.dogfotosproject.data.db.entity.UserEntity

interface UserRepository {
    suspend fun registerUser(user: UserEntity)
    suspend fun loginUser(login: String, password: String): UserEntity?
}