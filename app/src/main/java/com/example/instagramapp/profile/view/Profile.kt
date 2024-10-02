package com.example.instagramapp.profile.view

import com.example.instagramapp.common.base.BasePresenter
import com.example.instagramapp.common.base.BaseView

interface Profile {

    interface Presenter : BasePresenter {
        fun fetchUserProfile()
        fun fetchUserPosts()
    }
    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        //Novos Metodos Aqui
    }

}