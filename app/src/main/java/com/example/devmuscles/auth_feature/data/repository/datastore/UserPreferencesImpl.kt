package com.example.devmuscles.auth_feature.data.repository.datastore

import android.content.SharedPreferences
import com.example.devmuscles.auth_feature.domain.AuthenticatedUser
import com.example.devmuscles.auth_feature.domain.datastore.UserPreferences

class UserPreferencesImpl(
    private val prefs: SharedPreferences
) : UserPreferences {
    override fun saveAuthenticatedUser(user: AuthenticatedUser) {
        prefs.edit()
            .putString("jwt", user.token)
            .putString("fullName", user.fullName)
            .putString("email", user.email)
            .putString("userId", user.userId)
            .apply()
    }

    override fun getAuthenticatedUser(): AuthenticatedUser? {
        val fullName = prefs.getString("fullName", null) ?: return null
        val email = prefs.getString("email", null) ?: return null
        val token = prefs.getString("jwt", null) ?: return null
        val userId = prefs.getString("userId", null) ?: return null

        return AuthenticatedUser(
            fullName = fullName,
            email = email,
            token = token,
            userId = userId
        )
    }

    override fun clearPreferences() {
        prefs.edit().clear().apply()
    }
}