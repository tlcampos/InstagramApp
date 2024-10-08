package com.example.instagramapp.profile.data

import com.example.instagramapp.common.model.UserAuth

class ProfileDataSourceFactory(
    private val profileCache: ProfileCache<UserAuth>,
) {
    fun createLocalDataSource(): ProfileDataSource {
        return ProfileLocalDataSource(profileCache)
    }

    fun createFromUser(userAuth: UserAuth): ProfileDataSource {
        if (profileCache.isCached()) return ProfileFakeRemoteDataSource()
        return ProfileLocalDataSource(profileCache)
    }

}