package com.example.devmuscles.auth_feature.domain

import android.os.Parcelable
import com.example.devmuscles.core.util.AuthToken
import com.example.devmuscles.core.util.Email
import com.example.devmuscles.core.util.UserId
import com.example.devmuscles.core.util.Username
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class AuthInfo(
    val authToken: AuthToken? = null,
    val userId: UserId? = null,
    val username: Username? = null,
    val email: Email? = null,
): Parcelable