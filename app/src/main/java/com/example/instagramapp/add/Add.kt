package com.example.instagramapp.add

import android.net.Uri
import com.example.instagramapp.common.base.BasePresenter
import com.example.instagramapp.common.base.BaseView

interface Add {

    interface Presenter : BasePresenter {
        fun createPost(uri: Uri, caption: String)
    }

    interface View: BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayRequestSuccess()
        fun displayRequestFailure(message: String)
    }
}