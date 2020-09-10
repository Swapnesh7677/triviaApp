package com.swapnesh.triviaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_question2.*

class Question2Activity : AppCompatActivity() {
    val arrayList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question2)



        checkbox1.setOnClickListener {
            if (checkbox1.isChecked) {
                arrayList.add(checkbox1.text.toString())
            } else {
                arrayList.remove(checkbox1.text.toString())
            }
        }

        checkbox2.setOnClickListener {
            if (checkbox2.isChecked) {
                arrayList.add(checkbox2.text.toString())
            } else {
                arrayList.remove(checkbox2.text.toString())
            }
        }
        checkbox3.setOnClickListener {
            if (checkbox3.isChecked) {
                arrayList.add(checkbox3.text.toString())
            } else {
                arrayList.remove(checkbox3.text.toString())
            }
        }
        checkbox4.setOnClickListener {
            if (checkbox4.isChecked) {
                arrayList.add(checkbox4.text.toString())
            } else {
                arrayList.remove(checkbox4.text.toString())
            }
        }

        continue_button.setOnClickListener {
            if(arrayList.size==0){
                Toast.makeText(applicationContext,"Please select ansers", Toast.LENGTH_SHORT).show()
            }else{
                Contants.AnswerTwo = arrayList.toString()
                startActivity(Intent(this, SummeryActivity::class.java))
            }
        }

    }
}