package com.abcoding.connect.domain.models.util

sealed class ActivityAction {
    object LikedPost: ActivityAction()
    object CommentedOnPost: ActivityAction()
    object FollowedYou: ActivityAction()
}
