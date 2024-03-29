package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.quizapp.utils.Constant

class ResultActivity : ComponentActivity() {
    private lateinit var username: String
    private var score=0
    private var totalQuestions=0
    private lateinit var scoreView: TextView
    private lateinit var usernameView: TextView
    private lateinit var playAgain: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        scoreView=findViewById(R.id.score)
        usernameView=findViewById(R.id.username)

        if(intent.hasExtra(Constant.USER_NAME) && intent.hasExtra(Constant.SCORE) && intent.hasExtra(Constant.TOTAL_QUESTIONS)){
            username= intent.getStringExtra(Constant.USER_NAME)!!
            score=intent.getIntExtra(Constant.SCORE,0)
            totalQuestions=intent.getIntExtra(Constant.TOTAL_QUESTIONS,0)
        }
        usernameView.text=username
        scoreView.text="${score}/${totalQuestions}"
        playAgain=findViewById(R.id.playAgain)
        playAgain.setOnClickListener{
            Intent(this, QuizApp::class.java).also { intent ->
                startActivity(intent)
                finish()
            }
        }

    }
}