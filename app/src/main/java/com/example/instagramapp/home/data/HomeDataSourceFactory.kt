package com.example.instagramapp.home.data

import com.example.instagramapp.common.base.Cache
import com.example.instagramapp.common.model.Post
import com.example.instagramapp.common.model.UserAuth

class HomeDataSourceFactory(
    private val feedCache: Cache<List<Post>>,
) {
    fun createLocalDataSource(): HomeDataSource {
        return HomeLocalDataSource(feedCache)
    }
    fun createFromFeed(): HomeDataSource {
        if (feedCache.isCached()){
            return HomeLocalDataSource(feedCache)
        }
        return HomeFakeRemoteDataSource()
    }

}