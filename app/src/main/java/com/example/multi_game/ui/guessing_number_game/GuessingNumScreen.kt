package com.example.multi_game.ui.guessing_number_game


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GuessingNumScreen(
        gameViewModel: GuessingNumViewModel = viewModel()
){
    val gameUiState by gameViewModel.uiState.collectAsState()


    val playAgainButton by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Number Guessing Game",
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            color = Color(95,0,160)
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = "Try to guess the number I'm thinking of form 1 - 1000",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Current range form ${gameUiState.currentMin} - ${gameUiState.currentMax}",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 15.sp,

            )
        GuessingNumberField(value = gameUiState.userAnswer,
            onValueChange = {gameViewModel.updateUserGuess(it) },)
        if(gameUiState.isAnswer){
            if(gameUiState.counter > 1){
                Text(
                    text = "Try ${gameUiState.counter} times",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 15.sp,

                    )
            }else{
                Text(
                    text = "Try ${gameUiState.counter}  time",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 15.sp,

                    )
            }

        }
        if(gameUiState.isCorrect){
            Button(
                onClick = {
                    gameViewModel.resetGame()
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)

            ) {
                Text("Play Again")
            }
        }else{
            Button(
                onClick = { gameViewModel.checkUserGuess()
//                    val tip = playingGame(amountInput.toIntOrNull() ?: 0, answer)
//                    result = tip
//                    showCounter = true
//                    counter += 1
//                    if(result.compareTo("You are correct") == 0){
//                        playAgainButton = true
//                    }else if(result.compareTo("Hint: number is lower than your guess") == 0){
//                        maxNum = amountInput.toIntOrNull() ?: 0
//
//                    }else{
//                        minNum = amountInput.toIntOrNull() ?: 0
//
//                    }
//                    amountInput = ""

                },
                modifier = Modifier.align(Alignment.CenterHorizontally)

            ) {
                Text("Submit")
            }
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = gameUiState.hint,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

    }


}

@Composable
fun GuessingNumberField(
    value: String,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            label = { Text("your guess") },
            modifier = Modifier
                .weight(1f)
                ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.None,
                ),
            singleLine = true,
            value = value,
            onValueChange = onValueChange,
        )
    }
}

