package com.example.dogfotosproject.data.api

import com.example.dogfotosproject.data.response.DogPhotosResponse
import retrofit2.http.GET

interface DogApiService {
    @GET("breeds/image/random/20")
    suspend fun getRandomDogPhotos(): DogPhotosResponse
}