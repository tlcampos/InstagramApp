package com.example.instagramapp.login.data

interface LoginDataSource {
    fun login(email: String, password: String, callback: LoginCallback)
}