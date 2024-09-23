package com.example.instagramapp.register.data

import android.os.Handler
import android.os.Looper
import com.example.instagramapp.common.model.Database
import com.example.instagramapp.common.model.UserAuth
import java.util.UUID


class FakeRegisterDataSource : RegisterDataSource {

    override fun create(email: String, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.usersAuth.firstOrNull { email == it.email }

            if (userAuth == null) {
                callback.onSuccess()
            } else {
                callback.onFailure("Usuário já cadastrado")
            }

            callback.onComplete()
        }, 200)
    }

    override fun create(email: String, name: String, password: String, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.usersAuth.firstOrNull { email == it.email }

            if (userAuth != null) {
                callback.onFailure("Usuário já cadastrado")
            } else {
                val created = Database.usersAuth.add(
                    UserAuth(UUID.randomUUID().toString(), name, email, password)
                )
                if (created) {
                    callback.onSuccess()
                } else {
                    callback.onFailure("Erro interno no servidor")
                }
            }
            callback.onComplete()
        }, 200)
    }
}