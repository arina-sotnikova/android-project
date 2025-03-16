package ru.sotnikova.testapplication.services

import retrofit2.Response
import retrofit2.http.GET
import ru.sotnikova.testapplication.data.CatsFactResponse

interface CatFactApiService {
    @GET("/facts/random")
    suspend fun getCatFact(): Response<CatsFactResponse>
}