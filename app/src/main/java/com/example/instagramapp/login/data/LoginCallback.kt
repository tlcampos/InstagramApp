package com.example.instagramapp.login.data

import com.example.instagramapp.common.model.UserAuth

interface LoginCallback {
    fun onSucess(userAuth: UserAuth)
    fun onFailure(message: String)
    fun onComplete()
}