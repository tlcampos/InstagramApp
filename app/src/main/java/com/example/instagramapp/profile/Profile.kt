package com.example.instagramapp.profile

import com.example.instagramapp.common.base.BasePresenter
import com.example.instagramapp.common.base.BaseView
import com.example.instagramapp.common.model.Post
import com.example.instagramapp.common.model.UserAuth

interface Profile {

    interface Presenter : BasePresenter {
        fun fetchUserProfile()
        fun fetchUserPosts()
    }
    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayUserProfile(userAuth: UserAuth)
        fun displaRequestFailure(message: String)
        fun displayEmptyPosts()
        fun displayFullPosts(posts: List<Post>)
    }

}