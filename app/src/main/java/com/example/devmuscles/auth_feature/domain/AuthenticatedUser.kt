package com.example.devmuscles.auth_feature.domain

data class AuthenticatedUser(
    val fullName: String,
    val email: String,
    val token: String,
    val userId: String
)
