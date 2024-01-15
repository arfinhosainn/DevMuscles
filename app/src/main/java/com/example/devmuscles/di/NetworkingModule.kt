package com.example.devmuscles.di

import com.example.devmuscles.auth_feature.data.repository.local.AuthDao
import com.example.devmuscles.auth_feature.data.repository.remote.AuthApi
import com.example.devmuscles.auth_feature.data.repository.remote.AuthApi.Companion.createBearerTokenString
import com.example.devmuscles.core.data.remote.DevMusclesApi
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
    fun provideOkHttpClient(
        @AuthDaoProdUsingBinds authDao: AuthDao
    ): OkHttpClient {
        val dispatcher = Dispatcher(Executors.newFixedThreadPool(20))
        dispatcher.maxRequests = 20
        dispatcher.maxRequestsPerHost = 20

        val addHeadersInterceptor = Interceptor { chain ->
            runBlocking(Dispatchers.IO) {
                val requestBuilder = chain.request().newBuilder()
                    .addHeader("x-api-key", DevMusclesApi.API_KEY)

                AuthApi.getAuthToken {
                    authDao.getAuthToken()
                }?.let { authToken ->
                    requestBuilder.addHeader("Authorization", createBearerTokenString(authToken))
                }
                val request = requestBuilder.build()

                chain.proceed(request)
            }
        }
        return OkHttpClient.Builder()
            .dispatcher(dispatcher)
            .addInterceptor(addHeadersInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .callTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideDevMusclesApi(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): DevMusclesApi {
        return Retrofit.Builder()
            .baseUrl(DevMusclesApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
            .create()
    }

}