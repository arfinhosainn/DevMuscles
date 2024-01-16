package com.example.devmuscles.auth_feature.domain.datastore

import com.example.devmuscles.auth_feature.domain.AuthenticatedUser

interface UserPreferences {
    fun saveAuthenticatedUser(user: AuthenticatedUser)

    fun getAuthenticatedUser(): AuthenticatedUser?

    fun clearPreferences()
}