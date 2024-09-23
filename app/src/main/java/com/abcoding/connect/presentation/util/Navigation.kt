package com.abcoding.connect.presentation.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abcoding.connect.domain.models.Post
import com.abcoding.connect.presentation.PersonListScreen
import com.abcoding.connect.presentation.activitysceen.ActivityScreen
import com.abcoding.connect.presentation.chat_screen.ChatScreen
import com.abcoding.connect.presentation.create_post.CreatePostScreen
import com.abcoding.connect.presentation.edit_profile.EditProfileScreen
import com.abcoding.connect.presentation.main_screen.MainFeedScreen
import com.abcoding.connect.presentation.login.LoginScreen
import com.abcoding.connect.presentation.post_details.PostDetailScreen
import com.abcoding.connect.presentation.profile_screen.ProfileScreen
import com.abcoding.connect.presentation.register.RegisterScreen
import com.abcoding.connect.presentation.search.SearchScreen
import com.abcoding.connect.presentation.splash.SplashScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Navigation(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route,
        modifier = Modifier.fillMaxSize()
    ){
         composable(Screen.SplashScreen.route){
             SplashScreen(navController = navController )
         }
        composable(Screen.LoginScreen.route){
                LoginScreen(navController = navController)
         }
        composable(Screen.RegisterScreen.route){
            RegisterScreen(navController = navController)
        }
        composable(Screen.MainFeedScreen.route){
            MainFeedScreen(navController = navController)
        }
        composable(Screen.ChatScreen.route){
            ChatScreen(navController = navController)
        }
        composable(Screen.ActivityScreen.route){
            ActivityScreen(navController = navController)
        }
        composable(Screen.ProfileScreen.route){
            ProfileScreen(navController = navController)
        }
        composable(Screen.EditProfileScreen.route){
            EditProfileScreen(navController = navController)
        }
        composable(Screen.CreatePostScreen.route){
           CreatePostScreen(navController = navController)
        }
        composable(Screen.SearchScreen.route){
            SearchScreen(navController = navController)
        }

        composable(Screen.PostDetailScreen.route){
            PostDetailScreen(navController = navController,
                post = Post(
                    username = "Abdul Basid",
                    imageUrl = "",
                    profilePictureUrl = "",
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed" +
                            " diam nonumy eirmod tempor invidunt ut labore et dolore \n  " +
                            "agna aliquyam erat, sed diam voluptuaLorem ipsum dolor sit amet, consetetur sadipscing elitr, sed \n " +
                            "diam nonumy eirmod tempor invidunt ut labore et dolore" +
                            "magna aliquyam erat, sed diam voluptua",
                    likeCount = 70,
                    commentCount = 17
                )
            )
        }
        composable(Screen.PersonalListScreen.route){
            PersonListScreen(navController = navController)
        }
    }
}