package ru.sotnikova.testapplication.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://cat-fact.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: CatsFactApiService = retrofit.create(CatsFactApiService::class.java)
}