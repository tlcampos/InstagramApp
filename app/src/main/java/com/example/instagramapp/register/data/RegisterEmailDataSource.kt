package com.example.instagramapp.register.data

interface RegisterEmailDataSource {
    fun create(email: String, callback: RegisterEmailCallback)// {
       // val userAuth = Database.usersAuth.firstOrNull { email == it.email }
}