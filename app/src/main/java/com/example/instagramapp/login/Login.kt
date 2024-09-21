package com.example.instagramapp.login

import androidx.annotation.StringRes
import com.example.instagramapp.common.base.BasePresenter
import com.example.instagramapp.common.base.BaseView

interface Login {

    //Presenter

    interface Presenter : BasePresenter {
        fun login(email: String, password: String)
    }

    //View
    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayEmailFailure(@StringRes emailError: Int?)
        fun displayPasswordFailure(@StringRes passwordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized(message: String)
    }
}