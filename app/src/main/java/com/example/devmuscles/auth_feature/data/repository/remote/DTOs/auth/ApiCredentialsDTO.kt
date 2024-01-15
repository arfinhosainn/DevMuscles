package com.example.devmuscles.auth_feature.data.repository.remote.DTOs.auth

import kotlinx.serialization.SerialName

data class ApiCredentialsDTO(
    @SerialName("fullName")
    val username: String? = null,
    val email: String,
    val password: String
)
