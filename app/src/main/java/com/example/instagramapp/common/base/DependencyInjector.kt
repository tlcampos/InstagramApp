package com.example.instagramapp.common.base

import com.example.instagramapp.login.data.FakeDataSource
import com.example.instagramapp.login.data.LoginRepository
import com.example.instagramapp.register.data.FakeRegisterDataSource
import com.example.instagramapp.register.data.RegisterRepository

object DependencyInjector {
    fun loginRepository(): LoginRepository {
        return LoginRepository(FakeDataSource())
    }
    fun registerEmailRepository(): RegisterRepository {
        return RegisterRepository(FakeRegisterDataSource())
    }
}