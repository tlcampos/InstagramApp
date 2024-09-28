package com.example.instagramapp.register

import android.net.Uri
import com.example.instagramapp.common.base.BasePresenter
import com.example.instagramapp.common.base.BaseView

interface RegisterPhoto {

    interface Presenter : BasePresenter {
        fun updateUser(photoUri: Uri)
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)

        fun onUpdateFailure(message: String)

        fun onUpdateSuccess()
    }
}