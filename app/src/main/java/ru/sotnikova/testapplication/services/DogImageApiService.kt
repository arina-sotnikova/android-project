package ru.sotnikova.testapplication.services

import retrofit2.Response
import retrofit2.http.GET
import ru.sotnikova.testapplication.data.DogImageResponse

interface DogImageApiService {
    @GET("/woof.json")
    suspend fun getDogImage(): Response<DogImageResponse>
}
