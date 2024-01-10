package com.example.devmuscles.auth_feature.presentation.register_screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.devmuscles.R
import com.example.devmuscles.auth_feature.presentation.components.EmailField
import com.example.devmuscles.auth_feature.presentation.components.PasswordField
import com.example.devmuscles.core.appdesignsystem.common.modifiers.devMusclesSmallButton
import com.example.devmuscles.core.appdesignsystem.common.modifiers.devMusclesWideButton
import com.example.devmuscles.core.appdesignsystem.common.modifiers.extraLargeHeight
import com.example.devmuscles.core.appdesignsystem.common.modifiers.hugeHeight
import com.example.devmuscles.core.appdesignsystem.common.modifiers.largeWidth
import com.example.devmuscles.core.appdesignsystem.common.modifiers.mediumHeight
import com.example.devmuscles.core.appdesignsystem.common.modifiers.mediumWidth
import com.example.devmuscles.core.appdesignsystem.theme.fonts
import com.example.devmuscles.core.util.UiText

@Composable
fun RegisterScreenContent(
    modifier: Modifier = Modifier
) {
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
                value = "",
                isError = false,
                onValueChange = {})
            Spacer(modifier = Modifier.mediumHeight())

            // • PASSWORD
            PasswordField(
                value = "",
                isError = false,
                clickTogglePasswordVisibility = { /*TODO*/ },
                onValueChange = {},
                imeAction = ImeAction.Done
            )
            Spacer(modifier = Modifier.mediumHeight())
            // • CONFIRM PASSWORD
            PasswordField(
                label = UiText.Res(R.string.register_label_confirm_password).get,
                value = "",
                isError = false,
                onValueChange = {

                },
                isPasswordVisible = false,
                clickTogglePasswordVisibility = {
                },
                imeAction = ImeAction.Done,
                doneAction = {
                }
            )
            Spacer(modifier = Modifier.mediumHeight())

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
                    onClick = {},
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
                    onClick = { /* Handle third button click */ },
                    contentPadding = PaddingValues(0.dp),
                ) {
                    Text(text = UiText.Res(R.string.register_button).get)
                    Icon(imageVector = Icons.Filled.ArrowRight, contentDescription = "")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreenContent()

}