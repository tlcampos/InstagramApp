package com.example.instagramapp.splash.data

class SplashRepository(private val dataSource: SplashDataSource) {

    fun session(callback: SplashCallBack) {
        dataSource.session(callback)
    }
}