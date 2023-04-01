package com.example.multi_game.ui.quiz_game

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.multi_game.data.allQuizzes
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.multi_game.data.SCORE_INCREASE
import com.example.multi_game.data.MAX_NO_OF_QUIZZES
import kotlinx.coroutines.flow.update


class GameViewModel : ViewModel() {
    // Game UI state
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private lateinit var currentQuiz: Any
    private var usedQuiz: MutableSet<Any> = mutableSetOf()
    var userAnswer by mutableStateOf("")
        private set

    init {
        resetGame()
    }

    private fun pickRandomQuiz(): Any {
        // Continue picking up a new random word until you get one that hasn't been used before
        currentQuiz = allQuizzes.random()
        val question = (currentQuiz as Map<String, Any>)["question"]
        val choice = (currentQuiz as Map<String, Set<String>>)["choice"]
        return if(usedQuiz.contains(question)) {
            pickRandomQuiz()
        }else {
            if (question != null) {
                usedQuiz.add(question)
            }
            currentQuiz
        }
    }

    private fun getQuestion(): Any? {
        return (currentQuiz as Map<String, Any>)["question"]
    }

    private fun getChoice(): List<String>? {
        val choice = (currentQuiz as Map<String, Set<String>>)["choice"]
        return choice?.shuffled()
    }

    private  fun getAnswer(): String? {
        return (currentQuiz as Map<String, String>)["answer"]

    }
    fun resetGame() {
        usedQuiz.clear()
        currentQuiz = pickRandomQuiz()
        val answer: String? = (currentQuiz as Map<String, String>)["answer"]
        val question = (currentQuiz as Map<String, Any>)["question"]
        val choice = getChoice()
        _uiState.value = GameUiState(
            currentQuiz = currentQuiz,
            currentQuestion = question,
            currentChoice = choice,
            currentAnswer = answer

        )
    }


    fun updateUserGuess(answer: String){
        if(! _uiState.value.isAnswer){
            userAnswer = answer
        }

    }

    fun nextQuestion(){
        if (usedQuiz.size == MAX_NO_OF_QUIZZES){
//            //Last round in the game, update isGameOver to true, don't pick a new word
            _uiState.update { currentState ->
                currentState.copy(
                    isAnswerCorrect = false,
                    isGameOver = true
                )
            }
        }else{
            _uiState.update { currentState ->
                currentState.copy(
                    isAnswerCorrect = false,
                    isAnswer = false,
                    currentQuiz = pickRandomQuiz(),
                    currentQuestion = getQuestion(),
                    currentChoice = getChoice(),
                    currentAnswer = getAnswer(),
                    currentQuizCount = currentState.currentQuizCount.inc(),
                )
            }
            userAnswer = ""
        }


    }
    fun checkUserGuess() {
        val answer: String? = (currentQuiz as Map<String, String>)["answer"]
        if (userAnswer.equals(answer, ignoreCase = true)) {
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        } else {
            updateGameState(_uiState.value.score)
            // Reset user guess

        }
    }

    private fun updateGameState(updatedScore: Int) {
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
        _uiState.update { currentState ->
            currentState.copy(
                isAnswer = true,

                score = updatedScore
            )
        }
//        }
    }
}



