package com.profilecreator.model

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val usernameError: Int? = null,
    val otpError: Int? = null,
    val isDataValid: Boolean = false,
    val isOTPLengthValid: Boolean = false
)
