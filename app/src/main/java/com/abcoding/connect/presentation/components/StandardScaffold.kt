package com.abcoding.connect.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import android.os.Build
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abcoding.connect.domain.models.BottomNavItem
import com.abcoding.connect.presentation.util.Screen
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild

private val CoralAccent   = Color(0xFFFF5E62)
private val PurpleAccent  = Color(0xFFAB5CF7)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StandardScaffold(
    navController: NavController,
    modifier: Modifier = Modifier,
    showBottomBar: Boolean = true,
    bottomNavItems: List<BottomNavItem> = listOf(
        BottomNavItem(
            route = Screen.MainFeedScreen.route,
            icon = Icons.Outlined.Home,
            contentDescription = "Home"
        ),
        BottomNavItem(
            route = Screen.SearchScreen.route,
            icon = Icons.Outlined.Search,
            contentDescription = "Search"
        ),
        BottomNavItem(
            route = Screen.CreatePostScreen.route,
            icon = Icons.Outlined.AddBox,
            contentDescription = "Make Post"
        ),
        BottomNavItem(
            route = Screen.ActivityScreen.route,
            icon = Icons.Outlined.Notifications,
            contentDescription = "Activity"
        ),
        BottomNavItem(
            route = Screen.ProfileScreen.route,
            icon = Icons.Outlined.Person,
            contentDescription = "Profile"
        ),
    ),
    content: @Composable () -> Unit,
) {
    val hazeState = remember { HazeState() }
    val isDark = isSystemInDarkTheme()
    val tintColor = if (isDark) Color.Black.copy(alpha = 0.4f) else Color.White.copy(alpha = 0.4f)
    val bgColor = if (isDark) Color.Black else Color.White

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                GlobalFloatingHUD(
                    navController = navController,
                    items = bottomNavItems,
                    hazeState = hazeState
                )
            }
        },
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.haze(
                state = hazeState,
                backgroundColor = bgColor,
                tint = tintColor,
                blurRadius = 40.dp,
                noiseFactor = 0.05f
            )
        ) {
            content()
        }
    }
}

@Composable
private fun GlobalFloatingHUD(navController: NavController, items: List<BottomNavItem>, hazeState: HazeState, modifier: Modifier = Modifier) {
    val isDark = isSystemInDarkTheme()
    
    // Dynamic theme colors for the HUD
    val hudBorder = if (isDark) Color(0x26FFFFFF) else Color(0x26000000)
    val glassText = if (isDark) Color(0xFFEEEEF4) else Color(0xFF111111)
    
    val tintColor = if (isDark) Color.Black.copy(alpha = 0.4f) else Color.White.copy(alpha = 0.4f)

    Box(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 20.dp)
            .fillMaxWidth()
            .height(66.dp)
            .clip(RoundedCornerShape(33.dp))
            .hazeChild(
                state = hazeState,
                shape = RoundedCornerShape(33.dp)
            )
            .border(1.dp, hudBorder, RoundedCornerShape(33.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEach { item ->
                val isSelected = item.route == navController.currentDestination?.route

                if (item.route == Screen.CreatePostScreen.route) {
                    // Center create button — gradient accent pill
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(
                                Brush.linearGradient(listOf(CoralAccent, PurpleAccent)),
                                RoundedCornerShape(16.dp)
                            )
                            .clickable {
                                if (!isSelected) {
                                    navController.navigate(item.route)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Add, null, tint = Color.White, modifier = Modifier.size(26.dp))
                    }
                } else {
                    HUDNavIcon(
                        icon = item.icon ?: Icons.Outlined.Home,
                        tint = if (isSelected) CoralAccent else glassText.copy(alpha = if (isDark) 0.45f else 0.65f),
                        active = isSelected,
                        onClick = {
                            if (!isSelected) {
                                navController.navigate(item.route)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun HUDNavIcon(
    icon: ImageVector,
    tint: Color,
    active: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable { onClick() }) {
        Icon(icon, null, tint = tint, modifier = Modifier.size(24.dp))
        Spacer(Modifier.height(3.dp))
        Box(
            Modifier
                .size(4.dp)
                .background(
                    if (active) CoralAccent else Color.Transparent, CircleShape
                )
        )
    }
}
