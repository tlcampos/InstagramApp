package com.example.instagramapp.splash

import com.example.instagramapp.common.base.BasePresenter
import com.example.instagramapp.common.base.BaseView

interface Splash {

    interface Presenter : BasePresenter {
        fun authenticated()
    }

    interface View : BaseView<Presenter> {
        fun goToMainScreen()
        fun goToLoginScreen()
    }
}