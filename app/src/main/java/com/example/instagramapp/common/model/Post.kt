package com.example.instagramapp.common.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val uuid: String,
    val uri: Uri,
    val caption: String,
    val timestamp: Long
) : Parcelable
