package ru.sotnikova.testapplication.services

import retrofit2.Response
import retrofit2.http.GET
import ru.sotnikova.testapplication.data.DogFactResponse

interface DogFactApiService {
    @GET("/api/facts")
    suspend fun getDogFact(): Response<DogFactResponse>
}