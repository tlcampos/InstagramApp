package com.example.instagramapp.profile.data

import com.example.instagramapp.common.base.RequestCallBack
import com.example.instagramapp.common.model.Post
import com.example.instagramapp.common.model.UserAuth

class ProfileRepository(private val dataSourceFactory: ProfileDataSourceFactory) {

    fun clearCache(){
        val localDataSource = dataSourceFactory.createLocalDataSource()
        localDataSource.putPosts(null)
    }

    fun fetchUserProfile(callback: RequestCallBack<UserAuth>){
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userAuth = localDataSource.fetchSession()

        val dataSource = dataSourceFactory.createFromUser()

        dataSource.fetchUserProfile(userAuth.uuid, object : RequestCallBack<UserAuth> {
            override fun onSuccess(data: UserAuth) {
                localDataSource.putUser(data)
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

    fun fetchUserPosts(callback: RequestCallBack<List<Post>>){
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userAuth = localDataSource.fetchSession()

        val dataSource = dataSourceFactory.createFromPosts()

        dataSource.fetchUserPosts(userAuth.uuid, object : RequestCallBack<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                localDataSource.putPosts(data)
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
