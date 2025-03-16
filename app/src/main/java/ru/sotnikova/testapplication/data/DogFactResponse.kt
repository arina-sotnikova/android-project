package ru.sotnikova.testapplication.data

import com.google.gson.annotations.SerializedName

data class DogFactResponse(
    @SerializedName("facts")
    val facts: List<String>
)
