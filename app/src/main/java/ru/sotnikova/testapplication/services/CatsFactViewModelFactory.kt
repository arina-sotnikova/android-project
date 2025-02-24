package ru.sotnikova.testapplication.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CatsFactViewModelFactory(private val apiServiceV2: CatsFactApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatsFactViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CatsFactViewModel(apiServiceV2) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
