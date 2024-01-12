package com.abcoding.connect.domain.models

import com.abcoding.connect.domain.models.util.ActivityAction

data class Activity(
    val username: String,
    val actionType: ActivityAction,
    val formattedTime: String
)
