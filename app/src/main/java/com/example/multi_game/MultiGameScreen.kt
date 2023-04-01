package com.example.multi_game

import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.multi_game.ui.GameViewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.multi_game.ui.guessing_number_game.GuessingNumScreen
import com.example.multi_game.ui.PredictGameScreen
import com.example.multi_game.ui.quiz_game.QuizGameScreen


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
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = GameScreen.valueOf(
        backStackEntry?.destination?.route ?: GameScreen.Start.name
    )

    Scaffold(
        topBar = {
            GameAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = GameScreen.Start.name,
            modifier = modifier.padding(innerPadding)
        ){
            composable(route = GameScreen.Start.name) {
                StartScreen(
                    navController
                )
            }

            composable(route = GameScreen.Quiz.name) {
                val context = LocalContext.current
                QuizGameScreen()
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
    navController: NavController
){
    Column {
        Button(
            onClick = { navController.navigate(GameScreen.GuessingGame.name) },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Guessing Number Game")
        }
        Button(
            onClick = { navController.navigate(GameScreen.Quiz.name) },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Quiz")
        }
        Button(
            onClick = { navController.navigate(GameScreen.Predict.name) },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Predict")
        }
    }
}
@Composable
fun GameAppBar(
    currentScreen: GameScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text("Hello") },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}