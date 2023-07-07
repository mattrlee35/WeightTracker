package com.example.finalproject

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues

class loginHandler(context: Context, name: String?,
        factory: SQLiteDatabase.CursorFactory?, version: Int) :
        SQLiteOpenHelper(context, DBHandler.DATABASE_NAME,
        factory, DBHandler.DATABASE_VERSION
        ) {

        override fun onCreate(db: SQLiteDatabase) {
            val CREATE_TABLE_LOGIN = ("CREATE TABLE" + TABLE_LOGIN + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_USERNAME + " TEXT," + COLUMN_PASSWORD + "TEXT" + ")")
            db.execSQL(CREATE_TABLE_LOGIN)

        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                               newVersion: Int) {
            db.execSQL(TABLE_LOGIN)
            onCreate(db)

        }
        fun addLogin(login: login){
            val values = ContentValues()
            values.put(COLUMN_PASSWORD, login.password)
            values.put(COLUMN_USERNAME, login.username)

            val db = this.writableDatabase

            db.insert(TABLE_LOGIN,null,values)
            db.close()
        }
        fun findLogin(username: String): login? {
            val query = "SELECT * FROM $TABLE_LOGIN WHERE $COLUMN_USERNAME = \"$username\""

            val db = this.writableDatabase

            val result = db.rawQuery(query,null)
            var login: login? = null

            if (result.moveToFirst())
            {
                result.moveToFirst()

                val id = Integer.parseInt(result.getString(0))
                val username = result.getString(1)
                val password = result.getString(2)
                login = login(id, username, password)
                result.close()
            }
            db.close()
            return login
        }
        companion object {

            private val DATABASE_VERSION = 1
            private val DATABASE_NAME = "loginDB.db"
            val TABLE_LOGIN = "login"

            val COLUMN_ID = "_id"
            val COLUMN_USERNAME = "username"
            val COLUMN_PASSWORD = "password"
        }
    }﻿
}