package com.example.devmuscles.auth_feature.data.repository.remote.DTOs.auth

data class LoginResponse(
    val token: String,
    val userId: String,
    val fullName: String
)
