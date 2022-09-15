package com.profilecreator.dataSource

/**
 * Authentication result : success (user details) or error message.
 */
data class Response(
    val success: SuccessMessageView? = null,
    val error: String? = null
)
