package com.example.devmuscles.auth_feature.data.repository.local.authDaoImpls

import android.content.Context
import com.example.devmuscles.auth_feature.data.repository.local.AuthDao
import com.example.devmuscles.auth_feature.domain.AuthInfo
import com.example.devmuscles.core.domain.AppSettingRepository
import com.example.devmuscles.core.util.AuthToken
import com.example.devmuscles.core.util.UserId
import com.example.devmuscles.core.util.Username
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthDaoImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val appSettingRepository: AppSettingRepository
) : AuthDao {

    override suspend fun getAuthToken(): AuthToken? {
        return CoroutineScope(Dispatchers.IO).async {
            appSettingRepository.dataStore.data.first()
                .authInfo?.authToken
        }.await()
    }

    override suspend fun setAuthToken(authToken: AuthToken?) {
        appSettingRepository.dataStore.updateData { appSettings ->
            appSettings.copy(
                authInfo = appSettings.authInfo
                    ?.copy(authToken = authToken)
            )
        }
    }

    override suspend fun getAuthUsername(): Username? {
        return CoroutineScope(Dispatchers.IO).async {
            appSettingRepository.dataStore.data.first()
                .authInfo?.username
        }.await()
    }

    override suspend fun setAuthUsername(username: Username?) {
        appSettingRepository.dataStore.updateData { appSettings ->
            appSettings.copy(
                authInfo = appSettings.authInfo
                    ?.copy(username = username)
            )
        }
    }

    override suspend fun getAuthUserId(): UserId? {
        return CoroutineScope(Dispatchers.IO).async {
            appSettingRepository.dataStore.data.first()
                .authInfo?.userId
        }.await()
    }

    override suspend fun setAuthUserId(userId: UserId?) {
        appSettingRepository.dataStore.updateData { appsettings ->
            appsettings.copy(
                authInfo = appsettings.authInfo
                    ?.copy(userId = userId)
            )
        }
    }

    override suspend fun clearAuthToken() {
        appSettingRepository.dataStore.updateData { appSettings ->
            appSettings.copy(
                authInfo = appSettings.authInfo
                    ?.copy(authToken = null)
            )
        }
    }

    override suspend fun getAuthInfo(): AuthInfo? {
        return CoroutineScope(Dispatchers.IO).async {
            appSettingRepository.dataStore.data.first()
                .authInfo
        }.await()
    }

    override suspend fun setAuthInfo(authInfo: AuthInfo?) {
        appSettingRepository.dataStore.updateData { appSettings ->
            appSettings.copy(
                authInfo = authInfo
            )
        }
    }

    override suspend fun clearAuthInfo() {
        appSettingRepository.dataStore.updateData { appSettings ->
            appSettings.copy(
                authInfo = null
            )
        }
    }
}