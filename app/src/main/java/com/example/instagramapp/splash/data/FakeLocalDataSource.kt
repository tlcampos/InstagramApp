package com.example.instagramapp.splash.data

import com.example.instagramapp.common.model.Database

class FakeLocalDataSource : SplashDataSource {
    override fun session(callback: SplashCallBack) {
        if (Database.sessionAuth != null) {
            callback.onSuccess()
        } else {
            callback.onFailure()
        }
    }
}