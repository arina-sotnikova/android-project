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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sotnikova.testapplication.R
import ru.sotnikova.testapplication.data.Animals

@Composable
fun Home(viewModel: HomeViewModel) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
            .verticalScroll(state = scrollState)
    ) {
        Text(
            text = stringResource(R.string.home_screen_title),
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 50.dp)
        )
        Text(
            text = stringResource(R.string.home_screen_description),
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 40.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        ButtonToFactOrPic(
            viewModel = viewModel,
            image = R.drawable.katze,
            title = stringResource(R.string.cats_title),
            description1 = stringResource(R.string.cats_description),
            description2 = stringResource(R.string.cats_tap_text),
            animal = Animals.CATS,
        )
        Spacer(modifier = Modifier.height(30.dp))
        ButtonToFactOrPic(
            viewModel = viewModel,
            image = R.drawable.chihuahua,
            title = stringResource(R.string.dogs_title),
            description1 = stringResource(R.string.dogs_description),
            description2 = stringResource(R.string.dogs_tap_text),
            animal = Animals.DOGS,
        )
        Spacer(modifier = Modifier.height(30.dp))
        ButtonToFactOrPic(
            viewModel = viewModel,
            image = R.drawable.bear,
            title = stringResource(R.string.bears_title),
            description1 = stringResource(R.string.bears_description),
            description2 = stringResource(R.string.bears_tap_text),
            animal = Animals.BEARS,
        )
        Spacer(modifier = Modifier.height(30.dp))
        ButtonToFactOrPic(
            viewModel = viewModel,
            image = R.drawable.duck,
            title = stringResource(R.string.ducks_title),
            description1 = stringResource(R.string.ducks_description),
            description2 = stringResource(R.string.ducks_tap_text),
            animal = Animals.DUCKS,
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
    viewModel: HomeViewModel,
    image: Int,
    title: String,
    description1: String,
    description2: String,
    animal: Animals
) {
    Button(
        onClick = { viewModel.onAnimalClicked(animal) },
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
                    text = description1,
                    fontSize = 14.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description2,
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}
