package com.example.devmuscles.auth_feature.domain

import com.example.devmuscles.auth_feature.data.util.AuthResult

interface AuthRepository {

    suspend fun signUp(fullName: String, email: String, password: String) : AuthResult
    suspend fun signIn(email: String, password: String): AuthResult
    suspend fun authenticate(): AuthResult
}