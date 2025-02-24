package ru.sotnikova.testapplication.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatsFactViewModel(private val apiService: CatsFactApiService) : ViewModel() {
    private val _catFactText = MutableStateFlow<String?>(null)
    val catFactText: StateFlow<String?> = _catFactText

    fun fetchText(animal: String) {
        viewModelScope.launch {
            val response = apiService.getFacts()
            if (response.isSuccessful && response.body() != null) {
                _catFactText.value = response.body()?.get(0)?.text ?: "No text available"
            }
        }
    }
}