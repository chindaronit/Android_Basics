package com.example.quizapp

import android.content.Intent
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.quizapp.model.Question
import com.example.quizapp.utils.Constant

class QuestionsActivity : ComponentActivity(), View.OnClickListener {

    private lateinit var questionTextView: TextView
    private lateinit var optionOneTextView: TextView
    private lateinit var optionTwoTextView: TextView
    private lateinit var optionThreeTextView: TextView
    private lateinit var optionFourTextView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var progressBarTextView: TextView
    private lateinit var flagImg: ImageView
    private lateinit var questionList: MutableList<Question>
    private lateinit var submitAnswerButton: Button
    private var selectedOptionPosition = 1
    private var pos = 1
    private lateinit var currentQuestion: Question
    private var answered = false
    private lateinit var name: String
    private var score = 0
    private var total_questions = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        questionList = Constant.getQuestions()
        questionTextView = findViewById(R.id.questionTextView)

        optionOneTextView = findViewById(R.id.optionOne)
        optionOneTextView.setOnClickListener(this)
        optionTwoTextView = findViewById(R.id.optionTwo)
        optionTwoTextView.setOnClickListener(this)
        optionThreeTextView = findViewById(R.id.optionThree)
        optionThreeTextView.setOnClickListener(this)
        optionFourTextView = findViewById(R.id.optionFour)
        optionFourTextView.setOnClickListener(this)

        progressBar = findViewById(R.id.progressBar)
        progressBar.max = questionList.size
        total_questions = questionList.size
        progressBarTextView = findViewById(R.id.progressBarTextView)
        flagImg = findViewById(R.id.flagImg)
        submitAnswerButton = findViewById(R.id.submitAnswer)
        setQuestions()

        submitAnswerButton.setOnClickListener(this)
        if(intent.hasExtra(Constant.USER_NAME)){
            name=intent.getStringExtra(Constant.USER_NAME)!!
        }
    }

    private fun resetOptions() {
        val options = mutableListOf<TextView>()
        options.add(optionOneTextView)
        options.add(optionTwoTextView)
        options.add(optionThreeTextView)
        options.add(optionFourTextView)

        for (option in options) {
            option.typeface = Typeface.DEFAULT
            option.setTextColor(ResourcesCompat.getColor(resources, R.color.black, null))
            option.background = ContextCompat.getDrawable(this, R.drawable.option)
        }
        submitAnswerButton.text = getString(R.string.submit)
    }

    private fun selectedOption(textView: TextView, selectedOptionNumber: Int) {
        if (answered) return
        resetOptions()

        selectedOptionPosition = selectedOptionNumber
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(this, R.drawable.selected_option)
    }

    private fun setQuestions() {

        if (pos <= questionList.size) {
            resetOptions()
            val question = questionList[pos - 1]
            currentQuestion = question
            flagImg.setImageResource(question.image)
            progressBar.progress = pos
            progressBarTextView.text = "${pos}/${questionList.size}"
            questionTextView.text = question.question
            optionOneTextView.text = question.optionOne
            optionTwoTextView.text = question.optionTwo
            optionThreeTextView.text = question.optionThree
            optionFourTextView.text = question.optionFour
        } else {
            Intent(this, ResultActivity::class.java).also {
                it.putExtra(Constant.USER_NAME,name)
                it.putExtra(Constant.SCORE,score)
                it.putExtra(Constant.TOTAL_QUESTIONS,total_questions)
                startActivity(it)
                finish()
            }
        }

        pos++
        answered = false
    }

    private fun checkAnswer() {
        answered = true
        if (selectedOptionPosition == currentQuestion.correctAns) {
            score++
            highlight(selectedOptionPosition, R.drawable.correct_option)
        } else {
            highlight(selectedOptionPosition, R.drawable.wrong_option)
            highlight(currentQuestion.correctAns, R.drawable.correct_option)
        }
        if (pos < questionList.size + 1) {
            submitAnswerButton.text = getString(R.string.next)
            submitAnswerButton.setTypeface(submitAnswerButton.typeface, Typeface.BOLD)
        } else {
            submitAnswerButton.text = getString(R.string.finish)
            submitAnswerButton.setTypeface(submitAnswerButton.typeface, Typeface.BOLD)
        }
    }

    private fun highlight(option: Int, highlightColor: Int) {
        when (option) {
            1 -> {
                optionOneTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.white,
                        null
                    )
                )
                optionOneTextView.background =
                    ContextCompat.getDrawable(this, highlightColor)
            }

            2 -> {
                optionTwoTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.white,
                        null
                    )
                )
                optionTwoTextView.background =
                    ContextCompat.getDrawable(this, highlightColor)
            }

            3 -> {
                optionThreeTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.white,
                        null
                    )
                )
                optionThreeTextView.background =
                    ContextCompat.getDrawable(this, highlightColor)
            }

            4 -> {
                optionFourTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.white,
                        null
                    )
                )
                optionFourTextView.background =
                    ContextCompat.getDrawable(this, highlightColor)
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.optionOne -> {
                selectedOption(optionOneTextView, 1)
            }

            R.id.optionTwo -> {
                selectedOption(optionTwoTextView, 2)
            }

            R.id.optionThree -> {
                selectedOption(optionThreeTextView, 3)
            }

            R.id.optionFour -> {
                selectedOption(optionFourTextView, 4)
            }

            R.id.submitAnswer -> {
                if (!answered) {
                    checkAnswer()
                } else {
                    setQuestions()
                }
                selectedOptionPosition = 0
            }
        }
    }
}