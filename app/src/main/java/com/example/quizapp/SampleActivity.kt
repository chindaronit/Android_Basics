package com.example.quizapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class SampleActivity:ComponentActivity() {

// basic toggle button with text view
    private lateinit var toggleShowButton: Button
    private lateinit var helloWorldTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample_activity1)

        toggleShowButton=findViewById(R.id.toggleShow)
        toggleShowButton.text=getString(R.string.Hide)
        toggleShowButton.setOnClickListener { toggleVisibility() }
        helloWorldTextView=findViewById(R.id.helloWorldTV)
        helloWorldTextView.text=getString(R.string.helloWorldTV)
    }

    private fun toggleVisibility() {
        if (helloWorldTextView.visibility == View.VISIBLE){
            helloWorldTextView.visibility=View.INVISIBLE
            toggleShowButton.text=getString(R.string.Show)
        }
        else {
            helloWorldTextView.visibility=View.VISIBLE
            toggleShowButton.text=getString(R.string.Hide)
        }
    }

}