package com.example.devmuscles.di

import Constants
import android.content.Context
import android.content.SharedPreferences
import com.example.devmuscles.auth_feature.domain.datastore.UserPreferences
import com.example.devmuscles.core.util.ApiKeyInterceptor
import com.example.devmuscles.core.util.ApiTokenInterceptor
import com.example.devmuscles.core.util.InternetConnectivityObserver.InternetConnectivityObserver
import com.example.devmuscles.core.util.InternetConnectivityObserver.InternetConnectivityObserverImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
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

    @Provides
    @Singleton
    fun provideSharedPrefs(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("Prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideInternetConnectivityObserverProd(
        @ApplicationContext context: Context
    ): InternetConnectivityObserver {
        return InternetConnectivityObserverImpl(context)
    }

}