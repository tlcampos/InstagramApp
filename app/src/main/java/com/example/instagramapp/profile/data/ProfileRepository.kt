package com.example.instagramapp.profile.data

import com.example.instagramapp.common.base.RequestCallBack
import com.example.instagramapp.common.model.Post
import com.example.instagramapp.common.model.UserAuth

class ProfileRepository(private val dataSource: ProfileDataSource) {

    fun fetchUserProfile(userUUID: String, callback: RequestCallBack<UserAuth>){
        dataSource.fetchUserProfile(userUUID, callback)
    }

    fun fetchUserPosts(userUUID: String, callback: RequestCallBack<List<Post>>){
        dataSource.fetchUserPosts(userUUID, callback)
    }
}