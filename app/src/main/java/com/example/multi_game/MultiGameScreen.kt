package com.example.multi_game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.multi_game.ui.GameViewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import com.example.multi_game.ui.GuessingNumScreen
import com.example.multi_game.ui.PredictGameScreen


enum class GameScreen {
    Start,
    GuessingGame,
    Quiz,
    Predict
}

@Composable
fun GameApp(
        modifier: Modifier = Modifier,
        viewModel: GameViewModels = viewModel(),
){
    val navController = rememberNavController()

    Scaffold(

    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = GameScreen.Start.name,
            modifier = modifier.padding(innerPadding)
        ){
            composable(route = GameScreen.Start.name) {
                StartScreen(
                    onPredictButtonClicked = {
                        navController.navigate(GameScreen.Predict.name)},
                    onGuessingButtonClicked = {
                        navController.navigate(GameScreen.GuessingGame.name)
                    }
                )
            }

            composable(route = GameScreen.Quiz.name) {
                val context = LocalContext.current

            }

            composable(route = GameScreen.GuessingGame.name) {
                val context = LocalContext.current
                GuessingNumScreen()
            }

            composable(route = GameScreen.Predict.name) {

                PredictGameScreen()
            }
        }
    }
}

@Composable
fun StartScreen(
    onPredictButtonClicked: () -> Unit,
    onGuessingButtonClicked: () -> Unit
){
    Column() {
        Button(
            onClick = { onGuessingButtonClicked() },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Guessing Number Game")
        }
        Button(
            onClick = { onPredictButtonClicked() },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Quiz")
        }
        Button(
            onClick = { onPredictButtonClicked() },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Predict")
        }
    }
}