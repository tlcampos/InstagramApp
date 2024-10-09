package com.example.instagramapp.profile.data

import com.example.instagramapp.common.model.Post
import com.example.instagramapp.common.model.UserAuth

class ProfileDataSourceFactory(
    private val profileCache: ProfileCache<UserAuth>,
    private val postsCache: ProfileCache<List<Post>>,
) {
    fun createLocalDataSource(): ProfileDataSource {
        return ProfileLocalDataSource(profileCache, postsCache)
    }

    fun createFromUser(): ProfileDataSource {
        if (profileCache.isCached()){
            return ProfileLocalDataSource(profileCache, postsCache)
        }
            return ProfileFakeRemoteDataSource()
        }

    fun createFromPosts(): ProfileDataSource {
        if (profileCache.isCached()){
            return ProfileLocalDataSource(profileCache, postsCache)
        }
        return ProfileFakeRemoteDataSource()
    }

}