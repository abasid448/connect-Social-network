package com.abcoding.connect.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abcoding.connect.presentation.login.LoginScreen
import com.abcoding.connect.presentation.register.RegisterScreen
import com.abcoding.connect.presentation.splash.SplashScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
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
    }
}