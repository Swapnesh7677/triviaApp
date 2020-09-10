package com.swapnesh.triviaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {

    lateinit var triviaDatabaseHelper: TriviaDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        triviaDatabaseHelper = TriviaDatabaseHelper(this)

        if (triviaDatabaseHelper.readAllUsers().isNotEmpty() ){
            var users = triviaDatabaseHelper.readAllUsers()
            Log.e("Article","size"+users.size)
           // Toast.makeText(applicationContext,"SIZE"+users.get(0).name, Toast.LENGTH_SHORT).show()
            list.setHasFixedSize(true)
            val layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            list.layoutManager = layoutManager
            list.adapter = TriviaAdapter(users){
                Log.v("Article", it.name.toString())
            }
            (list.adapter as TriviaAdapter).notifyDataSetChanged()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        intent = Intent(this, SplashActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}