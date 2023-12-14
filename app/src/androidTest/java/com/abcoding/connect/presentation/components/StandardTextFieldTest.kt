package com.abcoding.connect.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.abcoding.connect.presentation.MainActivity
import org.junit.Rule
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class StandardTextFieldTest{

    @get:Rule
    val composeTestRules = createAndroidComposeRule<MainActivity>()

     fun enterTooLongString_maxLengthNotExeeded(){
         composeTestRules.setContent {
             MaterialTheme {

             }
         }
     }

}