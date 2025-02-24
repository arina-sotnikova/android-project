package ru.sotnikova.testapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.sotnikova.testapplication.R

@Composable
fun Home(navController: NavHostController) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
            .verticalScroll(state = scrollState)
    ) {
        Text(
            text = "Animals Application",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 50.dp)
        )
        Text(
            text = "Select an animal and enjoy random info and pictures with them",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 40.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        ButtonToFactOrPic(
            navController = navController,
            image = R.drawable.katze,
            title = "CATS",
            description = "Wonderful and mischievous\nTap here if you want to see some data about cats",
            animal = "cats"
        )
        Spacer(modifier = Modifier.height(30.dp))
        ButtonToFactOrPic(
            navController = navController,
            image = R.drawable.chihuahua,
            title = "DOGS",
            description = "Funny and loyal\nTap here if you want to see some data about dogs",
            animal = "dogs"
        )
        Spacer(modifier = Modifier.height(30.dp))
        ButtonToFactOrPic(
            navController = navController,
            image = R.drawable.bear,
            title = "BEARS",
            description = """Sometimes may be sleepy, sometimes may be furious...
                |Tap here if you want to see some data about bears""".trimMargin(),
            animal = "bears"
        )
        Spacer(modifier = Modifier.height(30.dp))
        ButtonToFactOrPic(
            navController = navController,
            image = R.drawable.duck,
            title = "DUCKS",
            description = "Never ever try to make them nervous\nTap here if you want to see some data about ducks",
            animal = "ducks"
        )
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.paws),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = 30.dp)
            )
        }
    }
}

@Composable
fun ButtonToFactOrPic(
    navController: NavHostController,
    image: Int,
    title: String,
    description: String,
    animal: String
) {
    Button(
        onClick = { navController.navigate("FactOrPic/$animal") },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .size(96.dp)
                    .padding(start = 8.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}
