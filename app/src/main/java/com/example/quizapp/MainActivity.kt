package com.example.quizapp

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.core.content.res.ResourcesCompat

class MainActivity: ComponentActivity() {

// how to access the button of activity main
    private lateinit var applyButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        applyButton=findViewById(R.id.applyButton)
        applyButton.setTextColor(ResourcesCompat.getColor(resources, R.color.purple_700,null));
        applyButton.text=getString(R.string.apply_button)
    }
}



