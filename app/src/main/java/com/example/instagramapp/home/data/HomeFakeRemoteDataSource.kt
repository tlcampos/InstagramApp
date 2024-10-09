package com.example.instagramapp.home.data

import android.os.Handler
import android.os.Looper
import com.example.instagramapp.common.base.RequestCallBack
import com.example.instagramapp.common.model.Database
import com.example.instagramapp.common.model.Post

class HomeFakeRemoteDataSource : HomeDataSource {

    override fun fetchFeed(userUUID: String, callBack: RequestCallBack<List<Post>>) {
        Handler(Looper.getMainLooper()).postDelayed({

            val feed = Database.feeds[userUUID]

            callBack.onSuccess(feed?.toList() ?: emptyList())

            callBack.onComplete()
        }, 1000)

    }
}
