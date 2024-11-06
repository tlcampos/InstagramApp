package com.example.instagramapp.add.data

import android.net.Uri
import android.os.Handler
import android.os.Looper
import com.example.instagramapp.common.base.RequestCallBack
import com.example.instagramapp.common.model.Database
import com.example.instagramapp.common.model.Post
import java.util.UUID

class AddFakeRemoteDataSource : AddDataSource {

    override fun createPost(
        userUUID: String,
        uri: Uri,
        caption: String,
        callback: RequestCallBack<Boolean>
    ) {
        Handler(Looper.getMainLooper()).postDelayed({

            var posts = Database.posts[userUUID]

            if (posts == null) {
                posts = mutableSetOf()
                Database.posts[userUUID] = posts
            }

            val post = Post(
                UUID.randomUUID().toString(),
                uri,
                caption,
                System.currentTimeMillis(),
                Database.sessionAuth!!
            )

            posts.add(post)

            var followers = Database.followers[userUUID]

            if (followers == null) {
                followers = mutableSetOf()
                Database.followers[userUUID] = followers
            } else {
                for (follower in followers) {
                    var feed = Database.feeds[follower]?.add(post)
                }

                Database.feeds[userUUID]?.add(post)
            }

            callback.onSuccess(true)

        }, 1000)
    }
}