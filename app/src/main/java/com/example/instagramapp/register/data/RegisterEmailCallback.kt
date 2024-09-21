package com.example.instagramapp.register.data

interface RegisterEmailCallback {
    fun onSuccess()
    fun onFailure(message: String)
    fun onComplete()

}