package com.example.instagramapp.login.data

interface LoginCallback {
    fun onSucess()
    fun onFailure(message: String)
    fun onComplete()
}