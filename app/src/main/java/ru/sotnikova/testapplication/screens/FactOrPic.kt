package ru.sotnikova.testapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.sotnikova.testapplication.NavRoutes
import ru.sotnikova.testapplication.services.RetrofitClient

@Composable
fun FactOrPic(navController: NavHostController, animal: String) {

    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    var text: String = ""


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(state = scrollState)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier.padding(top = 70.dp),
                text = "Get a $animal fact or a pic",
                color = Color.White,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.size(30.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 30.dp, end = 30.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(NavRoutes.Home.route) {
                            popUpTo(NavRoutes.Home.route)
                        }
                    },
//                    onClick = scope.launch {
//                        text = HttpClient.getRandomCatFact().text
//                    }, //todo
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    modifier = Modifier
                        .width(130.dp)
                        .height(80.dp)
                ) {
                    Text(
                        text = "Get a fact",
                        fontSize = 16.sp
                    )
                } // todo: work with api!
                Spacer(modifier = Modifier.width(40.dp))
                Button(
                    onClick = {
                        navController.navigate(NavRoutes.Home.route) {
                            popUpTo(NavRoutes.Home.route)
                        } // todo: work with api!
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    modifier = Modifier
                        .width(130.dp)
                        .height(80.dp)
                ) {
                    Text(
                        text = "Get a pic",
                        fontSize = 16.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = RetrofitClient.getRandomCatFact().text, // todo
                color = Color.White,
                fontSize = 16.sp
            ) // todo: text = api response
            Spacer(modifier = Modifier.height(50.dp))
//            Image(
//                contentDescription = null,
//                modifier = Modifier
//                    .size(96.dp)
//                    .padding(start = 40.dp)
//            ) // todo: image = api response
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                    navController.navigate(NavRoutes.Home.route) {
                        popUpTo(NavRoutes.Home.route)
                    }
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                modifier = Modifier
                    .width(120.dp)
                    .height(60.dp)
                    .padding(bottom = 20.dp)
            ) {
                Text(
                    text = "Home",
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
