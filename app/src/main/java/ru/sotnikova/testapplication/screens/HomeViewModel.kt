package ru.sotnikova.testapplication.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.sotnikova.testapplication.NavRoutes
import ru.sotnikova.testapplication.data.Animals

object HomeViewModel : ViewModel() {
    private val _navigateTo = MutableStateFlow<NavRoutes?>(null)
    val navigateTo: StateFlow<NavRoutes?> = _navigateTo

    fun onHomeClicked() {
        _navigateTo.value = NavRoutes.Home
    }

    fun onAnimalClicked(animal: Animals) {
        when (animal) {
            Animals.CATS -> _navigateTo.value = NavRoutes.FactOrPic(Animals.CATS)
            Animals.DOGS -> _navigateTo.value = NavRoutes.FactOrPic(Animals.DOGS)
            Animals.BEARS -> _navigateTo.value = NavRoutes.FactOrPic(Animals.BEARS)
            Animals.DUCKS -> _navigateTo.value = NavRoutes.FactOrPic(Animals.DUCKS)
        }
    }
}
