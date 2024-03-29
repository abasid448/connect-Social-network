package com.abcoding.connect.presentation.edit_profile.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.abcoding.connect.presentation.ui.theme.HintGray
import com.abcoding.connect.presentation.ui.theme.SpaceSmall

@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    selectedColor: Color = MaterialTheme.colorScheme.inversePrimary,
    unselectedColor: Color = HintGray,
    onChipClick: () -> Unit
) {
    Text(
        text = text,
        color = if(selected) selectedColor else unselectedColor,
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .border(
                width = 1.dp,
                color = if (selected) selectedColor else unselectedColor,
                shape = RoundedCornerShape(50.dp)
            )
            .clickable {
                onChipClick()
            }
            .padding(SpaceSmall)
        ,
    )
}