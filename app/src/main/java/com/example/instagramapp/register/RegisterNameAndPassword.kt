package com.example.instagramapp.register

import androidx.annotation.StringRes
import com.example.instagramapp.common.base.BasePresenter
import com.example.instagramapp.common.base.BaseView

interface RegisterNameAndPassword {

    interface Presenter : BasePresenter {
        fun create(email: String, name: String, password: String, confirm: String)
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayNameFailure(@StringRes nameError: Int?)
        fun displayPasswordFailure(@StringRes passwordError: Int?)
        fun onCreateFailure(message: String)
        fun onCreateSuccess(name: String)
    }
}