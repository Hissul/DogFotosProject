package com.example.dogfotosproject.data.repository

import com.example.dogfotosproject.data.db.dao.UserDao
import com.example.dogfotosproject.data.db.entity.UserEntity
import com.example.dogfotosproject.domain.repository.UserRepository


class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {

    override suspend fun registerUser(user: UserEntity) {
        userDao.register(user)
    }

    override suspend fun loginUser(login: String, password: String): UserEntity? {
        return userDao.login(login, password)
    }
}