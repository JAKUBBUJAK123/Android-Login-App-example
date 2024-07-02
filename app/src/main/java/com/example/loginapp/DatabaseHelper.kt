package com.example.loginapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    companion object{
        private  const val DATABASE_VERSION = 1
        private  const val DATABASE_NAME = "Accounts"
        private  const val TABLE_NAME = "Users"
        private  const val COLUMN_ID = "id"
        private  const val COLUMN_EMAIL = "email"
        private  const val COLUMN_PASSWORD = "password"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = ("Create Table $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_EMAIL TEXT UNIQUE,"
                + "$COLUMN_PASSWORD TEXT)"
                )
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun addUser(email :String, password : String) :Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_EMAIL,email)
        contentValues.put(COLUMN_PASSWORD,password)

        return db.insert(TABLE_NAME,null,contentValues)
    }
    fun deleteDatabase(context: Context) : Boolean{
        return context.deleteDatabase(DATABASE_NAME)
    }
    fun isValidUser(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_EMAIL=? AND $COLUMN_PASSWORD=?"
        val cursor = db.rawQuery(query, arrayOf(email, password))
        val count = cursor.count
        cursor.close()
        return count > 0
    }

}