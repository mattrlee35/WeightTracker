package com.example.finalproject

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues

class DBHandler (context: Context, name: String?,
    factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, DATABASE_NAME,
    factory, DATABASE_VERSION) {

        override fun onCreate(db: SQLiteDatabase) {
            val SQL_WEIGHTTRACK_TABLE = ("CREATE TABLE" + TABLE_WEIGHTTRACK + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"+ COLUMN_WEIGHT +"INTEGER"+ COLUMN_DATE +"STRING" + ")")
            db.execSQL(SQL_WEIGHTTRACK_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                               newVersion: Int) {
            db.execSQL(TABLE_WEIGHTTRACK)
            onCreate(db)
        }
        companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "WeightTrack.db"
        val TABLE_WEIGHTTRACK = "weightTrack"

        val COLUMN_ID = "id"
        val COLUMN_WEIGHT = "weight"
        val COLUMN_DATE = "date"
    }
    //function to add rows to the database
    fun addWeightTrack(weightTrack: WeightTrack)
    {
        val values = ContentValues()
        values.put(COLUMN_WEIGHT, weightTrack.weight)
        values.put(COLUMN_DATE, weightTrack.date)

        val db = this.writableDatabase

        db.insert(TABLE_WEIGHTTRACK,null, values)
        db.close()
    }
    //function to delete rows from the database
    fun deleteWeightTrack(id: Int): Boolean {
        var result = false

        val query = "SELECT * FROM $TABLE_WEIGHTTRACK WHERE $COLUMN_ID = \"$id\""
        val db = this.writableDatabase

        val cursor = db.rawQuery(query, null)

        if(cursor.moveToFirst()){
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(TABLE_WEIGHTTRACK, COLUMN_ID + "=?",arrayOf(id.toString()))
            cursor.close()
            result = true
        }
        db.close()
        return result

    }

    fun readWeightTrack(): MutableList<WeightTrack>{
        val list: MutableList<WeightTrack> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLE_WEIGHTTRACK"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst())
        {
            do {
                val weight = WeightTrack()
                weight.id = result.getString(result.getColumnIndex(COLUMN_ID)).toInt()
                weight.weight = result.getString(result.getColumnIndex(COLUMN_WEIGHT)).toInt()
                weight.date = result.getString(result.getColumnIndex(COLUMN_DATE))
            }
                while(result.moveToNext())
        }
        return list
    }

}