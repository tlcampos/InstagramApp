package com.example.instagramapp.profile.data

import com.example.instagramapp.common.base.RequestCallBack
import com.example.instagramapp.common.model.Database
import com.example.instagramapp.common.model.Post
import com.example.instagramapp.common.model.UserAuth

class ProfileLocalDataSource(
    private val profileCache: ProfileCache<UserAuth>
) : ProfileDataSource {

    override fun fetchUserProfile(userUUID: String, callBack: RequestCallBack<UserAuth>) {
        val userAuth = profileCache.get(userUUID)
        if (userAuth != null) {
            callBack.onSuccess(userAuth)
        } else {
            callBack.onFailure("Usuário não encontrado")

        }
    }

    override fun fetchUserPosts(userUUID: String, callBack: RequestCallBack<List<Post>>) {
        //TODO("Not yet implemented")
    }

    override fun fetchSession(): UserAuth {
        return Database.sessionAuth ?: throw RuntimeException("Usuário não logado!!")
    }

    override fun putUser(response: UserAuth) {
        profileCache.put(response)
    }
}