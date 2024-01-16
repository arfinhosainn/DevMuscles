package com.example.devmuscles.core.data.settings

import com.example.devmuscles.auth_feature.domain.AuthInfo

data class AppSettings(
    val authInfo: AuthInfo? = null,
    val isSettingsInitialized: Boolean = false //allows us to check if the settings is loaded
)
