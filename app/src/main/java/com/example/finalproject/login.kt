package com.example.finalproject

class login{
    var id: Int = 0
    var username: String? = null
    var password: String? = null

    constructor(id: Int, username: String, password: String){
        this.id = id
        this.username = username
        this.password = password
    }
    constructor(username: String, password: String){
        this.username = username
        this.password = password
    }
}