package com.example.quizapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.ComponentActivity
import android.graphics.Color
import android.widget.Switch

class MainActivity : ComponentActivity(), AdapterView.OnItemSelectedListener {

    // how to access the button of activity main
//    private lateinit var applyButton: Button
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        applyButton=findViewById(R.id.applyButton)
//        applyButton.setTextColor(ResourcesCompat.getColor(resources, R.color.purple_700,null));
//        applyButton.text=getString(R.string.apply_button)
//    }


    // basic toggle button with text view
//    private lateinit var toggleShowButton: Button
//    private lateinit var helloWorldTextView: TextView
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.sample_activity1)
//
//        toggleShowButton=findViewById(R.id.toggleShow)
//        toggleShowButton.text=getString(R.string.Hide)
//        toggleShowButton.setOnClickListener { toggleVisibility() }
//
//        helloWorldTextView=findViewById(R.id.helloWorldTV)
//        helloWorldTextView.text=getString(R.string.helloWorldTV)
//
//    }
//
//    fun toggleVisibility() {
//        if (helloWorldTextView.visibility == View.VISIBLE){
//            helloWorldTextView.visibility=View.INVISIBLE
//            toggleShowButton.text=getString(R.string.Show)
//        }
//        else {
//            helloWorldTextView.visibility=View.VISIBLE
//            toggleShowButton.text=getString(R.string.Hide)
//        }
//    }

    private val inches=2.54
    private val M=100
    private val KM=1000
    private lateinit var dropdownMenu: Spinner
    private lateinit var convertButton: Button
    private lateinit var answerView: TextView
    private var dropDownValue=0
    private lateinit var input: EditText
    private lateinit var pageView: View
    private lateinit var toggleModeSwitch: Switch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.convert_to_cm)
        setTitle(resources.getText(R.string.convert_to_cm));

        input=findViewById(R.id.input)

        dropdownMenu=findViewById(R.id.dropDownMenu)
        addDropDownValues(dropdownMenu)
        dropdownMenu.onItemSelectedListener=this


        answerView=findViewById(R.id.answerView)

        convertButton=findViewById(R.id.convert_button)
        convertButton.setOnClickListener{convert()}

        toggleModeSwitch=findViewById(R.id.toggleModeSwitch)
        toggleModeSwitch.setOnClickListener{toggleMode()}
        pageView=findViewById(R.id.pageView)
    }

    private fun toggleMode(){
        if (toggleModeSwitch.isChecked) {
            toggleModeSwitch.text= getString(R.string.light)
            pageView.setBackgroundColor(Color.LTGRAY)
        } else {
            toggleModeSwitch.text= getString(R.string.gray)
            pageView.setBackgroundColor(Color.WHITE)
        }
    }

    private fun convert(){
        if (input.text.toString().isEmpty()){
            answerView.text= getString(R.string.null_input_message)
        }
        else {
            println(dropDownValue)
            if(dropDownValue==0){
                answerView.text= (input.text.toString().toDouble()*inches).toString()
            }
            else if(dropDownValue==1){
                answerView.text= (input.text.toString().toDouble()*M).toString()
            }
            else if(dropDownValue==2){
                answerView.text= (input.text.toString().toDouble()*KM).toString()
            }
        }
    }

    private fun addDropDownValues(dropdownMenu: Spinner){
        ArrayAdapter.createFromResource(
            this,
            R.array.convert_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            dropdownMenu.adapter = adapter
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            println(position)
            dropDownValue=position
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        if (parent != null) {
            dropDownValue=0
        }
    }

}

