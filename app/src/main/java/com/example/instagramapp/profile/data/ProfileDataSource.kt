package com.example.instagramapp.profile.data

import com.example.instagramapp.common.base.RequestCallBack
import com.example.instagramapp.common.model.Post
import com.example.instagramapp.common.model.UserAuth

interface ProfileDataSource {

    fun fetchUserProfile(userUUID: String, callBack: RequestCallBack<UserAuth>)

    fun fetchUserPosts(userUUID: String, callBack: RequestCallBack<List<Post>>)

    fun fetchSession() : UserAuth  {throw UnsupportedOperationException()}

    fun putUser(response: UserAuth) {throw UnsupportedOperationException()}

    fun putPosts(response: List<Post>?) {throw UnsupportedOperationException()}
}