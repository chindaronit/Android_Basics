package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.android.material.textfield.TextInputLayout
import com.example.quizapp.utils.Constant

class QuizApp : ComponentActivity() {

    private lateinit var startButton: Button
    private lateinit var startInput: TextInputLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quizapp)

        startButton = findViewById(R.id.startButton)
        startInput = findViewById(R.id.nameInput)

        startButton.setOnClickListener {
            val userName = startInput.editText?.text?.toString()

            if (!userName.isNullOrEmpty()) {
                Intent(this@QuizApp, QuestionsActivity::class.java).also { intent ->
                    intent.putExtra(Constant.USER_NAME, userName)
                    startActivity(intent)
                    finish()
                }
            } else {
                Toast.makeText(this@QuizApp, "Please Enter Your Name...", Toast.LENGTH_SHORT).show()
            }
        }
    }
}