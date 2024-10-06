package com.example.instagramapp.profile.presentation

import com.example.instagramapp.common.base.RequestCallBack
import com.example.instagramapp.common.model.Database
import com.example.instagramapp.common.model.Database.posts
import com.example.instagramapp.common.model.Post
import com.example.instagramapp.common.model.UserAuth
import com.example.instagramapp.profile.Profile
import com.example.instagramapp.profile.data.ProfileRepository

class ProfilePresenter(
    private var view: Profile.View?,
    private val repository: ProfileRepository
) : Profile.Presenter {

    var posts: List<Post>? = null
    var user: UserAuth? = null

    override fun subscribe(state: Profile.State?) {
        posts = state?.onFetchUserPosts()

        if (posts != null) {
            if (posts!!.isEmpty()) {
                view?.displayEmptyPosts()
            } else {
                view?.displayFullPosts(posts!!)
            }
        } else {
            val userUUID = Database.sessionAuth?.uuid ?: throw RuntimeException("User not found")
            repository.fetchUserPosts(userUUID, object : RequestCallBack<List<Post>> {
                override fun onSuccess(data: List<Post>) {
                    posts = data
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
        user = state?.onFetchUserProfile()
        if (user != null) {
            view?.displayUserProfile(user!!)
        } else {
            view?.showProgress(true)
            val userUUID = Database.sessionAuth?.uuid ?: throw RuntimeException("User not found")
            repository.fetchUserProfile(userUUID, object : RequestCallBack<UserAuth> {
                override fun onSuccess(data: UserAuth) {
                    user = data
                    view?.displayUserProfile(data)
                }

                override fun onFailure(message: String) {
                    view?.displaRequestFailure(message)
                }

                override fun onComplete() {

                }
            })
        }
    }

    override fun getState(): Profile.State {
        return ProfileState(posts, user)
    }

//override fun fetchUserProfile() {
//    view?.showProgress(true)
//    val userUUID = Database.sessionAuth?.uuid ?: throw RuntimeException("User not found")
//    repository.fetchUserProfile(userUUID, object : RequestCallBack<UserAuth> {
//        override fun onSuccess(data: UserAuth) {
//            view?.displayUserProfile(data)
//        }
//
//        override fun onFailure(message: String) {
//            view?.displaRequestFailure(message)
//        }
//
//        override fun onComplete() {
//            //  TODO("Not yet implemented")
//        }
//    })
//}

//override fun fetchUserPosts() {
//    val userUUID = Database.sessionAuth?.uuid ?: throw RuntimeException("User not found")
//    repository.fetchUserPosts(userUUID, object : RequestCallBack<List<Post>> {
//        override fun onSuccess(data: List<Post>) {
//            if (data.isEmpty()) {
//                view?.displayEmptyPosts()
//            } else {
//                view?.displayFullPosts(data)
//
//            }
//        }
//
//        override fun onFailure(message: String) {
//            view?.displaRequestFailure(message)
//        }
//
//        override fun onComplete() {
//            view?.showProgress(false)
//        }
//    })
//}

override fun onDestroy() {
    view = null
}
}