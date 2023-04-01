package com.example.multi_game.data




const val MAX_NO_OF_QUIZZES = 10
const val SCORE_INCREASE = 1


val allQuizzes =
    setOf(
        mapOf(
            "question" to "What is the name of the longest river in Africa?",
            "choice" to setOf("The Nile River", "The Amazon River", "Yellow River","Red (USA)"),
            "answer" to "The Nile River"),
        mapOf(
            "question" to "What is the name of the largest country in the world?",
            "choice" to setOf("Russia", "China", "USA", "Brazil"),
            "answer" to "Russia"),
        mapOf(
            "question" to "What U.S. state is home to no documented poisonous snakes?",
            "choice" to setOf("Alaska", "New Mexico", "Tennessee","Hawaii"),
            "answer" to "Alaska"),
        mapOf(
            "question" to "What country has the most natural lakes?",
            "choice" to setOf("Canada", "Switzerland", "Mexico","Vietnam" ),
            "answer" to "Canada"),
        mapOf(
            "question" to "What planet is closest to Earth?",
            "choice" to setOf("Venus", "Saturn", "Mars", "Jupiter"),
            "answer" to "Venus"),
        mapOf(
            "question" to "How many countries are there in the United Kingdom?",
            "choice" to setOf("1","2","3","4"),
            "answer" to "4"),
        mapOf(
            "question" to "Only continent in the world without a desert ?",
            "choice" to setOf("Europe", "North America", "Asia", "Africa"),
            "answer" to "Europe"),
        mapOf(
            "question" to "Which one is the capital of Spain?",
            "choice" to setOf("Madrid", "Barcelona", "Seville", "Lisbon"),
            "answer" to "Madrid"),
        mapOf(
            "question" to "Which country that emu bird is found?",
            "choice" to setOf("Australia", "Thailand", "Japan", "New Zealand"),
            "answer" to "Australia"),
        mapOf(
            "question" to "Which country is also known as the Land of Tulips?",
            "choice" to setOf("Netherlands", "Italy", "Switzerland", "Thailand"),
            "answer" to "Netherlands"),
    )

