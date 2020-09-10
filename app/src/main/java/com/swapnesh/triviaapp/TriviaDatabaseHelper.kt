package com.swapnesh.triviaapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

class TriviaDatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION) {

    private val DBHelper: TriviaDatabaseHelper? = null
    var mcontext: Context
    private var db: SQLiteDatabase? = null
    private val TAG = javaClass.simpleName
    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_FAVORITE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    SR_NO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USER_NAME + " TEXT NOT NULL, " +
                    TIME + " TEXT NOT NULL, " +
                    DATE + " TEXT NOT NULL," +
                    QUESONE + " TEXT NOT NULL," +
                    QUESTWO + " TEXT NOT NULL," +
                    ANSONE + " TEXT NOT NULL," +
                    ANSTWO +" TEXT NOT NULL" +
                    "); "
        db.execSQL(SQL_CREATE_FAVORITE_TABLE)
        Log.e(TAG, "Create Table ------$SQL_CREATE_FAVORITE_TABLE")
        Log.e(TAG, "Table Created")
        db.execSQL(SQL_CREATE_FAVORITE_TABLE)
        Log.e(TAG, "Check Table-------$SQL_CREATE_FAVORITE_TABLE")
    }

    private val DROP_TABLE =
        "DROP TABLE IF EXISTS $TABLE_NAME"

    @Throws(SQLException::class)
    fun open(): TriviaDatabaseHelper {
        db = DBHelper!!.writableDatabase
        return this
    }

    override fun close() {
        DBHelper!!.close()
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db.execSQL(DROP_TABLE)
    }

    fun addBeneficiary(pojo: Questiondetails) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(USER_NAME, pojo.name)
        values.put(TIME, pojo.time)
        values.put(DATE, pojo.date)
        values.put(QUESONE, pojo.questionOne)
        values.put(QUESTWO, pojo.questionTwo)
        values.put(ANSONE, pojo.answerone)
        values.put(ANSTWO, pojo.answertwo)

        db.insert(TABLE_NAME, null, values)
        Log.e("Added","added")
        db.close()
    }//Table to query
    //columns to return
    //columns for the WHERE clause
    //The values for the WHERE clause
    //group the rows
    //filter by row groups
    //The sort order
    // Traversing through all rows and adding to list
    // return user list

    // array of columns to fetch
    val allBeneficiary: ArrayList<Questiondetails>
        get() { // array of columns to fetch
            val columns = arrayOf(SR_NO, USER_NAME, TIME, DATE, QUESONE , QUESTWO, ANSONE , ANSTWO)
            // sorting orders
            val sortOrder = "$SR_NO DESC"

            val details = ArrayList<Questiondetails>()
            val db = this.readableDatabase
            var cursor: Cursor? = null
            try {
                cursor = db.rawQuery("select * from " +TABLE_NAME, null)
            } catch (e: SQLiteException) {
             //   db.execSQL(SQL_CREATE_ENTRIES)
                return ArrayList()
            }
             cursor = db.query(
                TABLE_NAME,  //Table to query
                columns,  //columns to return
                null,  //columns for the WHERE clause
                null,  //The values for the WHERE clause
                null,  //group the rows
                null,  //filter by row groups
                sortOrder
            ) //The sort order
            // Traversing through all rows and adding to list

            var name: String
            var time: String
            var date: String
            var ques1: String
            var ques2: String
            var ans1: String
            var ans2: String

            if (cursor!!.moveToFirst()) {
                while (cursor.isAfterLast == false) {

                    name = (cursor.getString(cursor.getColumnIndex(USER_NAME)))
                    time = (cursor.getString(cursor.getColumnIndex(TIME)))
                    date = cursor.getString(cursor.getColumnIndex(DATE))
                    ques1 = cursor.getString(cursor.getColumnIndex(QUESONE))
                    ques2 = cursor.getString(cursor.getColumnIndex(QUESTWO))
                    ans1 = cursor.getString(cursor.getColumnIndex(ANSONE))
                    ans2 = cursor.getString(cursor.getColumnIndex(ANSTWO))

                    details.add(Questiondetails(name, time,date,ques1,ques2,ans1,ans2))
                    cursor.moveToNext()
                }
            }
            cursor.close()
            db.close()
            // return user list
            return details
        }


    fun readAllUsers(): ArrayList<Questiondetails> {
        val users = ArrayList<Questiondetails>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + TABLE_NAME, null)
        } catch (e: SQLiteException) {
            //db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }
        var name: String
        var time: String
        var date: String
        var ques1: String
        var ques2: String
        var ans1: String
        var ans2: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {

                name = (cursor.getString(cursor.getColumnIndex(USER_NAME)))
                time = (cursor.getString(cursor.getColumnIndex(TIME)))
                date = cursor.getString(cursor.getColumnIndex(DATE))
                ques1 = cursor.getString(cursor.getColumnIndex(QUESONE))
                ques2 = cursor.getString(cursor.getColumnIndex(QUESTWO))
                ans1 = cursor.getString(cursor.getColumnIndex(ANSONE))
                ans2 = cursor.getString(cursor.getColumnIndex(ANSTWO))

                users.add(Questiondetails(name, time,date,ques1,ques2,ans1,ans2))
                cursor.moveToNext()
            }
        }
        return users
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Manager.db"
        const val TABLE_NAME = "trivia"
        const val SR_NO = "SRNO"
        const val USER_NAME = "user_name"
        const val TIME = "in_time"
        const val DATE = "date"
        const val QUESONE = "quesone"
        const val QUESTWO = "questwo"
        const val ANSONE = "ansone"
        const val ANSTWO = "anstwo"
    }

    init {
        Log.e(
            TAG,
            "DataBase Name----------->$DATABASE_NAME"
        )
        mcontext = context
    }
}