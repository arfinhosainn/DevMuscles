package com.example.devmuscles.auth_feature.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.example.devmuscles.R
import com.example.devmuscles.core.appdesignsystem.theme.textEntryFieldTextStyle
import com.example.devmuscles.core.util.UiText

@Composable
fun TextEntryField(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = textEntryFieldTextStyle(),
    colors: TextFieldColors,
    label: String? = UiText.Res(R.string.emailField_label).get,
    labelComponent: @Composable (() -> Unit)? =
        { Text(text = label ?: UiText.Res(R.string.emailField_label).get) },
    placeHolder: String = UiText.Res(R.string.emailField_placeholder).get,
    isError: Boolean,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions =
        KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
    singleLine: Boolean = false,
    keyboardActions: KeyboardActions? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    validInputDescription: String =
        UiText.Res(R.string.textEntryField_description_isValid).get,
    invalidInputDescription: String = UiText.Res(R.string.textEntryField_description_isInvalid).get
) {
    val focusManager = LocalFocusManager.current
    val keyBoardActionsLocal: KeyboardActions = keyboardActions
        ?: KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )

    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        isError = isError,
        placeholder = {
            Text(text = placeHolder)
        },
        colors = colors,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyBoardActionsLocal,
        trailingIcon = {
            if (value.isNotBlank()) {
                val isNameValid = !isError
                val image =
                    if (isNameValid)
                        Icons.Filled.Check
                    else
                        Icons.Filled.Error
                val description =
                    if (isNameValid)
                        UiText.Res(R.string.textEntryField_description_isValid).get
                    else
                        UiText.Res(R.string.textEntryField_description_isInvalid).get

                Icon(imageVector = image, contentDescription = description)
            }
        }
    )
}