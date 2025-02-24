package ru.sotnikova.testapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.sotnikova.testapplication.screens.FactOrPic
import ru.sotnikova.testapplication.screens.Home
import ru.sotnikova.testapplication.services.CatsFactViewModel
import ru.sotnikova.testapplication.services.CatsFactViewModelFactory
import ru.sotnikova.testapplication.services.RetrofitInstance.apiService
import ru.sotnikova.testapplication.ui.theme.TestApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel: CatsFactViewModel = viewModel(factory = CatsFactViewModelFactory(apiService))

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ) {
        composable("Home") { Home(navController) }
        composable("FactOrPic/{animal}") { backStackEntry ->
            val animal = backStackEntry.arguments?.getString("animal") ?: "default"
            FactOrPic(viewModel, animal, navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TestApplicationTheme {
        MainScreen()
    }
}