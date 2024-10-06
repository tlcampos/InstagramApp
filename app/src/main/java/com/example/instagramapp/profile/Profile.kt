package com.example.instagramapp.profile

import com.example.instagramapp.common.base.BasePresenter
import com.example.instagramapp.common.base.BaseView
import com.example.instagramapp.common.model.Post
import com.example.instagramapp.common.model.UserAuth

interface Profile {

    interface StateFullPresenter<S:State> : BasePresenter {
        fun subscribe(state:S?)
        fun getState():S
    }

    interface State{
        fun onFetchUserProfile() : UserAuth?
        fun onFetchUserPosts() : List<Post>?
    }
    interface Presenter : StateFullPresenter<State> {
    }


    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayUserProfile(userAuth: UserAuth)
        fun displaRequestFailure(message: String)
        fun displayEmptyPosts()
        fun displayFullPosts(posts: List<Post>)
    }

}