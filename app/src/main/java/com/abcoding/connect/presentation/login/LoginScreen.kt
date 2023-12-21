package com.abcoding.connect.presentation.login

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.abcoding.connect.R
import com.abcoding.connect.presentation.components.StandardTextField
import com.abcoding.connect.presentation.ui.theme.SpaceLarge
import com.abcoding.connect.presentation.ui.theme.SpaceMedium
import com.abcoding.connect.presentation.ui.theme.SpaceSmall
import com.abcoding.connect.presentation.util.Screen

@Composable
fun LoginScreen(
    navController: NavController, viewModel: LoginViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = SpaceLarge, end = SpaceLarge, top = SpaceLarge, bottom = 40.dp
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
        ) {
            Text(
                text = stringResource(id = R.string.login),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(SpaceLarge))

            StandardTextField(
                text = viewModel.usernameText.value,
                onValueChange = {
                    viewModel.setUsernameText(it)
                },
                hint = stringResource(id = R.string.login_hint),
                error = viewModel.usernameError.value
            )

            Spacer(modifier = Modifier.height(SpaceLarge))

            StandardTextField(text = viewModel.passwordText.value,
                onValueChange = {
                    viewModel.setPasswordText(it)
                },
                hint = stringResource(id = R.string.password_hint),
                keyboardType = KeyboardType.Password,
                error = viewModel.passwordError.value,
                showPasswordToggle = viewModel.showPassword.value,
                onPasswordToggleClick = {
                    viewModel.setShowPassword(it)
                })
            Spacer(modifier = Modifier.height(SpaceLarge))
            Button(
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.inversePrimary
                ),
                elevation = ButtonDefaults.buttonElevation(2.dp),
                onClick = {
                    navController.navigate(Screen.MainFeedScreen.route)
                }, modifier = Modifier.align(Alignment.End),

            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }

        Text(
            text = buildAnnotatedString {
                append(stringResource(R.string.dont_have_an_account_yet))
                append(" ")
                val signUpText = stringResource(id = R.string.sign_up)
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                ) {
                    append(signUpText)
                }
            },
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clickable {
                    navController.navigate(Screen.RegisterScreen.route)
                },
        )

    }

}
