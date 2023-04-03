package com.example.multi_game

import android.widget.Space
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
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


enum class GameScreen( val title: String) {
    Start(title = "Home"),
    GuessingGame(title = "Guessing Number Game"),
    Quiz(title = "Quiz Game"),
    Predict(title = "Siem See")
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(25.dp))

        Text(text = "Select game you want to play ",
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W500,
                fontStyle = FontStyle.Italic,
            )
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = { navController.navigate(GameScreen.GuessingGame.name) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(116, 211, 244, 255),
                contentColor = MaterialTheme.colors.onPrimary
            )
        ) {
            Text(text = "Guessing Number Game")
        }
        Button(
            onClick = { navController.navigate(GameScreen.Quiz.name) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(116, 211, 244, 255),
                contentColor = MaterialTheme.colors.onPrimary
            )

        ) {
            Text(text = "Quiz Game")
        }
        Button(
            onClick = { navController.navigate(GameScreen.Predict.name) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(116, 211, 244, 255),
                contentColor = MaterialTheme.colors.onPrimary
            )
        ) {
            Text(text = "Siem See")
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
        title = { Text((currentScreen.title)) },
        modifier = modifier,
        backgroundColor = Color(0, 106, 125, 232),
        contentColor = Color(255,255,255),
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