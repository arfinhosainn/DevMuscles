package com.example.devmuscles.auth_feature.presentation.register_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.devmuscles.auth_feature.domain.AuthRepository
import com.example.devmuscles.auth_feature.domain.validation.EmailPatternValidator
import com.example.devmuscles.auth_feature.domain.validation.UserDataValidator
import com.example.devmuscles.core.util.InternetConnectivityObserver.InternetConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    val validateEmail: EmailPatternValidator,
    val userDataValidator: UserDataValidator,
    val connectivityObserver: InternetConnectivityObserver,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


}