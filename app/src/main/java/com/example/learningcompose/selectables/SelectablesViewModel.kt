package com.example.learningcompose.selectables

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.learningcompose.selectables.models.Answer

class SelectablesViewModel() : ViewModel() {

    private val _answers = getList().toMutableStateList()
    val answers : List<Answer>
        get() = _answers


    private fun getList() = buildList {
        for(i in 1..30) {
            add(Answer(i, "Item $i", false))
        }
    }

    fun remove(item: Answer) {
        _answers.remove(item)
    }

}