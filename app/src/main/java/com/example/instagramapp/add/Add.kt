package com.example.instagramapp.add

import com.example.instagramapp.common.base.BasePresenter
import com.example.instagramapp.common.base.BaseView

interface Add {

    interface Presenter : BasePresenter {
    }

    interface View: BaseView<Presenter> {
    }
}