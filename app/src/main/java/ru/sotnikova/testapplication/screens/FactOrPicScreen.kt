package ru.sotnikova.testapplication.screens

import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import ru.sotnikova.testapplication.R
import ru.sotnikova.testapplication.data.Animals


@Composable
fun FactOrPic(viewModel: FactOrPicViewModel, animal: Animals) {

    ErrorToast(viewModel)

    val scrollState = rememberScrollState()
    val isApiCalled by viewModel.isApiCalled.collectAsState()
    val isImageVisible by viewModel.isImageVisible.collectAsState()
    val isFactTextVisible by viewModel.isFactTextVisible.collectAsState()
    val catFactText by viewModel.catFactText.collectAsState()
    val dogFactText by viewModel.dogFactText.collectAsState()
    val catImageUrl by viewModel.catImageUrl.collectAsState()
    val dogImageUrl by viewModel.dogImageUrl.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(state = scrollState)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier.padding(top = 70.dp),
                text = stringResource(R.string.fact_or_pic_title),
                color = Color.White,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = animal.toString(),
                color = Color.White,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.size(30.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 30.dp, end = 30.dp)
            ) {
                Button(
                    onClick = { viewModel.onFactButtonClicked(animal) },
                    enabled = !isApiCalled,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    modifier = Modifier
                        .width(130.dp)
                        .height(80.dp)
                ) {
                    Text(
                        text = stringResource(R.string.button_get_fact),
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.width(40.dp))
                Button(
                    onClick = {
                        viewModel.onImageButtonClicked(animal)
                        Log.d("Compose", "Image: ${viewModel.catImageUrl.value}")
                    },
                    enabled = !isApiCalled,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    modifier = Modifier
                        .width(130.dp)
                        .height(80.dp)
                ) {
                    Text(
                        text = stringResource(R.string.button_get_pic),
                        fontSize = 16.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(50.dp))

            if (isFactTextVisible) {
                when (animal) {
                    Animals.CATS -> {
                        if (catFactText != null) {
                            Text(
                                text = catFactText ?: stringResource(R.string.no_text),
                                modifier = Modifier.padding(horizontal = 20.dp),
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                    }
                    Animals.DOGS -> {
                        if (dogFactText != null) {
                            Text(
                                text = dogFactText ?: stringResource(R.string.no_text),
                                modifier = Modifier.padding(horizontal = 20.dp),
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                    }
                    Animals.BEARS -> {
                        Text(
                            text = stringResource(R.string.bear_fact_text),
                            modifier = Modifier.padding(horizontal = 20.dp),
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                    Animals.DUCKS -> {
                        Text(
                            text = stringResource(R.string.duck_fact_text),
                            modifier = Modifier.padding(horizontal = 20.dp),
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(50.dp))

            if (isImageVisible) {
                when (animal) {
                    Animals.CATS -> {
                        AsyncImage(
                            model = catImageUrl,
                            contentDescription = stringResource(R.string.cat_image_description)
                        )
                    }
                    Animals.DOGS -> {
                        AsyncImage(
                            model = dogImageUrl,
                            contentDescription = stringResource(R.string.dog_image_description)
                        )
                    }
                    Animals.BEARS -> {
                        AsyncImage(
                            model = R.drawable.bear,
                            contentDescription = stringResource(R.string.bear_image_description)
                        )
                    }
                    Animals.DUCKS -> {
                        AsyncImage(
                            model = R.drawable.duck,
                            contentDescription = stringResource(R.string.duck_image_description)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = { HomeViewModel.onHomeClicked() },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                modifier = Modifier
                    .width(120.dp)
                    .height(60.dp)
                    .padding(bottom = 20.dp)
            ) {
                Text(
                    text = stringResource(R.string.button_home),
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun ErrorToast(viewModel: FactOrPicViewModel) {
    val context = LocalContext.current
    val errorFlow = viewModel.errorFlow.collectAsState()

    errorFlow.value?.let { message ->
        LaunchedEffect(key1 = message) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            viewModel.clearErrorMessage()
        }
    }
}
