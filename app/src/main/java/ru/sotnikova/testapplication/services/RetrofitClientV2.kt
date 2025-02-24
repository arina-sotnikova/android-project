package ru.sotnikova.testapplication.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getInstance(baseUrl: String?): Retrofit? {
        if (retrofit == null) {
            retrofit = baseUrl?.let {
                Retrofit.Builder()
                    .baseUrl(it)
                    .addConverterFactory(GsonConverterFactory.create()) // Use your preferred converter
                    .build()
            }
        }
        return retrofit
    }
}