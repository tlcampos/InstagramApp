package com.example.instagramapp.home.data

import com.example.instagramapp.common.base.RequestCallBack
import com.example.instagramapp.common.model.Post
import com.example.instagramapp.common.model.UserAuth

class HomeRepository(private val dataSourceFactory: HomeDataSourceFactory) {

    fun fetchFeed(callback: RequestCallBack<List<Post>>){
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userAuth = localDataSource.fetchSession()

        val dataSource = dataSourceFactory.createFromFeed()

        dataSource.fetchFeed(userAuth.uuid, object : RequestCallBack<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                localDataSource.putFeed(data)
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }

        })
    }
}
