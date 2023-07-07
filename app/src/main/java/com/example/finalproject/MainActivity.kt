package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun addLogin(view: View){
        val loginHandler = loginHandler(this,null,null,1)

        val username = username.text.toString()
        val password = password.text.toString()

        val login = login(username, password)

        loginHandler.addLogin(login)
        Toast.makeText(applicationContext, "Welcome $username",Toast.LENGTH_SHORT).show()
    }
    fun findLogin(view: View){
        val loginHandler = loginHandler(this,null,null,1)

        val login = loginHandler.findLogin(username)

        if (login != null){
            loginID.text = login.id.toString()
        }
        else{
            Toast.makeText(applicationContext,"Bad Login",Toast.LENGTH_SHORT).show()
        }
    }
}