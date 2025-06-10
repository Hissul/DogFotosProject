package com.example.dogfotosproject.di

import com.example.dogfotosproject.data.api.DogApiService
import com.example.dogfotosproject.data.db.AppDatabase
import com.example.dogfotosproject.data.repository.DogRepositoryImpl
import com.example.dogfotosproject.domain.repository.UserRepository
import org.koin.dsl.module
import com.example.dogfotosproject.data.repository.UserRepositoryImpl
import com.example.dogfotosproject.domain.repository.DogRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }

    single<DogRepository> {
        DogRepositoryImpl(get())
    }

    single { AppDatabase.getInstance(get()) }
    single { get<AppDatabase>().userDao() }

    single {
        Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<DogApiService> {
        get<Retrofit>().create(DogApiService::class.java)
    }
}