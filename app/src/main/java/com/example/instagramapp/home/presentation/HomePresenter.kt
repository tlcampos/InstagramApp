package com.example.instagramapp.home.presentation

import com.example.instagramapp.common.base.RequestCallBack
import com.example.instagramapp.common.model.Post
import com.example.instagramapp.home.Home
import com.example.instagramapp.home.data.HomeRepository

class HomePresenter(
    private var view: Home.View?,
    private val repository: HomeRepository
) : Home.Presenter {

    override fun clear() {
        repository.clearCache()
    }

    override fun fetchFeed() {
        view?.showProgress(true)
        repository.fetchFeed(object : RequestCallBack<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                if (data.isEmpty()) {
                    view?.displayEmptyPosts()
                } else {
                    view?.displayFullPosts(data)
                }
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }
        })
    }

    override fun onDestroy() {
        view = null
    }

}