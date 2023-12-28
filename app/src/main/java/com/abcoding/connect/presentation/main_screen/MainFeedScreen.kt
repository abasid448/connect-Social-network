package com.abcoding.connect.presentation.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.abcoding.connect.domain.models.Post
import com.abcoding.connect.presentation.components.Post
import com.abcoding.connect.presentation.components.StandardScaffold
import com.abcoding.connect.presentation.util.Screen

@Composable
fun MainFeedScreen(
    navController: NavController
) {
        StandardScaffold(
           navController =  navController,
            modifier = Modifier.fillMaxSize()
        ){
        Post(
            post =Post(
                username = "Philipp Lackner",
                imageUrl = "",
                profilePictureUrl = "",
                description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed\n" +
                        "diam nonumy eirmod tempor invidunt ut labore et dolore \n" +
                        "magna aliquyam erat, sed diam voluptua...",
                likeCount = 17,
                commentCount = 7
            ),
            onPostClick = {
                navController.navigate(Screen.PostDetailScreen.route)
            }
        )
    }
}