package com.example.instagramapp.add.data

import android.net.Uri
import com.example.instagramapp.common.base.RequestCallBack
import com.example.instagramapp.common.model.UserAuth

interface AddDataSource {

    fun createPost(userUUID: String, uri: Uri, caption: String, callback: RequestCallBack<Boolean>) {throw UnsupportedOperationException()}

    fun fetchSession(): UserAuth {throw UnsupportedOperationException() }



}