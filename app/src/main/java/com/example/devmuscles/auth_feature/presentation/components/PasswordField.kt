package com.example.devmuscles.auth_feature.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.devmuscles.R
import com.example.devmuscles.core.appdesignsystem.theme.DevMusclesTheme
import com.example.devmuscles.core.appdesignsystem.theme.textEntryFieldTextStyle
import com.example.devmuscles.core.util.UiText

@Composable
fun PasswordField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: String,
    textStyle: TextStyle = textEntryFieldTextStyle(),
    label: String? = UiText.Res(R.string.emailField_label).get,
    labelComponent: @Composable (() -> Unit)? =
        { Text(text = label ?: UiText.Res(R.string.emailField_label).get) },
    placeHolder: String = UiText.Res(R.string.passwordField_placeholder).get,
    isError: Boolean,
    isPasswordVisible: Boolean = false,
    clickTogglePasswordVisibility: () -> Unit,
    onValueChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Done,
    keyboardActions: KeyboardActions? = null,
    keyboardOptions: KeyboardOptions =
        KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
    doneAction: () -> Unit = {}
) {

    val focusManager = LocalFocusManager.current
    val keyboardActionsLocal: KeyboardActions = keyboardActions ?: KeyboardActions(
        onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        },
        onDone = {
            focusManager.clearFocus()
            doneAction()
        }
    )

    TextEntryField(
        value = value,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.DarkGray
        ),
        isError = isError,
        onValueChange = onValueChange,
        placeHolder = placeHolder,
        modifier = modifier,
        visualTransformation =
        if (isPasswordVisible)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActionsLocal,
        singleLine = true,
        trailingIcon = {
            val isPasswordValid = !isError

            val validImage = if (isPasswordValid)
                Icons.Filled.Check
            else
                Icons.Filled.Error

            val validDescription =
                if (isPasswordValid)
                    UiText.Res(R.string.emailField_description_isValid).get
                else
                    UiText.Res(R.string.emailField_description_isInvalid).get
            val passwordVisibleImage = if (isPasswordVisible)
                Icons.Default.Visibility
            else
                Icons.Default.VisibilityOff

            // localized description for accessibility services
            val passwordVisibleDescription = if (isPasswordVisible)
                UiText.Res(R.string.passwordField_description_hide).get
            else
                UiText.Res(R.string.passwordField_description_show).get

            Row {
                if (value.isNotBlank()) {
                    Icon(
                        imageVector = validImage,
                        contentDescription = validDescription,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }

                IconButton(onClick = clickTogglePasswordVisibility) {
                    Icon(imageVector = passwordVisibleImage, passwordVisibleDescription)
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PasswordFieldPreview() {

    val textState = remember { mutableStateOf("") }

    DevMusclesTheme {
        PasswordField(
            value = textState.value,
            label = null,
            isError = true,
            onValueChange = {
                textState.value = it
            },
            clickTogglePasswordVisibility = {

            },
            isPasswordVisible = true
        )
    }
}