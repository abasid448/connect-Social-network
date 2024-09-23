package com.abcoding.connect.presentation.chat_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.abcoding.connect.R
import com.abcoding.connect.presentation.components.StandardScaffold
import com.abcoding.connect.presentation.components.StandardToolbar
import com.abcoding.connect.presentation.util.Screen

@Composable
fun ChatScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        StandardToolbar(
            navController = navController,
            title = {
                androidx.compose.material.Text(
                    text = stringResource(id = R.string.chat),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true,
        )
    }
}
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        