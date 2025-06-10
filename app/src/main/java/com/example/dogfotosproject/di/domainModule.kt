package com.example.dogfotosproject.di

import com.example.dogfotosproject.domain.usecase.LoginUserUseCase
import com.example.dogfotosproject.domain.usecase.RegisterUserUseCase
import org.koin.dsl.module

val domainModule = module {
    single { RegisterUserUseCase(get()) }
    single { LoginUserUseCase(get()) }
}