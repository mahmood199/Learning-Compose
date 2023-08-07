package com.example.learningcompose.selectables.single

data class Answer(
    val id: Int,
    val text: String,
    val isSelected: Boolean = false
)
