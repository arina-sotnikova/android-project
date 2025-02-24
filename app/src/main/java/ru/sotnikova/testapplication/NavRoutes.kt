package ru.sotnikova.testapplication

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object FactOrPic : NavRoutes("factOrPic")
}