package com.example.instagramapp.profile.presentation

import com.example.instagramapp.common.model.Post
import com.example.instagramapp.common.model.UserAuth
import com.example.instagramapp.profile.Profile

class ProfileState(
    private val posts: List<Post>?,
    private val userAuth: UserAuth?
) : Profile.State {
    override fun onFetchUserProfile(): UserAuth? {
        return userAuth
    }

    override fun onFetchUserPosts(): List<Post>? {
        return posts

    }
}