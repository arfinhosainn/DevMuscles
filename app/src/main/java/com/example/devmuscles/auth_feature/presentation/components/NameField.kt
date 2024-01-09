package com.example.devmuscles.auth_feature.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
fun NameField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: String,
    label: String? = UiText.Res(R.string.nameField_label).get, // if this is null, label is not shown
    @Suppress("UNUSED_PARAMETER") // left for future use
    labelComponent: @Composable (() -> Unit)? =
        { Text(text = label ?: UiText.Res(R.string.nameField_label).get) },
    placeholder: String = UiText.Res(R.string.nameField_placeholder).get,
    isError: Boolean,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions =
        KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
    keyboardActions: KeyboardActions? = null,
) {

    val focusManager = LocalFocusManager.current
    val keyboardActionsLocal: KeyboardActions = keyboardActions
        ?: KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )

    TextEntryField(
        modifier = modifier,
        value = value,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.DarkGray
        ),
        isError = isError,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActionsLocal,
        validInputDescription = UiText.Res(R.string.nameField_description_isValid).get,
        invalidInputDescription = UiText.Res(R.string.nameField_description_isInvalid).get
    )


}

@Preview(showBackground = true)
@Composable
fun NameFieldPreview() {
    DevMusclesTheme {
        NameField(
            value = "Arfin Hosain",
            isError = false,
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NameFieldPreviewError() {
    DevMusclesTheme {
        NameField(
            value = "Arfin Hosian",
            isError = true,
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NameFieldPreviewNoLabel() {
    DevMusclesTheme {
        NameField(
            value = "",
            label = null,
            isError = false,
            onValueChange = {}
        )
    }
}
