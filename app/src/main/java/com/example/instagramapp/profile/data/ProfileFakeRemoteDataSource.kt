package com.example.instagramapp.profile.data

import android.os.Handler
import android.os.Looper
import com.example.instagramapp.common.base.RequestCallBack
import com.example.instagramapp.common.model.Database
import com.example.instagramapp.common.model.Post
import com.example.instagramapp.common.model.UserAuth

class ProfileFakeRemoteDataSource : ProfileDataSource {

    override fun fetchUserProfile(userUUID: String, callBack: RequestCallBack<UserAuth>) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.usersAuth.firstOrNull { userUUID == it.uuid }

            if (userAuth != null) {
                callBack.onSuccess(userAuth)
            } else {
                callBack.onFailure("Usuário não encontrado")
            }
            callBack.onComplete()
        }, 2000)
    }

    override fun fetchUserPosts(userUUID: String, callBack: RequestCallBack<List<Post>>) {
        Handler(Looper.getMainLooper()).postDelayed({

            val posts = Database.posts[userUUID]

            callBack.onSuccess(posts?.toList() ?: emptyList())

            callBack.onComplete()
        }, 2000)

    }
}
