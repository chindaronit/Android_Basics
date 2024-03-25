package com.example.quizapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity


class Converter: ComponentActivity(), AdapterView.OnItemSelectedListener {

    private val inches=2.54
    private val m=100
    private val km=1000
    private lateinit var dropdownMenu: Spinner
    private lateinit var convertButton: Button
    private lateinit var answerView: TextView
    private var dropDownValue=0
    private lateinit var input: EditText
    private lateinit var pageView: View
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var toggleModeSwitch: Switch
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButton: RadioButton
    private lateinit var thanksView: TextView
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
        toggleModeSwitch.setOnCheckedChangeListener{toggleModeSwitch,isChecked ->
            if(isChecked){
                toggleModeSwitch.text= getString(R.string.light)
                pageView.setBackgroundColor(Color.LTGRAY)
            }
            else{
                toggleModeSwitch.text= getString(R.string.gray)
                pageView.setBackgroundColor(Color.WHITE)
            }
        }

        pageView=findViewById(R.id.pageView)
        thanksView=findViewById(R.id.Thanks)

        radioGroup=findViewById(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            radioButton=findViewById(checkedId)

            when(radioButton.id){
                R.id.yesButton -> {
                    Toast.makeText(this,R.string.thank_you_happy, Toast.LENGTH_SHORT).show()
                }
                R.id.noButton -> {
                    Toast.makeText(this,R.string.thank_you_sad, Toast.LENGTH_SHORT).show()
                }
            }
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
                answerView.text= (input.text.toString().toDouble()*m).toString()
            }
            else if(dropDownValue==2){
                answerView.text= (input.text.toString().toDouble()*km).toString()
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