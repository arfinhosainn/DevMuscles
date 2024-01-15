package com.example.devmuscles.auth_feature.data.repository.local.entities

import com.example.devmuscles.core.util.AuthToken
import com.example.devmuscles.core.util.Email
import com.example.devmuscles.core.util.UserId
import com.example.devmuscles.core.util.Username

data class AuthInfoEntity(
    val authToken: AuthToken? = null,
    val userId: UserId? = null,
    val username: Username? = null,
    val email: Email? = null
)
