package com.swapnesh.triviaapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.swapnesh.triviaapp.Contants.USERNAME
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_questionone.*

class MainActivity : AppCompatActivity() {
    lateinit var preference : SharedPreferences
    lateinit var editor : SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preference=getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        editor=preference.edit()


        start_button.setOnClickListener {

            if (name.text.toString().equals("")){
                Toast.makeText(applicationContext,"Please Enter name", Toast.LENGTH_SHORT).show()
            }else{
                USERNAME = name.text.toString()
                startActivity(Intent(this, QuestiononeActivity::class.java))
            }



        }
    }
}