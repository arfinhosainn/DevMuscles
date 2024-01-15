package com.example.devmuscles.di

import androidx.datastore.core.DataStore
import com.example.devmuscles.auth_feature.domain.validation.ValidateEmail
import com.example.devmuscles.auth_feature.domain.validation.ValidatePassword
import com.example.devmuscles.auth_feature.domain.validation.ValidateUsername
import com.example.devmuscles.core.data.settings.AppSettings
import com.example.devmuscles.core.data.settings.AppSettingsRepositoryImpl
import com.example.devmuscles.core.domain.AppSettingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppSettingsRepository(
        dataStore: DataStore<AppSettings>
    ): AppSettingRepository = AppSettingsRepositoryImpl(dataStore)

    @Provides
    @Singleton
    fun provideValidateEmail(): ValidateEmail = ValidateEmail()

    @Provides
    @Singleton
    fun provideValidatePassword(): ValidatePassword = ValidatePassword()

    @Provides
    @Singleton
    fun provideValidateUsername(): ValidateUsername = ValidateUsername()


}

@Qualifier
@Named("AuthDao.PROD.usingBinds")
annotation class AuthDaoProdUsingBinds