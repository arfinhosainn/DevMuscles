package com.example.devmuscles.auth_feature.presentation.register_screen

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devmuscles.R
import com.example.devmuscles.auth_feature.domain.AuthInfo
import com.example.devmuscles.auth_feature.domain.AuthRepository
import com.example.devmuscles.auth_feature.domain.validation.EmailPatternValidator
import com.example.devmuscles.auth_feature.domain.validation.UserDataValidator
import com.example.devmuscles.core.common.SavedStateConstants.SAVED_STATE_authInfo
import com.example.devmuscles.core.common.SavedStateConstants.SAVED_STATE_confirmPassword
import com.example.devmuscles.core.common.SavedStateConstants.SAVED_STATE_email
import com.example.devmuscles.core.common.SavedStateConstants.SAVED_STATE_errorMessage
import com.example.devmuscles.core.common.SavedStateConstants.SAVED_STATE_isInvalidConfirmPassword
import com.example.devmuscles.core.common.SavedStateConstants.SAVED_STATE_isInvalidConfirmPasswordMessageVisible
import com.example.devmuscles.core.common.SavedStateConstants.SAVED_STATE_isInvalidEmail
import com.example.devmuscles.core.common.SavedStateConstants.SAVED_STATE_isInvalidEmailMessageVisible
import com.example.devmuscles.core.common.SavedStateConstants.SAVED_STATE_isInvalidPassword
import com.example.devmuscles.core.common.SavedStateConstants.SAVED_STATE_isInvalidPasswordMessageVisible
import com.example.devmuscles.core.common.SavedStateConstants.SAVED_STATE_isPasswordsMatch
import com.example.devmuscles.core.common.SavedStateConstants.SAVED_STATE_password
import com.example.devmuscles.core.common.SavedStateConstants.SAVED_STATE_statusMessage
import com.example.devmuscles.core.common.SavedStateConstants.SAVED_STATE_username
import com.example.devmuscles.core.util.Exceptions
import com.example.devmuscles.core.util.InternetConnectivityObserver.InternetConnectivityObserver
import com.example.devmuscles.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userDataValidator: UserDataValidator,
    private val connectivityObserver: InternetConnectivityObserver,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Get params from savedStateHandle (from another screen or after process death)
    private val username: String =
        Uri.decode(savedStateHandle[SAVED_STATE_username]) ?: ""
    private val email: String =
        Uri.decode(savedStateHandle[SAVED_STATE_email]) ?: ""
    private val password: String =
        Uri.decode(savedStateHandle[SAVED_STATE_password]) ?: ""
    private val confirmPassword: String =
        Uri.decode(savedStateHandle[SAVED_STATE_confirmPassword]) ?: ""
    private val isInvalidEmail: Boolean =
        savedStateHandle[SAVED_STATE_isInvalidEmail] ?: false
    private val isInvalidEmailMessageVisible: Boolean =
        savedStateHandle[SAVED_STATE_isInvalidEmailMessageVisible] ?: false
    private val isInvalidPassword: Boolean =
        savedStateHandle[SAVED_STATE_isInvalidPassword] ?: false
    private val isInvalidPasswordMessageVisible: Boolean =
        savedStateHandle[SAVED_STATE_isInvalidPasswordMessageVisible] ?: false
    private val isInvalidConfirmPassword: Boolean =
        savedStateHandle[SAVED_STATE_isInvalidConfirmPassword] ?: false
    private val isInvalidConfirmPasswordMessageVisible: Boolean =
        savedStateHandle[SAVED_STATE_isInvalidConfirmPasswordMessageVisible] ?: false
    private val isPasswordsMatch: Boolean =
        savedStateHandle[SAVED_STATE_isPasswordsMatch] ?: true
    private val authInfo: AuthInfo? =
        savedStateHandle[SAVED_STATE_authInfo]
    private val statusMessage: UiText? =
        savedStateHandle[SAVED_STATE_statusMessage]
    private val errorMessage: UiText? =
        savedStateHandle[SAVED_STATE_errorMessage]


    @OptIn(ExperimentalCoroutinesApi::class)
    val onlineState = connectivityObserver.onlineStateFlow.mapLatest { it }

    private val _registerState = MutableStateFlow(
        RegisterState(
            username = username,
            email = email,
            password = password,
            confirmPassword = confirmPassword,
            isInvalidEmail = isInvalidEmail,
            isInvalidEmailMessageVisible = isInvalidEmailMessageVisible,
            isInvalidPassword = isInvalidPassword,
            isInvalidPasswordMessageVisible = isInvalidPasswordMessageVisible,
            isInvalidConfirmPassword = isInvalidConfirmPassword,
            isInvalidConfirmPasswordMessageVisible = isInvalidConfirmPasswordMessageVisible,
            isPasswordsMatch = isPasswordsMatch,
            authInfo = authInfo,
            statusMessage = statusMessage,
            errorMessage = errorMessage,
        )
    )
    val registerState = _registerState.onEach { state ->
        // save state for process death
        savedStateHandle[SAVED_STATE_username] =
            state.username
        savedStateHandle[SAVED_STATE_email] =
            state.email
        savedStateHandle[SAVED_STATE_password] =
            state.password
        savedStateHandle[SAVED_STATE_confirmPassword] =
            state.confirmPassword
        savedStateHandle[SAVED_STATE_isInvalidEmail] =
            state.isInvalidEmail
        savedStateHandle[SAVED_STATE_isInvalidEmailMessageVisible] =
            state.isInvalidEmailMessageVisible
        savedStateHandle[SAVED_STATE_isInvalidPassword] =
            state.isInvalidPassword
        savedStateHandle[SAVED_STATE_isInvalidPasswordMessageVisible] =
            state.isInvalidPasswordMessageVisible
        savedStateHandle[SAVED_STATE_isInvalidConfirmPassword] =
            state.isInvalidConfirmPassword
        savedStateHandle[SAVED_STATE_isInvalidConfirmPasswordMessageVisible] =
            state.isInvalidConfirmPasswordMessageVisible
        savedStateHandle[SAVED_STATE_isPasswordsMatch] =
            state.isPasswordsMatch
        savedStateHandle[SAVED_STATE_authInfo] =
            state.authInfo
        savedStateHandle[SAVED_STATE_statusMessage] =
            state.statusMessage
        savedStateHandle[SAVED_STATE_errorMessage] =
            state.errorMessage

        if (state.username.isNotBlank()) sendEvent(RegisterEvent.ValidateUsername)
        if (state.email.isNotBlank()) sendEvent(RegisterEvent.ValidateEmail)
        if (state.password.isNotBlank()) sendEvent(RegisterEvent.ValidatePassword)
        if (state.confirmPassword.isNotBlank()) sendEvent(RegisterEvent.ValidateConfirmPassword)
        sendEvent(RegisterEvent.ValidatePassword)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RegisterState())


    init {
        viewModelScope.launch {
            // restore state after process death (or coming from another screen)

            // Validate email & password when restored from process death or coming from another screen
            if (registerState.value.username.isNotBlank()) sendEvent(RegisterEvent.ValidateUsername)
            if (registerState.value.email.isNotBlank()) sendEvent(RegisterEvent.ValidateEmail)
            if (registerState.value.password.isNotBlank()) sendEvent(RegisterEvent.ValidatePassword)
            if (registerState.value.confirmPassword.isNotBlank()) sendEvent(RegisterEvent.ValidateConfirmPassword)
            sendEvent(RegisterEvent.ValidatePasswordsMatch)

            // Show status validation messages when restored from process death or coming from another screen
            if (registerState.value.isInvalidUsername) sendEvent(RegisterEvent.ShowInvalidUsernameMessage)
            if (registerState.value.isInvalidEmail) sendEvent(RegisterEvent.ShowInvalidEmailMessage)
            if (registerState.value.isInvalidPassword) sendEvent(RegisterEvent.ShowInvalidPasswordMessage)
            if (registerState.value.isInvalidConfirmPassword) sendEvent(RegisterEvent.ShowInvalidConfirmPasswordMessage)
        }
    }


    private suspend fun register(
        username: String,
        email: String,
        password: String
    ) {
        try {
            authRepository.signUp(
                username.trim().lowercase(), email = email, password = password
            )
            sendEvent(RegisterEvent.RegisterSuccess(UiText.Res(R.string.register_success)))
        } catch (e: Exceptions.EmailAlreadyExistsException) {
            sendEvent(RegisterEvent.EmailAlreadyExists)
        } catch (e: Exceptions.RegisterException) {
            sendEvent(
                RegisterEvent.RegisterError(
                    UiText.Res(
                        R.string.register_register_error,
                        e.message ?: ""
                    )
                )
            )
        } catch (e: Exceptions.NetworkException) {
            sendEvent(
                RegisterEvent.RegisterError(
                    UiText.Res(
                        R.string.register_network_error,
                        e.message ?: ""
                    )
                )
            )
        } catch (e: Exception) {
            sendEvent(
                RegisterEvent.UnknownError(
                    UiText.Res(
                        R.string.error_unknown,
                        e.message ?: ""
                    )
                )
            )
            e.printStackTrace()
        }
    }

    private fun validatePasswordMatch(): Boolean {
        if (registerState.value.password.isBlank() || registerState.value.confirmPassword.isBlank()) {
            return true
        }
        return (registerState.value.password == registerState.value.confirmPassword)
    }


    fun sendEvent(event: RegisterEvent) {
        viewModelScope.launch {
            onEvent(event)
            yield() // allow events to percolate
        }
    }

    private suspend fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.SetIsLoading -> {
                _registerState.update {
                    it.copy(isLoading = event.isLoading)
                }
            }

            is RegisterEvent.UpdateUsername -> {
                _registerState.update {
                    it.copy(
                        username = event.username,
                        isInvalidUsername = false,
                        isInvalidUsernameMessageVisible = false,
                        errorMessage = null,
                    )
                }
            }

            is RegisterEvent.UpdateEmail -> {
                _registerState.update {
                    it.copy(
                        email = event.email,
                        isInvalidEmail = false,
                        isInvalidEmailMessageVisible = false,
                        errorMessage = null,
                    )
                }
            }

            is RegisterEvent.UpdatePassword -> {
                _registerState.update {
                    it.copy(
                        password = event.password,
                        isInvalidPassword = false,
                        isInvalidPasswordMessageVisible = false,
                        isPasswordsMatch = false,
                        errorMessage = null,
                    )
                }
            }

            is RegisterEvent.UpdateConfirmPassword -> {
                _registerState.update {
                    it.copy(
                        confirmPassword = event.confirmPassword,
                        isInvalidConfirmPassword = false,
                        isInvalidConfirmPasswordMessageVisible = false,
                        isPasswordsMatch = false,
                        errorMessage = null,
                    )
                }
            }

            is RegisterEvent.SetIsPasswordVisible -> {
                _registerState.update {
                    it.copy(isPasswordVisible = event.isVisible)
                }
            }

            is RegisterEvent.ValidateUsername -> {
                val isValid =
                    userDataValidator.validateFullName(_registerState.value.username).isValid
                _registerState.update {
                    it.copy(isInvalidUsername = !isValid)
                }
            }

            is RegisterEvent.ValidateEmail -> {
                val isValid = userDataValidator.validateEmail(_registerState.value.email).isValid
                _registerState.update {
                    it.copy(
                        isInvalidEmail = !isValid
                    )
                }
            }

            is RegisterEvent.ValidatePassword -> {
                val isValid =
                    userDataValidator.validatePassword(_registerState.value.password).isValid
                _registerState.update {
                    it.copy(
                        isInvalidPassword = !isValid
                    )
                }
            }

            is RegisterEvent.ValidateConfirmPassword -> {
                val isValid =
                    userDataValidator.validatePassword(_registerState.value.confirmPassword).isValid
                _registerState.update {
                    it.copy(
                        isInvalidConfirmPassword = !isValid
                    )
                }
            }

            is RegisterEvent.ValidatePasswordsMatch -> {
                _registerState.update {
                    it.copy(
                        isPasswordsMatch = validatePasswordMatch()
                    )
                }
            }

            is RegisterEvent.ShowInvalidUsernameMessage -> {
                _registerState.update {
                    it.copy(
                        isInvalidUsernameMessageVisible = true
                    )
                }
            }

            is RegisterEvent.ShowInvalidEmailMessage -> {
                _registerState.update {
                    it.copy(
                        isInvalidEmailMessageVisible = true
                    )
                }
            }

            is RegisterEvent.ShowInvalidPasswordMessage -> {
                _registerState.update {
                    it.copy(
                        isInvalidPasswordMessageVisible = true
                    )
                }
            }

            is RegisterEvent.ShowInvalidConfirmPasswordMessage -> {
                _registerState.update {
                    it.copy(
                        isInvalidConfirmPasswordMessageVisible = true
                    )
                }
            }

            is RegisterEvent.Register -> {
                sendEvent(RegisterEvent.ValidateUsername)
                sendEvent(RegisterEvent.ValidateEmail)
                sendEvent(RegisterEvent.ValidatePassword)
                sendEvent(RegisterEvent.ValidateConfirmPassword)
                sendEvent(RegisterEvent.ValidatePasswordsMatch)
                yield()

                // Only show `Invalid Username` message only when "login" is clicked and the username is invalid.
                if (_registerState.value.isInvalidUsername)
                    sendEvent(RegisterEvent.ShowInvalidUsernameMessage)

                // Only show `Invalid Email` message only when "login" is clicked and the email is invalid.
                if (_registerState.value.isInvalidEmail)
                    sendEvent(RegisterEvent.ShowInvalidEmailMessage)

                // Only show `Invalid Password` message only when "login" is clicked and the password is invalid.
                if (_registerState.value.isInvalidPassword)
                    sendEvent(RegisterEvent.ShowInvalidPasswordMessage)

                // Only show `Invalid Confirm Password` message only when "login" is clicked and the confirm password is invalid.
                if (_registerState.value.isInvalidConfirmPassword)
                    sendEvent(RegisterEvent.ShowInvalidConfirmPasswordMessage)

                if (_registerState.value.isInvalidUsername
                    || _registerState.value.isInvalidEmail
                    || _registerState.value.isInvalidPassword
                    || _registerState.value.isInvalidConfirmPassword
                    || !_registerState.value.isPasswordsMatch
                ) return

                sendEvent(RegisterEvent.SetIsLoading(true))
                register(event.username, event.email, event.password)
            }

            is RegisterEvent.EmailAlreadyExists -> {
                _registerState.update {
                    it.copy(
                        errorMessage = UiText.Res(R.string.register_error_email_exists),
                        statusMessage = null
                    )
                }
                sendEvent(RegisterEvent.SetIsLoading(false))
            }

            is RegisterEvent.RegisterSuccess -> {
                _registerState.update {
                    it.copy(
                        errorMessage = null,
                        statusMessage = UiText.Res(R.string.register_success),
                        isPasswordVisible = false
                    )
                }
                sendEvent(RegisterEvent.SetIsLoading(false))
            }

            is RegisterEvent.RegisterError -> {
                _registerState.update {
                    it.copy(
                        errorMessage = event.message,
                        statusMessage = null
                    )
                }
                sendEvent(RegisterEvent.SetIsLoading(false))
            }

            is RegisterEvent.UnknownError -> {
                _registerState.update {
                    it.copy(
                        errorMessage = if (event.message.isRes)
                            event.message
                        else
                            UiText.Res(R.string.error_unknown, "")
                    )
                }
                sendEvent(RegisterEvent.SetIsLoading(false))
            }

        }
    }
}