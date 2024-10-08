package com.abcoding.connect.presentation.register

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.abcoding.connect.presentation.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel(

) {
    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EnteredUsername -> {
                _state.value = _state.value.copy(
                    usernameText = event.value
                )
            }

            is RegisterEvent.EnteredEmail -> {
                _state.value = _state.value.copy(
                    emailText = event.value
                )
            }

            is RegisterEvent.EnteredPassword -> {
                _state.value = _state.value.copy(
                    passwordText = event.value
                )
            }

            is RegisterEvent.Register -> {
                validateUserName(state.value.usernameText)
                validateEmail(state.value.emailText)
                validatePassword(state.value.passwordText)
            }

            is RegisterEvent.TogglePasswordVisibility -> {
                _state.value = _state.value.copy(
                    isPasswordVisible = !state.value.isPasswordVisible
                )
            }
        }
    }

    private fun validateUserName(username: String) {
        val trimmedUsername = username.trim()
        if (trimmedUsername.isBlank()) {
            _state.value = _state.value.copy(
                usernameError = RegisterState.UsernmeError.FieldEmpty
            )
            return
        }
        if (trimmedUsername.length < Constants.MIN_PASSWORD_LENGTH) {
            _state.value = _state.value.copy(
                usernameError = RegisterState.UsernmeError.InputTooShort
            )
            return
        }
        _state.value = _state.value.copy(usernameError = null)
    }

    private fun validateEmail(email: String) {
        val trimmedEmail = email.trim()
        if (trimmedEmail.isBlank()) {
            _state.value = _state.value.copy(
                emailError = RegisterState.EmailError.FieldEmpty
            )
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _state.value = _state.value.copy(
                emailError = RegisterState.EmailError.InvalidEmail
            )
            return
        }
        _state.value = _state.value.copy(emailError = null)

    }

    private fun validatePassword(password: String) {

        if (password.isBlank()) {
            _state.value = _state.value.copy(
                passwordError = RegisterState.PasswordError.FieldEmpty
            )
            return
        }
        if (password.length < Constants.MIN_PASSWORD_LENGTH) {
            _state.value = _state.value.copy(
                passwordError = RegisterState.PasswordError.InputTooShort
            )
            return
        }

        val isPasswordIsCAPS = password.any { it.isUpperCase() }
        val numberInaPassword = password.any { it.isDigit() }
        if (!isPasswordIsCAPS || !numberInaPassword) {
            _state.value = _state.value.copy(
                passwordError = RegisterState.PasswordError.InvalidPassword
            )
            return
        }
        _state.value = _state.value.copy(passwordError = null)

    }

}