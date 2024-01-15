package com.example.devmuscles.core.data.remote

import com.example.devmuscles.auth_feature.data.repository.remote.DTOs.auth.ApiCredentialsDTO
import com.example.devmuscles.auth_feature.data.repository.remote.DTOs.auth.AuthInfoDTO
import com.example.devmuscles.core.util.AuthToken
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface DevMusclesApi {

    companion object {
        const val BASE_URL = "http://localhost:8080/" // for local testing of the backend
        const val API_KEY = "01639098488"
    }

    @POST("login")
    suspend fun login(
        @Body credentials: ApiCredentialsDTO
    ): Response<AuthInfoDTO>

    @POST("register")
    suspend fun register(
        @Body credentials: ApiCredentialsDTO
    ): Response<Void>


}