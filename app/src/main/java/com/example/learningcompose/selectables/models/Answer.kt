package com.example.learningcompose.selectables.models

data class Answer(
    val id: Int,
    val text: String,
    val isSelected: Boolean = false
)
