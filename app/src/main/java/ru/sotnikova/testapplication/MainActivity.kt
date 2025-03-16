package ru.sotnikova.testapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.sotnikova.testapplication.data.Animals
import ru.sotnikova.testapplication.screens.FactOrPic
import ru.sotnikova.testapplication.screens.FactOrPicViewModel
import ru.sotnikova.testapplication.screens.Home
import ru.sotnikova.testapplication.screens.HomeViewModel
import ru.sotnikova.testapplication.services.FactOrPicViewModelFactory
import ru.sotnikova.testapplication.services.RetrofitInstance.catFactApiService
import ru.sotnikova.testapplication.services.RetrofitInstance.dogFactApiService
import ru.sotnikova.testapplication.services.RetrofitInstance.dogImageApiService
import ru.sotnikova.testapplication.ui.theme.TestApplicationTheme


class MainActivity : ComponentActivity() {
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        homeViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun MainScreen(homeViewModel: HomeViewModel, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navigateTo by homeViewModel.navigateTo.collectAsState()

    LaunchedEffect(navigateTo) {
        navigateTo?.let { destination ->
            when (destination) {
                is NavRoutes.Home -> {
                    navController.navigate(destination.route) {
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                    }
                }

                is NavRoutes.FactOrPic -> {
                    navController.navigate(destination.createRoute()) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                    }
                }
            }
        }
    }

    NavHost(navController, startDestination = NavRoutes.Home.route) {

        composable(NavRoutes.Home.route) {
            Home(homeViewModel)
        }

        composable(
            "factOrPic/{animal}",
            arguments = listOf(navArgument("animal") { type = NavType.StringType })
        ) { backStackEntry ->
            val animal = backStackEntry.arguments?.getString("animal")
            val factOrPicViewModel: FactOrPicViewModel = viewModel(
                factory = FactOrPicViewModelFactory(
                    catFactApiService,
                    dogFactApiService,
                    dogImageApiService
                )
            )
            FactOrPic(factOrPicViewModel, Animals.valueOf(animal ?: "CATS"))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TestApplicationTheme {
        MainScreen(homeViewModel = HomeViewModel, modifier = Modifier)
    }
}