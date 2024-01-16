package com.example.devmuscles.auth_feature.data.repository.remote

import com.example.devmuscles.auth_feature.data.repository.remote.DTOs.auth.LoginRequest
import com.example.devmuscles.auth_feature.data.repository.remote.DTOs.auth.LoginResponse
import com.example.devmuscles.auth_feature.data.repository.remote.DTOs.auth.RegistrationRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    companion object {
        const val BASE_URL = "http://localhost:8080/" // for local testing of the backend
        const val API_KEY = "01639098488"
    }

    @POST("login")
    suspend fun login(
        @Body credentials: LoginRequest
    ): Response<LoginResponse>

    @POST("register")
    suspend fun register(
        @Body credentials: RegistrationRequest
    )

    @GET("/authenticate")
    suspend fun authenticate()


}