package com.example.instagramapp.common.model

import android.net.Uri
import android.os.Parcelable

data class Post(
    val uuid: String,
    val uri: Uri,
    val caption: String,
    val timestamp: Long
)
