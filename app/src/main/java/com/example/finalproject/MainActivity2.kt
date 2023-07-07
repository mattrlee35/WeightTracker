package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
    fun addWeight(view: View){
        val dbHandler = DBHandler(this, null,null,1)

        val date = weightTrackDate.text.toString())
        val weight = WeightTrack(weight.text.toString(),date)

        dbHandler.addWeightTrack(weight)
        }
    fun deleteWeight(view: View){
        val dbHandler = DBHandler(this,null,null,1)

        val result = dbHandler.deleteWeightTrack(id.text.toString())

    }
    fun readWeight(view: View){
        val dbHandler = DBHandler(this, null, null, 1)

        val result = dbHandler.readWeightTrack()
    }
}