package com.example.devmuscles.auth_feature.data.repository

import com.example.devmuscles.auth_feature.data.repository.local.AuthDao
import com.example.devmuscles.auth_feature.data.repository.remote.AuthApi
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
    private val authDao: AuthDao,
    private val authApi: AuthApi,
    override val validateEmail: ValidateEmail,
    override val validatePassword: ValidatePassword,
    override val validateUsername: ValidateUsername,
) : AuthRepository {

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