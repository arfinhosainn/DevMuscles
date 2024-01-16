package com.example.devmuscles.core.util

import com.example.devmuscles.auth_feature.domain.datastore.UserPreferences
import okhttp3.Interceptor
import okhttp3.Response

class ApiTokenInterceptor (
    private val userPrefs: UserPreferences
): Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val authenticatedUser  = userPrefs.getAuthenticatedUser()
        val token = authenticatedUser?.token ?: ""

        return chain.proceed(
            chain
                .request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        )
    }
}