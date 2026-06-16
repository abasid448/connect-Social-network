package com.abcoding.connect.presentation.register

data class RegisterState(
    val emailText: String = "",
    val usernameText: String = "",
    val passwordText: String = "",
    val emailError: EmailError? = null,
    val usernameError: UsernmeError? = null,
    val passwordError: PasswordError? = null,
    val isPasswordVisible: Boolean = false
) {
    sealed class EmailError {
        object FieldEmpty : EmailError()
        object InvalidEmail : EmailError()
    }

    sealed class UsernmeError {
        object FieldEmpty : UsernmeError()
        object InputTooShort : UsernmeError()
    }

    sealed class PasswordError {
        object FieldEmpty : PasswordError()
        object InputTooShort : PasswordError()
        object InvalidPassword : PasswordError()
    }
}
