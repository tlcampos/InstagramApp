package com.example.instagramapp.profile.presentation

import com.example.instagramapp.common.model.Post
import com.example.instagramapp.common.model.UserAuth
import com.example.instagramapp.profile.Profile

class ProfileState(
    private val posts: List<Post>?,
    private val userAuth: UserAuth?
) : Profile.State {
    override fun fetchUserProfile(): UserAuth? {
        return userAuth
    }

    override fun fetchUserPosts(): List<Post>? {
        return posts
    }
}