package com.example.instagramapp.profile.presentation

import com.example.instagramapp.common.base.RequestCallBack
import com.example.instagramapp.common.model.Database
import com.example.instagramapp.common.model.Post
import com.example.instagramapp.common.model.UserAuth
import com.example.instagramapp.profile.Profile
import com.example.instagramapp.profile.data.ProfileRepository

class ProfilePresenter(
    private var view: Profile.View?,
    private val repository: ProfileRepository
) : Profile.Presenter {

    override var state: UserAuth? = null


    override fun fetchUserProfile() {
        view?.showProgress(true)
        val userUUID = Database.sessionAuth?.uuid ?: throw RuntimeException("User not found")
        repository.fetchUserProfile(userUUID, object : RequestCallBack<UserAuth> {
            override fun onSuccess(data: UserAuth) {
                state = data
                view?.displayUserProfile(data)
            }

            override fun onFailure(message: String) {
                view?.displaRequestFailure(message)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }
        })
    }

    override fun fetchUserPosts() {
        val userUUID = Database.sessionAuth?.uuid ?: throw RuntimeException("User not found")
        repository.fetchUserPosts(userUUID, object : RequestCallBack<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                if (data.isEmpty()) {
                    view?.displayEmptyPosts()
                } else {
                    view?.displayFullPosts(data)
                }
            }

            override fun onFailure(message: String) {
                view?.displaRequestFailure(message)
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
