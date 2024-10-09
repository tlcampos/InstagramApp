package com.example.instagramapp.home.data

import com.example.instagramapp.common.base.Cache
import com.example.instagramapp.common.model.Post

object FeedMemoryCache : Cache<List<Post>> {

    private var posts: List<Post>? = null

    override fun isCached(): Boolean {
        return posts != null
    }

    override fun get(key: String): List<Post>? {
        return posts
    }

    override fun put(data: List<Post>) {
        posts = data
    }
}