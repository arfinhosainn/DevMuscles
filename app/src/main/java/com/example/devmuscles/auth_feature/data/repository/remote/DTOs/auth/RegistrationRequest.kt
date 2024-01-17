package com.example.devmuscles.auth_feature.data.repository.remote.DTOs.auth

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistrationRequest(
    val fullName: String?,
    val email: String,
    val password: String
)