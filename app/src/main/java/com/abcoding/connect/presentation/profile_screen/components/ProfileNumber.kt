package com.abcoding.connect.presentation.profile_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.abcoding.connect.presentation.ui.theme.SpaceSmall

@Composable
fun ProfileNumber(
    number: Int,
    text:String
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = number.toString(),
            style = MaterialTheme.typography.h2.copy(
                fontSize = 24.sp
            ),
            textAlign = TextAlign.Center
            )
        Spacer(modifier = Modifier.height(SpaceSmall))
        Text(text = text,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center
            )

    }
}