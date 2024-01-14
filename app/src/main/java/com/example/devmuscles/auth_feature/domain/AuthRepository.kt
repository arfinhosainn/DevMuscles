package com.example.devmuscles.auth_feature.domain

import com.example.devmuscles.auth_feature.domain.validation.ValidateEmail
import com.example.devmuscles.auth_feature.domain.validation.ValidatePassword
import com.example.devmuscles.auth_feature.domain.validation.ValidateUsername
import com.example.devmuscles.core.util.Email
import com.example.devmuscles.core.util.Password
import com.example.devmuscles.core.util.Username

interface AuthRepository {

    val validateEmail: ValidateEmail
    val validatePassword: ValidatePassword
    val validateUsername: ValidateUsername

    fun validateEmail(email: Email): Boolean {
        return validateEmail.validate(email)
    }

    fun validatePassword(password: Password): Boolean {
        return validatePassword.validate(password)
    }

    fun validateUsername(username: Username): Boolean {
        return validateUsername.validate(username)
    }

    suspend fun login(
        email: Email,
        password: Password
    ): AuthInfo

    suspend fun register(
        username: Username,
        email: Email,
        password: Password
    )

    suspend fun logout()

    suspend fun setAuthInfo(authInfo: AuthInfo?)

    suspend fun getAuthInfo(): AuthInfo?

    suspend fun clearAuthInfo()

    fun getAuthUserId(): String?

    // uses the logged-in user's authInfo to check if the user is authenticated
    suspend fun authenticate(): Boolean

    // Checks any authInfo to see if it is valid
    suspend fun authenticateAuthInfo(authInfo: AuthInfo?): Boolean


}