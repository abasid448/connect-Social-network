package com.abcoding.connect.presentation.activitysceen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.abcoding.connect.R
import com.abcoding.connect.domain.models.Activity
import com.abcoding.connect.domain.models.util.ActivityAction
import com.abcoding.connect.domain.models.util.DateFormatUtil
import com.abcoding.connect.presentation.activitysceen.component.ActivityItem
import com.abcoding.connect.presentation.components.StandardToolbar
import com.abcoding.connect.presentation.ui.theme.SpaceMedium
import kotlin.random.Random

@Composable
fun ActivityScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        StandardToolbar(
            navController = navController,
            title = {
                androidx.compose.material.Text(
                    text = stringResource(id = R.string.your_feed),
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
                ActivityItem(
                    activity = Activity(
                        username = "Martha",
                        actionType = if (Random.nextInt(2) == 0) {
                            ActivityAction.LikedPost
                        } else ActivityAction.CommentedOnPost,
                        formattedTime = DateFormatUtil.timestampToFormattedString(
                            timestamp = System.currentTimeMillis(),
                            pattern = "MMM dd, HH:mm"
                        )
                    ),
                )
                if (it < 19) {
                    Spacer(modifier = Modifier.height(SpaceMedium))

                }
            }

        }

    }

}

@Preview
@Composable
fun Preview() {
    val navController = rememberNavController()
    ActivityScreen(navController = navController)
}
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        