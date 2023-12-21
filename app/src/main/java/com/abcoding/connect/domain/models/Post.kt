package com.abcoding.connect.domain.models

data class Post(
    val userName: String,
    val imageUrl: String,
    val profilePictureUrl: String,
    val description: String,
    val likeCount:Int,
    val commentCount: Int

)
