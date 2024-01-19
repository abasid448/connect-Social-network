package com.abcoding.connect.presentation.components

import androidx.annotation.ColorRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.abcoding.connect.R
import com.abcoding.connect.presentation.util.TestTags

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    maxLength: Int = 25,
    onValueChange: (String) -> Unit,
    error: String = " ",
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    leadingIcon: ImageVector? = null,
    showPasswordToggle: Boolean = false,
    onPasswordToggleClick: (Boolean) -> Unit = {}
) {
    val isPasswordToggleDisplayed by remember {
        mutableStateOf(keyboardType == KeyboardType.Password)
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(value = text,
            shape = RoundedCornerShape(5.dp),
            onValueChange = {
                if (it.length <= maxLength) {
                    onValueChange(it)
                }
            },
            maxLines = maxLines,
            label = {
                Text(
                    text = hint, style = MaterialTheme.typography.labelSmall
                )

            },
            isError = error != "",
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            singleLine = true,
            visualTransformation = if (!showPasswordToggle && isPasswordToggleDisplayed) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },

            trailingIcon = {

//                //color of trailing icon
                val colorResource = MaterialTheme.colorScheme.inversePrimary

                val iconColor = if (showPasswordToggle) {
                    colorResource
                } else {
                    Color.Gray
                }


                if (isPasswordToggleDisplayed) {

                    IconButton(onClick = {
                        onPasswordToggleClick(!showPasswordToggle)
                    }) {
                        Icon(
                            imageVector = if (showPasswordToggle) {
                                Icons.Filled.Visibility
                            } else {
                                Icons.Filled.VisibilityOff
                            },
                            contentDescription = if (showPasswordToggle) {
                                stringResource(id = R.string.password_visible_content_description)
                            } else {
                                stringResource(id = R.string.password_hidden_content_description)
                            },
                            tint = iconColor
                        )
                    }
                }

            },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                focusedIndicatorColor = MaterialTheme.colorScheme.inversePrimary,
                focusedLabelColor = MaterialTheme.colorScheme.inversePrimary,
                cursorColor = MaterialTheme.colorScheme.inversePrimary,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    testTag = TestTags.STANDARD_TEXT_FIELD
                })
        if (error.isNotEmpty()){
            Text(
                text =  error,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}