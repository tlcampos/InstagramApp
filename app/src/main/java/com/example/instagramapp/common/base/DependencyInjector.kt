package com.example.instagramapp.common.base

import com.example.instagramapp.login.data.FakeDataSource
import com.example.instagramapp.login.data.LoginRepository
import com.example.instagramapp.register.data.FakeRegisterEmailDataSource
import com.example.instagramapp.register.data.RegisterEmailRepository

object DependencyInjector {
    fun loginRepository(): LoginRepository {
        return LoginRepository(FakeDataSource())
    }
    fun registerEmailRepository(): RegisterEmailRepository {
        return RegisterEmailRepository(FakeRegisterEmailDataSource())
    }
}