package com.example.devmuscles.auth_feature.presentation.register_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Deblur
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.devmuscles.R
import com.example.devmuscles.auth_feature.presentation.components.EmailField
import com.example.devmuscles.auth_feature.presentation.components.PasswordField
import com.example.devmuscles.core.appdesignsystem.common.modifiers.devMusclesSmallButton
import com.example.devmuscles.core.appdesignsystem.common.modifiers.devMusclesWideButton
import com.example.devmuscles.core.appdesignsystem.common.modifiers.extraSmallHeight
import com.example.devmuscles.core.appdesignsystem.common.modifiers.hugeHeight
import com.example.devmuscles.core.appdesignsystem.common.modifiers.largeWidth
import com.example.devmuscles.core.appdesignsystem.common.modifiers.mediumHeight
import com.example.devmuscles.core.appdesignsystem.common.modifiers.mediumWidth
import com.example.devmuscles.core.appdesignsystem.theme.fonts
import com.example.devmuscles.core.util.InternetConnectivityObserver.InternetAvailabilityIndicator
import com.example.devmuscles.core.util.InternetConnectivityObserver.InternetConnectivityObserver
import com.example.devmuscles.core.util.UiText
import com.example.devmuscles.core.util.keyboardVisibilityObserver


@Composable
fun RegistrationScreen(
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val registerState by viewModel.registerState.collectAsState()
    val connectivityState by viewModel.onlineState.collectAsState(
        initial =
        InternetConnectivityObserver.OnlineStatus.OFFLINE
    ) //must start as Offline

    RegisterScreenContent(
        state = registerState,
        onAction = viewModel::sendEvent
    )

    InternetAvailabilityIndicator(connectivityState = connectivityState)


}


@Composable
fun RegisterScreenContent(
    modifier: Modifier = Modifier,
    state: RegisterState,
    onAction: (RegisterEvent) -> Unit
) {

    val focusManager = LocalFocusManager.current
    val isKeyboardOpen by keyboardVisibilityObserver()

    fun performRegister() {
        onAction(
            RegisterEvent.Register(
                username = state.username,
                email = state.email,
                password = state.password,
                confirmPassword = state.confirmPassword
            )
        )
        focusManager.clearFocus()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onSurface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(384.dp)

        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.register_background),
                contentDescription = UiText.Res(R.string.register_background_image).get,
                contentScale = ContentScale.FillBounds
            )

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(28.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier,
                    text = UiText.Res(R.string.login_button).get,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    fontFamily = fonts
                )

            }

            Text(
                modifier = Modifier
                    .padding(bottom = 120.dp, start = 8.dp, end = 8.dp)
                    .align(Alignment.BottomCenter),
                text = UiText.Res(R.string.register_screen_headline).get,
                fontSize = 38.sp,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.surface,
                fontFamily = fonts
            )
            Text(
                modifier = Modifier
                    .padding(bottom = 65.dp, start = 34.dp, end = 8.dp)
                    .align(Alignment.BottomStart),
                text = UiText.Res(R.string.register_screen_description).get,
                fontSize = 10.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Normal,
                lineHeight = 16.sp,
                color = MaterialTheme.colorScheme.surface,
                fontFamily = fonts
            )

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {


            // • EMAIL
            EmailField(
                value = state.username,
                isError = false,
                onValueChange = {
                    onAction(RegisterEvent.UpdateUsername(it))
                })

            // • EMAIL
            EmailField(
                value = state.email,
                isError = false,
                onValueChange = {
                    onAction(RegisterEvent.UpdateEmail(it))
                })
            AnimatedVisibility(state.isInvalidEmail && state.isInvalidEmailMessageVisible) {
                Text(text = stringResource(R.string.error_invalid_username), color = Color.Red)
            }
            Spacer(modifier = Modifier.mediumHeight())

            // • PASSWORD
            PasswordField(
                value = state.password,
                isError = false,
                isPasswordVisible = state.isPasswordVisible,
                clickTogglePasswordVisibility = {
                    onAction(RegisterEvent.SetIsPasswordVisible(!state.isPasswordVisible))
                },
                onValueChange = {
                    onAction(RegisterEvent.UpdatePassword(it))
                },
                imeAction = ImeAction.Next
            )
            if (state.isInvalidPassword && state.isInvalidPasswordMessageVisible) {
                Text(text = stringResource(R.string.error_invalid_password), color = Color.Red)
            }
            Spacer(modifier = Modifier.mediumHeight())
            // • CONFIRM PASSWORD
            PasswordField(
                label = UiText.Res(R.string.register_label_confirm_password).get,
                value = state.confirmPassword,
                isError = state.isInvalidConfirmPassword,
                onValueChange = {
                    onAction(RegisterEvent.UpdateConfirmPassword(it))
                },
                isPasswordVisible = state.isPasswordVisible,
                clickTogglePasswordVisibility = {
                    onAction(RegisterEvent.SetIsPasswordVisible(!state.isPasswordVisible))
                },
                imeAction = ImeAction.Done,
                doneAction = {
                    performRegister()
                }
            )
            Spacer(modifier = Modifier.mediumHeight())
            AnimatedVisibility(state.isInvalidConfirmPassword && state.isInvalidConfirmPasswordMessageVisible) {
                Text(
                    text = stringResource(R.string.error_invalid_confirm_password),
                    color = Color.Red
                )
                Spacer(modifier = Modifier.extraSmallHeight())
            }
            // • SHOW IF MATCHING PASSWORDS
            AnimatedVisibility(!state.isPasswordsMatch) {
                Text(
                    text = stringResource(R.string.register_error_passwords_do_not_match),
                    color = Color.Red
                )
                Spacer(modifier = Modifier.extraSmallHeight())
            }
            // • SHOW PASSWORD REQUIREMENTS
            AnimatedVisibility(state.isInvalidPasswordMessageVisible || state.isInvalidConfirmPasswordMessageVisible) {
                Text(
                    text = stringResource(R.string.register_password_requirements),
                    color = Color.Red
                )
                Spacer(modifier = Modifier.extraSmallHeight())
            }

            Spacer(modifier = modifier.hugeHeight())

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                // • Apple Button
                Button(
                    onClick = {},
                    modifier = Modifier.devMusclesSmallButton(),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Icon(imageVector = Icons.Filled.Deblur, contentDescription = "")
                }
                Spacer(modifier = Modifier.mediumWidth())

                // • Google Button
                Button(
                    onClick = {

                    },
                    enabled = !state.isLoading,
                    modifier = Modifier.devMusclesSmallButton(),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Icon(imageVector = Icons.Filled.Deblur, contentDescription = "")
                }

                Spacer(modifier = Modifier.largeWidth())

                //• SignUp button
                Button(
                    modifier = modifier.devMusclesWideButton(),
                    onClick = {
                        performRegister()
                    },
                    contentPadding = PaddingValues(0.dp),
                ) {
                    Text(text = UiText.Res(R.string.register_button).get)
                    Icon(imageVector = Icons.Filled.ArrowRight, contentDescription = "")
                }
                // STATUS //////////////////////////////////////////

                AnimatedVisibility(state.errorMessage != null) {
                    state.errorMessage?.getOrNull?.let { errorMessage ->
                        Spacer(modifier = Modifier.extraSmallHeight())
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            modifier = Modifier
                                .animateContentSize()
                        )
                        Spacer(modifier = Modifier.extraSmallHeight())
                    }
                }
                AnimatedVisibility(state.statusMessage != null) {
                    state.statusMessage?.getOrNull?.let { message ->
                        Spacer(modifier = Modifier.extraSmallHeight())
                        Text(text = message)
                        Spacer(modifier = Modifier.extraSmallHeight())
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewRegisterScreen() {
//    RegisterScreenContent()
//
//}