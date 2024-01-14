package com.example.devmuscles.auth_feature.data.repository

import com.example.devmuscles.auth_feature.domain.AuthInfo
import com.example.devmuscles.auth_feature.domain.AuthRepository
import com.example.devmuscles.auth_feature.domain.validation.ValidateEmail
import com.example.devmuscles.auth_feature.domain.validation.ValidatePassword
import com.example.devmuscles.auth_feature.domain.validation.ValidateUsername
import com.example.devmuscles.core.util.Email
import com.example.devmuscles.core.util.Password
import com.example.devmuscles.core.util.Username
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
): AuthRepository{
    override val validateEmail: ValidateEmail
        get() = TODO("Not yet implemented")
    override val validatePassword: ValidatePassword
        get() = TODO("Not yet implemented")
    override val validateUsername: ValidateUsername
        get() = TODO("Not yet implemented")

    override suspend fun login(email: Email, password: Password): AuthInfo {
        TODO("Not yet implemented")
    }

    override suspend fun register(username: Username, email: Email, password: Password) {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun setAuthInfo(authInfo: AuthInfo?) {
        TODO("Not yet implemented")
    }

    override suspend fun getAuthInfo(): AuthInfo? {
        TODO("Not yet implemented")
    }

    override suspend fun clearAuthInfo() {
        TODO("Not yet implemented")
    }

    override fun getAuthUserId(): String? {
        TODO("Not yet implemented")
    }

    override suspend fun authenticate(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun authenticateAuthInfo(authInfo: AuthInfo?): Boolean {
        TODO("Not yet implemented")
    }
}