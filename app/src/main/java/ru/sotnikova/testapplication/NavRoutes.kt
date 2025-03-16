package ru.sotnikova.testapplication

import ru.sotnikova.testapplication.data.Animals

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    class FactOrPic(val animal: Animals) : NavRoutes("factOrPic/{animal") {
        fun createRoute() = "factOrPic/${animal.name}"
    }
}
