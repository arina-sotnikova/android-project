package ru.sotnikova.testapplication.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.sotnikova.testapplication.screens.FactOrPicViewModel

class FactOrPicViewModelFactory(
    private val catFactApiService: CatFactApiService,
    private val dogFactApiService: DogFactApiService,
    private val dogImageApiService: DogImageApiService
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FactOrPicViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FactOrPicViewModel(
                catFactApiService,
                dogFactApiService,
                dogImageApiService
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
