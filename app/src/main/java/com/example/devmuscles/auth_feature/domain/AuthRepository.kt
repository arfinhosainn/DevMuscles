package com.example.devmuscles.auth_feature.domain

import com.example.devmuscles.auth_feature.data.util.AuthResult
import com.example.devmuscles.auth_feature.domain.validation.ValidateEmail
import com.example.devmuscles.auth_feature.domain.validation.ValidatePassword
import com.example.devmuscles.auth_feature.domain.validation.ValidateUsername
import com.example.devmuscles.core.util.Email
import com.example.devmuscles.core.util.Password
import com.example.devmuscles.core.util.Username

interface AuthRepository {

    suspend fun signUp(fullName: String, email: String, password: String) : AuthResult
    suspend fun signIn(email: String, password: String): AuthResult
    suspend fun authenticate(): AuthResult
}