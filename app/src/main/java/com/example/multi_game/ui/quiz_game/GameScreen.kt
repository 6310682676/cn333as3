package com.example.multi_game.ui.quiz_game

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multi_game.R


@Composable
fun QuizGameScreen(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel()
) {
    val gameUiState by gameViewModel.uiState.collectAsState()
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GameStatus(
            quizCount = gameUiState.currentQuizCount,
            score = gameUiState.score
        )

        GameLayout(
            onUserGuessChanged = { gameViewModel.updateUserGuess(it) },
            currentChoice = gameUiState.currentChoice!!,
            currentQuestion = gameUiState.currentQuestion,
            userAnswer = gameViewModel.userAnswer,
            isAnswer = gameUiState.isAnswer,
            currentAnswer = gameUiState.currentAnswer
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            if(gameUiState.isAnswer){
                Button(
                    modifier = modifier
                        .padding(start = 8.dp),
                    onClick = { gameViewModel.nextQuestion() }
                ) {
                    Text(stringResource(R.string.next))
                }
            }else{
                Button(
                    modifier = modifier
                        .padding(start = 8.dp),
                    onClick = { gameViewModel.checkUserGuess() }
                ) {
                    Text(stringResource(R.string.submit))
                }
            }

        }
        if (gameUiState.isGameOver) {
            FinalScoreDialog(
                score = gameUiState.score,
                onPlayAgain = { gameViewModel.resetGame() }
            )
        }


    }

}


@Composable
fun GameLayout(
    currentQuestion: Any?,
    currentChoice: List<String>,
    userAnswer: String,
    onUserGuessChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    isAnswer: Boolean,
    currentAnswer: String?,
) {
    var selectedItem by remember {
        mutableStateOf("")
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = currentQuestion.toString(),
            fontSize = 25.sp,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )

        currentChoice.forEach { label ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (selectedItem == label),
                        onClick = {
                            selectedItem = label
                            onUserGuessChanged(selectedItem)
                        },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    modifier = Modifier.padding(end = 16.dp),
                    selected = (selectedItem == label),
                    onClick = null
                )
                if(isAnswer){
                    if(userAnswer == label){
                        if(currentAnswer == userAnswer){
                            Text(text = label, style = TextStyle(
                                color = Color(0,255,20),))
                        }else{
                            Text(text = label, style = TextStyle(
                                color = Color(255,20,20),))
                        }
                    }else{
                        Text(text = label)
                    }
                }else{
                    Text(text = label)
                }



            }

        }

//        OutlinedTextField(
//            value = userAnswer,
//            singleLine = true,
//            modifier = Modifier.fillMaxWidth(),
//            onValueChange = onUserGuessChanged,
//            label = {
//                if (isGuessWrong) {
//                    Text(stringResource(R.string.wrong_guess))
//                } else {
//                    Text(stringResource(R.string.enter_your_word))
//                }
//            },
//            isError = isGuessWrong,
//            keyboardOptions = KeyboardOptions.Default.copy(
//                imeAction = ImeAction.Done
//            ),
//            keyboardActions = KeyboardActions(
//                onDone = { onKeyboardDone() }
//            ),
//        )


    }
}




@Composable
fun GameStatus(quizCount: Int, score: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .size(48.dp),
    ) {
        Text(
            text = stringResource(R.string.quizzes_count, quizCount),
            fontSize = 18.sp,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            text = stringResource(R.string.score, score),
            fontSize = 18.sp,
        )
    }
}

@Composable
private fun FinalScoreDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { Text(stringResource(R.string.congratulations)) },
        text = { Text(stringResource(R.string.your_scored, score)) },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = stringResource(R.string.exit))
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onPlayAgain()
                }
            ) {
                Text(text = stringResource(R.string.play_again))
            }
        }
    )
}




