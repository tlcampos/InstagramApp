package com.example.instagramapp.profile

import com.example.instagramapp.common.base.BasePresenter
import com.example.instagramapp.common.base.BaseView
import com.example.instagramapp.common.model.Post
import com.example.instagramapp.common.model.UserAuth

interface Profile {

    interface StatefulPresenter<S: State>: BasePresenter {
        fun subscribe(state: S?)
        fun getState(): S
    }

    interface State {
        fun fetchUserProfile() : UserAuth?
        fun fetchUserPosts()  : List<Post>?
    }

    interface Presenter : StatefulPresenter<State> {
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayUserProfile(userAuth: UserAuth)
        fun displayRequestFailure(message: String)
        fun displayEmptyPosts()
        fun displayFullPosts(posts: List<Post>)
    }

}