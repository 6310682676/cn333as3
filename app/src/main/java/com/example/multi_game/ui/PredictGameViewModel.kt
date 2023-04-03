package com.example.multi_game.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.multi_game.data.predictions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class PredictGameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    var userAnswer by mutableStateOf("")
        private set

    init {
        resetGame()
    }

    fun resetGame() {
        _uiState.value = GameUiState(
            totalTap = Random.nextInt(20),
            tap = 0
        )
    }

    fun plusTap(){
        _uiState.update { currentState ->
            currentState.copy(
                tap = uiState.value.tap + 1
            )
        }
    }

    fun getPrediction(no:String){
        val prediction = predictions[no]
        if (prediction != null) {
            _uiState.update { currentState ->
                currentState.copy(
                    prediction = prediction.random() ?: "Error not found "
                )
            }
        }

    }

}