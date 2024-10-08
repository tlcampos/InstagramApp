package com.example.instagramapp.common.base

import com.example.instagramapp.login.data.FakeDataSource
import com.example.instagramapp.login.data.LoginRepository
import com.example.instagramapp.profile.data.ProfileDataSourceFactory
import com.example.instagramapp.profile.data.ProfileFakeRemoteDataSource
import com.example.instagramapp.profile.data.ProfileMemoryCache
import com.example.instagramapp.profile.data.ProfileRepository
import com.example.instagramapp.register.data.FakeRegisterDataSource
import com.example.instagramapp.register.data.RegisterRepository
import com.example.instagramapp.splash.data.FakeLocalDataSource
import com.example.instagramapp.splash.data.SplashRepository

object DependencyInjector {

    fun splashRepository(): SplashRepository {
        return SplashRepository(FakeLocalDataSource())
    }
    fun loginRepository(): LoginRepository {
        return LoginRepository(FakeDataSource())
    }
    fun registerEmailRepository(): RegisterRepository {
        return RegisterRepository(FakeRegisterDataSource())
    }

    fun profileRepository(): ProfileRepository {
        return ProfileRepository(ProfileDataSourceFactory(ProfileMemoryCache))
    }
}