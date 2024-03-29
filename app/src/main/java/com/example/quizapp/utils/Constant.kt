package com.example.quizapp.utils

import com.example.quizapp.model.Question
import com.example.quizapp.R
object Constant {
    const val USER_NAME="username"
    const val TOTAL_QUESTIONS="total_questions"
    const val SCORE="score"
    fun getQuestions(): MutableList<Question> {
        val questions= mutableListOf<Question>()
        val quest1=Question(
            1,
            "To which country does this flag belong?",
            R.drawable.flag_of_india,
            "India",
            "Pakistan",
            "China",
            "Bangladesh",
            1
        )
        questions.add(quest1)

        val quest2=Question(
            1,
            "To which country does this flag belong?",
            R.drawable.flag_of_usa,
            "India",
            "USA",
            "China",
            "Italy",
            2
        )

        questions.add(quest2)


        return questions
    }
}