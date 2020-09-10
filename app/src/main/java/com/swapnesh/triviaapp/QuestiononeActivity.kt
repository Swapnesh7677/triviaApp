package com.swapnesh.triviaapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.swapnesh.triviaapp.Contants.AnswerONE
import kotlinx.android.synthetic.main.activity_questionone.*

class QuestiononeActivity : AppCompatActivity() {

    lateinit var radioButton: RadioButton
    var answer: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionone)



        next_button.setOnClickListener {
            try {
                val intSelectButton: Int = radiogroup!!.checkedRadioButtonId
                radioButton = findViewById(intSelectButton)
                answer = radioButton.text as String?
            } catch (e: Exception) {
            }
            // Toast.makeText(baseContext, radioButton.text, Toast.LENGTH_SHORT).show()
            if(answer.equals("")){
                Toast.makeText(applicationContext,"Please select answer", Toast.LENGTH_SHORT).show()
            }else{

                AnswerONE = answer.toString()
                startActivity(Intent(this, Question2Activity::class.java))
            }




        }
    }
}