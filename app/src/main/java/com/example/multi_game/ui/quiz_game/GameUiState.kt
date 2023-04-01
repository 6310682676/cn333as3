package com.example.multi_game.ui.quiz_game

data class GameUiState(
    val currentQuiz: Any = "",
    val currentQuestion: Any? = "",
    val currentChoice: List<String>? = listOf("0"),
    val currentQuizCount: Int = 1,
    val score: Int = 0,
    val isAnswerCorrect: Boolean = false,
    val isAnswer: Boolean = false,
    val isGameOver: Boolean = false,
    val currentAnswer: String? = ""
)