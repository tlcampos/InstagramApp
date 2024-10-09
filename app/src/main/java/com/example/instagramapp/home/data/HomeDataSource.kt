package com.example.instagramapp.home.data

import com.example.instagramapp.common.base.RequestCallBack
import com.example.instagramapp.common.model.Post
import com.example.instagramapp.common.model.UserAuth

interface HomeDataSource {

    fun fetchFeed(userUUID: String, callBack: RequestCallBack<List<Post>>)

    fun fetchSession() : UserAuth  {throw UnsupportedOperationException()}

    fun putFeed(response: List<Post>) {throw UnsupportedOperationException()}
}