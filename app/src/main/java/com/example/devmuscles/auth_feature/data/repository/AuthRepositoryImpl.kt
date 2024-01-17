package com.example.devmuscles.auth_feature.data.repository

import android.util.Log
import com.example.devmuscles.auth_feature.data.repository.remote.AuthApi
import com.example.devmuscles.auth_feature.data.repository.remote.DTOs.auth.LoginRequest
import com.example.devmuscles.auth_feature.data.repository.remote.DTOs.auth.RegistrationRequest
import com.example.devmuscles.auth_feature.data.util.AuthResult
import com.example.devmuscles.auth_feature.domain.AuthRepository
import com.example.devmuscles.auth_feature.domain.AuthenticatedUser
import com.example.devmuscles.auth_feature.domain.datastore.UserPreferences
import retrofit2.HttpException
import java.util.concurrent.CancellationException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val userPreferences: UserPreferences
) : AuthRepository {
    override suspend fun signUp(fullName: String, email: String, password: String): AuthResult {

        return try {
            api.register(
                RegistrationRequest(
                    fullName = fullName,
                    email = email,
                    password = password
                )
            )
            Log.d("signup", "signUp: $email")
            signIn(email, password)
        } catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized(e.message)
            } else {
                AuthResult.Error(e.message)
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            AuthResult.Error(e.message)
        }

    }

    override suspend fun signIn(email: String, password: String): AuthResult {
        return try {
            val response = api.login(LoginRequest(email, password))
            userPreferences.saveAuthenticatedUser(
                AuthenticatedUser(
                    fullName = response.body()?.fullName
                        ?: return AuthResult.Error("Error Singing In"),
                    email = email,
                    token = response.body()?.token ?: return AuthResult.Error("Error Signing In"),
                    userId = response.body()?.userId ?: return AuthResult.Error("Error Signing In")
                )
            )
            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized(e.message)
            } else {
                AuthResult.Error(e.message)
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            AuthResult.Error(e.message)
        }
    }

    override suspend fun authenticate(): AuthResult {
        return try {
            api.authenticate()
            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized(e.message)
            } else {
                AuthResult.Error(e.message)
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            AuthResult.Error(e.message)
        }
    }
}