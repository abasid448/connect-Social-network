package com.abcoding.connect.domain.models

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val contentDescription: String? = null,
    val alertCount: Int? = null,
)
