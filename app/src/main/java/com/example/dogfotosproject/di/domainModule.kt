package com.example.dogfotosproject.di

import com.example.dogfotosproject.data.local.UserSessionManager
import com.example.dogfotosproject.domain.usecase.AddToFavoritesUseCase
import com.example.dogfotosproject.domain.usecase.GetRandomDogPhotosUseCase
import com.example.dogfotosproject.domain.usecase.LoginUserUseCase
import com.example.dogfotosproject.domain.usecase.RegisterUserUseCase
import org.koin.dsl.module

val domainModule = module {
    single { RegisterUserUseCase(get()) }
    single { LoginUserUseCase(get()) }
    single { GetRandomDogPhotosUseCase(get()) }
    factory { AddToFavoritesUseCase(get()) }
    single { UserSessionManager(get()) }
}