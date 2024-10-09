package com.example.instagramapp.home

import com.example.instagramapp.common.base.BasePresenter
import com.example.instagramapp.common.base.BaseView
import com.example.instagramapp.common.model.Post
import com.example.instagramapp.common.model.UserAuth

interface Home {

    interface Presenter : BasePresenter{
        fun fetchFeed()
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayRequestFailure(message: String)
        fun displayEmptyPosts()
        fun displayFullPosts(posts: List<Post>)
    }
}