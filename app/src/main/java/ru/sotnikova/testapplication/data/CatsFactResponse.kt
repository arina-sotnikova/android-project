package ru.sotnikova.testapplication.data

import com.google.gson.annotations.SerializedName

data class CatsFactResponse(
    @SerializedName("text")
    val text: String
)
