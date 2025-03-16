package ru.sotnikova.testapplication.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.sotnikova.testapplication.data.Animals
import ru.sotnikova.testapplication.services.ApiException
import ru.sotnikova.testapplication.services.CatFactApiService
import ru.sotnikova.testapplication.services.DogFactApiService
import ru.sotnikova.testapplication.services.DogImageApiService


class FactOrPicViewModel(
    private val catFactApiService: CatFactApiService,
    private val dogFactApiService: DogFactApiService,
    private val dogImageApiService: DogImageApiService
) : ViewModel() {

    private val _isFactTextVisible = MutableStateFlow(false)
    val isFactTextVisible: StateFlow<Boolean> = _isFactTextVisible

    private val _isImageVisible = MutableStateFlow(false)
    val isImageVisible: StateFlow<Boolean> = _isImageVisible

    private val _isApiCalled = MutableStateFlow(false)
    val isApiCalled: StateFlow<Boolean> = _isApiCalled

    private val _errorFlow = MutableStateFlow<String?>(null)
    val errorFlow: StateFlow<String?> = _errorFlow

    private val _catFactText = MutableStateFlow<String?>(null)
    val catFactText: StateFlow<String?> = _catFactText

    private val _catImageUrl = MutableStateFlow<String?>(null)
    val catImageUrl: StateFlow<String?> get() = _catImageUrl

    private val _dogFactText = MutableStateFlow<String?>(null)
    val dogFactText: StateFlow<String?> get() = _dogFactText

    private val _dogImageUrl = MutableStateFlow<String?>(null)
    val dogImageUrl: StateFlow<String?> get() = _dogImageUrl


    fun onFactButtonClicked(animal: Animals) {
        if (animal == Animals.CATS) {
            if (!_isApiCalled.value) {
                fetchCatFactText()
            }
        } else if (animal == Animals.DOGS) {
            if (!_isApiCalled.value) {
                fetchDogFactText()
            }
        }
        _isFactTextVisible.value = !_isFactTextVisible.value
    }

    fun onImageButtonClicked(animal: Animals) {
            if (animal == Animals.CATS) {
                if (_catImageUrl.value == null) {
                    updateCatImageUrl()
                }
            } else if (animal == Animals.DOGS) {
                if (_dogImageUrl.value == null) {
                    fetchDogImageUrl()
                }
            }
        _isImageVisible.value = !_isImageVisible.value
    }

    private fun fetchCatFactText() {
        viewModelScope.launch {
            try {
                val response = catFactApiService.getCatFact()
                if (response.isSuccessful && response.body() != null) {
                    _catFactText.value = response.body()?.text ?: "No text available"
                } else {
                    throw ApiException("Error ${response.code()} occurred")
                }
            } catch (e: Exception) {
                _catFactText.value = e.message ?: "Unexpected error"
            }
        }
    }

    private fun fetchDogFactText() {
        viewModelScope.launch {
            try {
                val response = dogFactApiService.getDogFact()
                if (response.isSuccessful && response.body() != null) {
                    _dogFactText.value = response.body()?.get(0).toString()
                } else {
                    throw ApiException("Error ${response.code()} occurred")
                }
            } catch (e: Exception) {
                _dogFactText.value = e.message ?: "Unexpected error"
            }
        }
    }

    private fun updateCatImageUrl() {
        viewModelScope.launch {
            val imageUrlsList = listOf(
                "https://cataas.com/cat/small/says/hello",
                "https://cataas.com/cat/cute/says/hi",
                "https://cataas.com/cat/small/says/hello",
                "https://cataas.com/cat/sleepy/says/goodnight",
                "https://cataas.com/cat/large/says/hehe",
                "https://cataas.com/cat/beautiful/says/hehe"
            )
            val newImageUrl = imageUrlsList.random()
            _catImageUrl.emit(newImageUrl)
        }
    }

    private fun fetchDogImageUrl() {
        viewModelScope.launch {
            try {
                val response = dogImageApiService.getDogImage()
                if (response.isSuccessful && response.body() != null) {
                    _dogImageUrl.value = response.body()?.url
                } else {
                    throw ApiException("Error ${response.code()} occurred")
                }
            } catch (e: Exception) {
                _dogImageUrl.value = e.message ?: "Unexpected error"
            }
        }
    }
}