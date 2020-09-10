package com.swapnesh.triviaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.swapnesh.triviaapp.Contants.USERNAME
import kotlinx.android.synthetic.main.activity_summery.*
import kotlinx.android.synthetic.main.booklayout.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class SummeryActivity : AppCompatActivity() {

    lateinit var triviaDatabaseHelper: TriviaDatabaseHelper
    var cal = Calendar.getInstance()
    var datestring: String?=""
    var daycurrent  :Int?=null
    var monthcurrent :Int?=null
    var yearccurrent :Int?=null
    var time: String?=""
    var question1: String?=""
    var question2: String?=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summery)
        triviaDatabaseHelper = TriviaDatabaseHelper(this)
        question1 = "Who is the best cricketer in the world?"
        question2 = "What are the colors in the Indian national flag?"
        daycurrent = cal.get(Calendar.DAY_OF_MONTH)
        monthcurrent = (cal.get(Calendar.MONTH)+1)
        yearccurrent = cal.get(Calendar.YEAR)

        namepersoon.text ="Hello "+USERNAME
        answerone.text = Contants.AnswerONE

        val output = Contants.AnswerTwo.replace("[", "")
        Log.e("Adapter", "output--------" + output)
        val outputnew = output.replace("]", "")
        Log.e("", "outputnew--------" + outputnew)
        answertwo.text = outputnew
    

        datestring=  SimpleDateFormat("dd").format(cal.time)+"-"+ SimpleDateFormat("MM").format(cal.time)+"-"+yearccurrent

        finish.setOnClickListener {

            val df: DateFormat = SimpleDateFormat(" hh:mm a")
            time = df.format(Calendar.getInstance().time)

            var result = triviaDatabaseHelper.addBeneficiary(Questiondetails(name=Contants.USERNAME,time = time!!,date = datestring!!,questionOne =question1!! ,questionTwo = question2!!,answerone =Contants.AnswerONE ,answertwo =Contants.AnswerTwo ))
            Toast.makeText(applicationContext,"REsult"+result, Toast.LENGTH_SHORT).show()

            intent = Intent(this, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        history.setOnClickListener {
            val df: DateFormat = SimpleDateFormat(" hh:mm a")
            time = df.format(Calendar.getInstance().time)

            var result = triviaDatabaseHelper.addBeneficiary(Questiondetails(name=Contants.USERNAME,time = time!!,date = datestring!!,questionOne =question1!! ,questionTwo = question2!!,answerone =Contants.AnswerONE ,answertwo =Contants.AnswerTwo ))
            Toast.makeText(applicationContext,"REsult"+result, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HistoryActivity::class.java))
        }

    }
}