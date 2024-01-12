package com.abcoding.connect.presentation.profile_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.abcoding.connect.R
import com.abcoding.connect.domain.models.Activity
import com.abcoding.connect.domain.models.util.ActivityAction
import com.abcoding.connect.domain.models.util.DateFormatUtil
import com.abcoding.connect.presentation.activitysceen.component.ActivityItem
import com.abcoding.connect.presentation.components.StandardScaffold
import com.abcoding.connect.presentation.components.StandardToolbar
import com.abcoding.connect.presentation.ui.theme.SpaceMedium
import com.abcoding.connect.presentation.util.Screen
import kotlin.random.Random

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        StandardToolbar(
            navController = navController,
            title = {
                androidx.compose.material.Text(
                    text = stringResource(id = R.string.your_profile),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = false,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(SpaceMedium)

        ) {

            items(20) {

            }

        }

    }

}
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        