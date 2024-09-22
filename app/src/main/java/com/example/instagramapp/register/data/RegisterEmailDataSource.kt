package com.example.instagramapp.register.data

interface RegisterEmailDataSource {
    fun create(email: String, callback: RegisterEmailCallback) {
    }
}