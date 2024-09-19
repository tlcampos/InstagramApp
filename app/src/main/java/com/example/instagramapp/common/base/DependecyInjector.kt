package com.example.instagramapp.common.base

import com.example.instagramapp.login.data.FakeDataSource
import com.example.instagramapp.login.data.LoginRepository

object DependecyInjector {
    fun loginRepository(): LoginRepository {
        return LoginRepository(FakeDataSource())
    }
}