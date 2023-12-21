package com.abcoding.connect.presentation.main_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.abcoding.connect.presentation.components.Post
import com.abcoding.connect.presentation.util.Screen

@Composable
fun MainFeedScreen(
    navController: NavController
) {
    Post(
        post = com.abcoding.connect.domain.models.Post(
            userName = "abdul_bazzi",
            imageUrl = "",
            profilePictureUrl = "",
            description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed\n" +
                    "diam nonumy eirmod tempor invidunt ut labore et dolore \n" +
                    "magna aliquyam erat, sed diam voluptua...",
            likeCount = 17 ,
            commentCount = 7
        ),
        onPostClick = {
            navController.navigate(Screen.PostDetailScreen.route)
        }
    )
}