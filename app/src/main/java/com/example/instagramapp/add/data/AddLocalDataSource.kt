package com.example.instagramapp.add.data

import com.example.instagramapp.common.model.Database
import com.example.instagramapp.common.model.UserAuth

class AddLocalDataSource : AddDataSource {

    override fun fetchSession(): UserAuth {
        return Database.sessionAuth ?: throw RuntimeException("Usuário não logado!!")
    }
}