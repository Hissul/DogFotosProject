package com.example.dogfotosproject.di

import com.example.dogfotosproject.data.db.AppDatabase
import com.example.dogfotosproject.data.repository.UserRepository
import org.koin.dsl.module
import com.example.dogfotosproject.data.repository.UserRepositoryImpl

val dataModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }

    single { AppDatabase.getInstance(get()) }
    single { get<AppDatabase>().userDao() }
}