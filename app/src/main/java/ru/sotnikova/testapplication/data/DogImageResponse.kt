package ru.sotnikova.testapplication.data

import com.google.gson.annotations.SerializedName

data class DogImageResponse(
    @SerializedName("url")
    val url: String
)
