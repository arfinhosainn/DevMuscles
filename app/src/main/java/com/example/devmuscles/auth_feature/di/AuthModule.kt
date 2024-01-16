package com.example.devmuscles.auth_feature.di

import android.content.SharedPreferences
import com.example.devmuscles.auth_feature.data.repository.AuthRepositoryImpl
import com.example.devmuscles.auth_feature.data.repository.datastore.UserPreferencesImpl
import com.example.devmuscles.auth_feature.data.repository.remote.AuthApi
import com.example.devmuscles.auth_feature.data.validation.EmailPatternValidatorImpl
import com.example.devmuscles.auth_feature.domain.AuthRepository
import com.example.devmuscles.auth_feature.domain.datastore.UserPreferences
import com.example.devmuscles.auth_feature.domain.validation.EmailPatternValidator
import com.example.devmuscles.auth_feature.domain.validation.UserDataValidator
import com.example.devmuscles.auth_feature.domain.validation.UserDataValidatorImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): AuthApi {
        return Retrofit.Builder()
            .baseUrl(AuthApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideUserPreferences(preferences: SharedPreferences): UserPreferences {
        return UserPreferencesImpl(preferences)
    }

    @Provides
    @Singleton
    fun provideEmailPatternValidator(): EmailPatternValidator {
        return EmailPatternValidatorImpl()
    }

    @Provides
    @Singleton
    fun provideUserDataValidator(emailPatternValidator: EmailPatternValidator): UserDataValidator {
        return UserDataValidatorImpl(emailPatternValidator)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        api: AuthApi,
        prefs: UserPreferences
    ): AuthRepository {
        return AuthRepositoryImpl(
            api = api,
            userPreferences = prefs
        )
    }

}