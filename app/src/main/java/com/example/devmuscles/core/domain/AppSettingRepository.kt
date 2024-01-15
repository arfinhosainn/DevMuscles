package com.example.devmuscles.core.domain

import androidx.datastore.core.DataStore
import com.example.devmuscles.auth_feature.domain.AuthInfo
import com.example.devmuscles.core.data.settings.AppSettings

interface AppSettingRepository {

    val dataStore: DataStore<AppSettings>

    suspend fun saveAuthInfo(authInfo: AuthInfo)

    suspend fun getAppSettings(): AppSettings

    suspend fun saveIsSettingsInitialized(firstTime: Boolean)




}