package ru.sotnikova.testapplication.services

import retrofit2.Call
import retrofit2.http.GET
import ru.sotnikova.testapplication.data.CatsFact

interface CatsFactsApiService {
    @GET("/facts")
    suspend fun getFacts(): Call<Array<CatsFact>>
}