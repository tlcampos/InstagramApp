package com.example.instagramapp.home

import com.example.instagramapp.common.base.BasePresenter
import com.example.instagramapp.common.base.BaseView
import com.example.instagramapp.common.model.Post

interface Home {

    interface Presenter : BasePresenter{
        fun fetchFeed()
        fun clear()
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayRequestFailure(message: String)
        fun displayEmptyPosts()
        fun displayFullPosts(posts: List<Post>)
    }
}