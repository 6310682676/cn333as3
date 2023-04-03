package com.example.multi_game.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class SiemSeeScreen(){
    First,
    Second,
    Third,
    Fourth,
    Fifth
}
@Composable
fun PredictGameScreen(
    gameViewModel:PredictGameViewModel = viewModel()
){
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SiemSeeScreen.First.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = SiemSeeScreen.First.name) {
                BeginningScreen(
                    navController
                )
            }

            composable(route = SiemSeeScreen.Second.name) {
                val context = LocalContext.current
                SecondScreen(
                    navController
                )
            }

            composable(route = SiemSeeScreen.Third.name) {
                val context = LocalContext.current
                ThirdScreen(
                    navController,
                    gameViewModel
                )
            }

            composable(route = SiemSeeScreen.Fourth.name) {

                FourthScreen(
                    navController,
                    gameViewModel
                )
            }

            composable(route = SiemSeeScreen.Fifth.name) {

                FifthScreen(
                    gameViewModel
                )

            }
        }
    }
}


@Composable
fun BeginningScreen(
    navController: NavController,
){
    Column(verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(250.dp))

        Text(text = "Siem See",
            style = TextStyle(
                color = Color.Black,
                fontSize = 45.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W800,
                fontStyle = FontStyle.Italic,
                letterSpacing = 0.5.em,
                ))
        Text(text = "lucky number",style = TextStyle(
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.W600,
            fontStyle = FontStyle.Italic,
            letterSpacing = 0.5.em,))

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = { navController.navigate(SiemSeeScreen.Second.name) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary
            )
        ){
            Text(text = "Click me")
        }

    }
}

@Composable
fun SecondScreen(
    navController: NavController
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(120.dp))

        Text(
            text = "Before",
            style = TextStyle(
                color = Color.Black,
                fontSize = 45.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W800,
                fontStyle = FontStyle.Italic,
                letterSpacing = 0.3.em,
            )
        )
        Text(
            text = "Siemsee",
            style = TextStyle(
                color = Color.Black,
                fontSize = 36.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W800,
                fontStyle = FontStyle.Italic,
                letterSpacing = 0.3.em,
            )
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "1. Be focused and take a deep breath", style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W600,
                fontStyle = FontStyle.Italic,
                letterSpacing = 0.em,
            )
        )

        Text(
            text = "2. Recognize about your lucky number", style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W600,
                fontStyle = FontStyle.Italic,
                letterSpacing = 0.em,
            )
        )

        Spacer(modifier = Modifier.height(120.dp))

        Text(
            text = "If You Are Ready",
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.W800,
                letterSpacing = 0.em,
            )
        )

        Spacer(modifier = Modifier.height(5.dp))

        Button(
            onClick = { navController.navigate(SiemSeeScreen.Third.name) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary
            )
        ) {
            Text(text = "siem see" ,style = TextStyle(
                color = Color.White,
                fontSize = 25.sp,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Italic,
                letterSpacing = 0.em)
            )
        }

    }
}

@Composable
fun ThirdScreen(
    navController: NavController,
    gameViewModel:PredictGameViewModel
){
        Column(
            verticalArrangement = Arrangement.spacedBy(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text="Choose a number"
            )
            for (i in 1..4){
                Row(horizontalArrangement = Arrangement.spacedBy(50.dp),){
                    Button(onClick = {
                        gameViewModel.getPrediction((3 * (i - 1) + 1).toString())
                        navController.navigate(SiemSeeScreen.Fourth.name)
                    }) {
                        Text(text=(3 * (i - 1) + 1).toString())
                    }
                    Button(onClick = {
                        gameViewModel.getPrediction((3 * (i - 1) + 2).toString())
                        navController.navigate(SiemSeeScreen.Fourth.name)
                    }) {
                        Text(text=(3 * (i - 1) + 2).toString())
                    }
                    Button(onClick = {
                        gameViewModel.getPrediction((3 * (i - 1) + 3).toString())
                        navController.navigate(SiemSeeScreen.Fourth.name)
                    }) {
                        Text(text=(3 * (i - 1) + 3).toString())
                    }
                }
            }
        }
}

@Composable
fun FourthScreen(
    navController: NavController,
    gameViewModel:PredictGameViewModel
){

    val gameUiState by gameViewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
        Text(text = "")
        Spacer(modifier = Modifier.height(200.dp))
        Button(
            onClick = {
                if(gameUiState.totalTap == gameUiState.tap){
                    navController.navigate(SiemSeeScreen.Fifth.name)
                }else{
                    gameViewModel.plusTap()
                }
            },
            modifier= Modifier.size(300.dp),  //avoid the oval shape
            shape = CircleShape,
            border= BorderStroke(1.dp, Color.Blue),
            contentPadding = PaddingValues(0.dp), //avoid the little icon
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor =  Color.White,
                backgroundColor = Color.Red
            ),


        ) {
            Text(text = "TAP !!!",
                style = TextStyle(
                    fontSize = 50.sp,
                )
            )
        }
    }

}

@Composable
fun FifthScreen(
    gameViewModel:PredictGameViewModel

){
    val gameUiState by gameViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            gameUiState.prediction,
            textAlign = TextAlign.Center,
            style = TextStyle(color = Color.Black,
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W400,
                fontStyle = FontStyle.Normal,
                letterSpacing = 0.05.em,
                textDecoration = TextDecoration.None) ,

        )
    }

//    Text(gameUiState.prediction, style = TextStyle())
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




//@Composable
//fun GameStatus(quizCount: Int, score: Int, modifier: Modifier = Modifier) {
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//            .size(48.dp),
//    ) {
//        Text(
//            text = stringResource(R.string.quizzes_count, quizCount),
//            fontSize = 18.sp,
//        )
//        Text(
//            modifier = Modifier
//                .fillMaxWidth()
//                .wrapContentWidth(Alignment.End),
//            text = stringResource(R.string.score, score),
//            fontSize = 18.sp,
//        )
//    }
//}
//
//@Composable
//private fun FinalScoreDialog(
//    score: Int,
//    onPlayAgain: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    val activity = (LocalContext.current as Activity)
//
//    AlertDialog(
//        onDismissRequest = {
//            // Dismiss the dialog when the user clicks outside the dialog or on the back
//            // button. If you want to disable that functionality, simply use an empty
//            // onCloseRequest.
//        },
//        title = { Text(stringResource(R.string.congratulations)) },
//        text = { Text(stringResource(R.string.your_scored, score)) },
//        modifier = modifier,
//        dismissButton = {
//            TextButton(
//                onClick = {
//                    activity.finish()
//                }
//            ) {
//                Text(text = stringResource(R.string.exit))
//            }
//        },
//        confirmButton = {
//            TextButton(
//                onClick = {
//                    onPlayAgain()
//                }
//            ) {
//                Text(text = stringResource(R.string.play_again))
//            }
//        }
//    )
//}

