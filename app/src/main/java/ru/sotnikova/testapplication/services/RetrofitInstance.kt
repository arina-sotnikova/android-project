package ru.sotnikova.testapplication.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val catTextRetrofit = Retrofit.Builder()
        .baseUrl("https://cat-fact.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val catFactApiService: CatFactApiService = catTextRetrofit.create(CatFactApiService::class.java)

    val dogFactRetrofit = Retrofit.Builder()
        .baseUrl("https://dog-api.kinduff.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val dogFactApiService: DogFactApiService = dogFactRetrofit.create(DogFactApiService::class.java)

    val dogImageRetrofit = Retrofit.Builder()
        .baseUrl("https://random.dog")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val dogImageApiService: DogImageApiService = dogImageRetrofit.create(DogImageApiService::class.java)
}