package com.example.devmuscles.core.data.settings

import androidx.datastore.core.DataStore
import com.example.devmuscles.auth_feature.domain.AuthInfo
import com.example.devmuscles.core.domain.AppSettingRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AppSettingsRepositoryImpl @Inject constructor(
    override val dataStore: DataStore<AppSettings>
) : AppSettingRepository {
    override suspend fun saveAuthInfo(authInfo: AuthInfo) {
        dataStore.updateData { appSettings ->
            appSettings.copy(authInfo = authInfo)
        }
    }

    override suspend fun getAppSettings() = dataStore.data.first()

    override suspend fun saveIsSettingsInitialized(firstTime: Boolean) {

        dataStore.updateData { appSettings ->
            appSettings.copy(isSettingsInitialized = firstTime)
        }

    }
}