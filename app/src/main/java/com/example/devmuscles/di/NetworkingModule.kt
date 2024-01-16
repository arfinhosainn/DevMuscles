package com.example.devmuscles.di

import Constants
import com.example.devmuscles.auth_feature.data.repository.local.AuthDao
import com.example.devmuscles.auth_feature.data.repository.remote.AuthApi
import com.example.devmuscles.auth_feature.data.repository.remote.AuthApi.Companion.createBearerTokenString
import com.example.devmuscles.auth_feature.domain.datastore.UserPreferences
import com.example.devmuscles.core.data.remote.DevMusclesApi
import com.example.devmuscles.core.util.ApiKeyInterceptor
import com.example.devmuscles.core.util.ApiTokenInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(userPrefs: UserPreferences): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(Constants.API_KEY))
            .addInterceptor(ApiTokenInterceptor(userPrefs))
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

}