package com.example.instagramapp.home.data

import com.example.instagramapp.common.base.Cache
import com.example.instagramapp.common.base.RequestCallBack
import com.example.instagramapp.common.model.Database
import com.example.instagramapp.common.model.Post
import com.example.instagramapp.common.model.UserAuth

class HomeLocalDataSource(private val cache: Cache<List<Post>>) : HomeDataSource {

    override fun fetchFeed(userUUID: String, callBack: RequestCallBack<List<Post>>) {
        val posts = cache.get(userUUID)
        if (posts != null) {
            callBack.onSuccess(posts)
        } else {
            callBack.onFailure("posts não existem")
        }
        callBack.onComplete()
    }

    override fun fetchSession(): UserAuth {
        return Database.sessionAuth ?: throw RuntimeException("Usuário não logado!!")
    }
    override fun putFeed(response: List<Post>) {
        cache.put(response)
    }
}