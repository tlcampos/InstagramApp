package com.example.instagramapp.common.base

import com.example.instagramapp.login.data.FakeDataSource
import com.example.instagramapp.login.data.LoginRepository

object DependencyInjector {
    fun loginRepository(): LoginRepository {
        return LoginRepository(FakeDataSource())
    }
}