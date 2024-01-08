package com.example.devmuscles.auth_feature.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.devmuscles.R
import com.example.devmuscles.core.appdesignsystem.theme.DevMusclesTheme
import com.example.devmuscles.core.util.UiText

@Composable
fun EmailField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: String,
    label: String? = UiText.Res(R.string.emailField_label).get,
    labelComponent: @Composable (() -> Unit)? =
        { Text(text = label ?: UiText.Res(R.string.emailField_label).get) },
    placeHolder: String = UiText.Res(R.string.emailField_placeholder).get,
    isError: Boolean,
    onValueChange: (String) -> Unit,
    keyboardActions: KeyboardActions? = null,
    keyboardOptions: KeyboardOptions =
        KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
) {

    val focusManager = LocalFocusManager.current
    val keyboardActionsLocal: KeyboardActions = keyboardActions
        ?: KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )

    TextEntryField(
        value = value, isError = isError, onValueChange = onValueChange,
        label = label,
        placeHolder = placeHolder,
        keyboardActions = keyboardActionsLocal,
        keyboardOptions = keyboardOptions,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = UiText.Res(R.string.emailField_description_email).get
            )
        },
        validInputDescription = UiText.Res(R.string.emailField_description_isValid).get,
        invalidInputDescription = UiText.Res(R.string.textEntryField_description_isInvalid).get,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.DarkGray
        )
    )

}


@Preview(showBackground = true)
@Composable
fun EmailFieldPreview() {
    DevMusclesTheme {
        EmailField(
            value = "",
            label = null,
            isError = false,
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmailFieldPreviewError() {
    DevMusclesTheme {
        EmailField(
            value = "Bad.Email",
            isError = true,
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmailFieldPreviewValid() {
    DevMusclesTheme {
        EmailField(
            value = "chris@demo.com",
            isError = false,
            onValueChange = {}
        )
    }
}

