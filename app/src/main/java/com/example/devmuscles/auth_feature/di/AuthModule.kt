package com.example.devmuscles.auth_feature.di

import com.example.devmuscles.auth_feature.data.repository.remote.AuthApi
import com.example.devmuscles.auth_feature.domain.datastore.UserPreferences
import com.squareup.moshi.Moshi
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

object AuthModule {

    @Provides
    @Singleton
    fun provideDevMusclesApi(
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

}