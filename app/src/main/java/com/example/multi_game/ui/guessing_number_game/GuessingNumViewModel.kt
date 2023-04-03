package com.example.multi_game.ui.guessing_number_game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class GuessingNumViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(GuessingNumUiState())
    val uiState: StateFlow<GuessingNumUiState> = _uiState.asStateFlow()

    private var userAnswer by mutableStateOf("0")
    private var counter by mutableStateOf(0)

    init {
        resetGame()
    }

    fun resetGame() {

        _uiState.value = GuessingNumUiState(
            currentAnswer = Random.nextInt(1,1000),
            currentMax = 1000,
            currentMin = 1,
            counter = 0,
            hint = "",
            isAnswer = false,
            userAnswer = "",
            isCorrect = false
        )

    }

    fun updateUserGuess(answer: String){
        userAnswer = answer
        _uiState.update { currentState ->
            currentState.copy(
                userAnswer = answer
            )
        }
    }

    fun checkUserGuess() {
        counter += 1
        val answer = userAnswer.toIntOrNull()?: 0
        if(answer > _uiState.value.currentAnswer){
            _uiState.update { currentState ->
                currentState.copy(
                    hint = "Hint: number is lower than your guess",
                    currentMax = answer,
                    counter = counter ,
                    isAnswer = true,
                    userAnswer = ""
                )
            }
            updateGameState()
        }else if(answer < _uiState.value.currentAnswer){
            _uiState.update { currentState ->
                currentState.copy(
                    hint = "Hint: number is higher than your guess",
                    currentMin = answer,
                    counter = counter,
                    isAnswer = true,
                    userAnswer = ""
                )
            }
            updateGameState()
        }else{
            _uiState.update { currentState ->
                currentState.copy(
                    hint = "You are correct",
                    counter = counter,
                    isAnswer = true,
                    userAnswer = "",
                    isCorrect = true
                )
            }
            updateGameState()
        }

    }

    private fun updateGameState() {
//        if (usedQuiz.size == MAX_NO_OF_QUIZZES){
//            //Last round in the game, update isGameOver to true, don't pick a new word
//            _uiState.update { currentState ->
//                currentState.copy(
//                    isAnswerCorrect = false,
//                    score = updatedScore,
//                    isGameOver = true
//                )
//            }
//        } else{
//            // Normal round in the game

//        }
    }

}