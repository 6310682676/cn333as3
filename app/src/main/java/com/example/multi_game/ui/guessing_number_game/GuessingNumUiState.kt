package com.example.multi_game.ui.guessing_number_game

data class GuessingNumUiState(
    val currentAnswer: Int = 0,
    val currentMax: Int = 1000,
    val currentMin: Int = 0,
    val hint: String = "",
    val counter: Int = 0,
    val isAnswer: Boolean = false,
    val userAnswer: String ="",
    val isCorrect: Boolean = false
)