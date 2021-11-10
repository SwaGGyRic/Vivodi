package com.example.vivodi.type

class User {
    lateinit var login:String
    lateinit var password:String
    fun getInfo():String{
        return login + " - " + password
    }
}