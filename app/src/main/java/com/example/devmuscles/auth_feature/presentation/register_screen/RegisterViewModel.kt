package com.example.devmuscles.auth_feature.presentation.register_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.devmuscles.auth_feature.domain.AuthRepository
import com.example.devmuscles.auth_feature.domain.validation.ValidateEmail
import com.example.devmuscles.auth_feature.domain.validation.ValidateUsername
import com.example.devmuscles.core.util.InternetConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class RegisterViewModel(
    private val authRepository: AuthRepository,
    val validateEmail: ValidateEmail,
    val validateUsername: ValidateUsername,
    val connectivityObserver: InternetConnectivityObserver,
    private val savedStateHandle: SavedStateHandle

) : ViewModel() {
}