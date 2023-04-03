package com.example.multi_game.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PredictGameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    var userAnswer by mutableStateOf("")
        private set

    init {
        resetGame()
    }

    fun resetGame() {
        _uiState.value = com.example.multi_game.ui.GameUiState(


        )
    }
//
//    private fun pickRandomQuiz(): Any {
//        // Continue picking up a new random word until you get one that hasn't been used before
//
//
//    }
//
//    private fun getQuestion(): Any? {
//        return (currentQuiz as Map<String, Any>)["question"]
//    }
//
//    private fun getChoice(): List<String>? {
//        val choice = (currentQuiz as Map<String, Set<String>>)["choice"]
//        return choice?.shuffled()
//    }
//
//    private  fun getAnswer(): String? {
//        return (currentQuiz as Map<String, String>)["answer"]
//
//    }
//    fun resetGame() {
//
//    }
//
//
//    fun updateUserGuess(answer: String){
//
//
//    }
//
//    fun nextQuestion(){
//        if (true){
////            //Last round in the game, update isGameOver to true, don't pick a new word
//            _uiState.update { currentState ->
//                currentState.copy(
//
//                )
//            }
//        }else{
//
//        }
//
//
//    }
//    fun checkUserGuess() {
//
//    }
//
//    private fun updateGameState(updatedScore: Int) {
////        if (usedQuiz.size == MAX_NO_OF_QUIZZES){
////            //Last round in the game, update isGameOver to true, don't pick a new word
////            _uiState.update { currentState ->
////                currentState.copy(
////                    isAnswerCorrect = false,
////                    score = updatedScore,
////                    isGameOver = true
////                )
////            }
////        } else{
////            // Normal round in the game
//        _uiState.update { currentState ->
//            currentState.copy(
//
//            )
//        }
////        }

}