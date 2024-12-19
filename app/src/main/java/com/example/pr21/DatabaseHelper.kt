package com.example.composesqliteapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, SCHEMA) {

    companion object {
        private const val DATABASE_NAME = "userstore.db"
        private const val SCHEMA = 1
        const val TABLE = "users"
        const val COLUMN_ID = "_id"
        const val COLUMN_NAME = "name"
        const val COLUMN_YEAR = "year"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT, $COLUMN_YEAR INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE")
        onCreate(db)
    }

    fun insertUser(name: String, year: Int) {
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_YEAR, year)
        }
        writableDatabase.insert(TABLE, null, values)
    }

    fun getAllUsers(): Cursor {
        return readableDatabase.query(TABLE, null, null, null, null, null, null)
    }
}
