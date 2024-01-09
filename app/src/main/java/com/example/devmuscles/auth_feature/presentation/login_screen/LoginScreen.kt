package com.example.devmuscles.auth_feature.presentation.login_screen

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Deblur
import androidx.compose.material.icons.filled.DeveloperBoard
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LogoDev
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.devmuscles.R
import com.example.devmuscles.auth_feature.presentation.components.EmailField
import com.example.devmuscles.auth_feature.presentation.components.PasswordField
import com.example.devmuscles.core.appdesignsystem.common.modifiers.DP
import com.example.devmuscles.core.appdesignsystem.common.modifiers.devMusclesMediumButton
import com.example.devmuscles.core.appdesignsystem.common.modifiers.devMusclesSmallButton
import com.example.devmuscles.core.appdesignsystem.common.modifiers.devMusclesWideButton
import com.example.devmuscles.core.appdesignsystem.common.modifiers.extraLargeHeight
import com.example.devmuscles.core.appdesignsystem.common.modifiers.largeWidth
import com.example.devmuscles.core.appdesignsystem.common.modifiers.mediumHeight
import com.example.devmuscles.core.appdesignsystem.common.modifiers.mediumWidth
import com.example.devmuscles.core.appdesignsystem.theme.fonts

@Composable
fun LoginScreenContent(
    email: String? = null,
    confirmPassword: String? = null,
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
                .height(500.dp)

        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.login_background),
                contentDescription = "Login_background",
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
                    text = "Sign Up",
                    // Footnote/Medium
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
                text = "Welcome Back,",
                fontSize = 38.sp,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.surface,
                fontFamily = fonts
            )
            Text(
                modifier = Modifier
                    .padding(bottom = 70.dp, start = 34.dp, end = 8.dp)
                    .align(Alignment.BottomStart),
                text = "Arfin,",
                fontSize = 38.sp,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
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
            Text(
                modifier = Modifier.align(Alignment.End),
                text = "Forgot Password",
                // Footnote/Medium
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Normal,
                color = Color(0xFFD0FD3E),
                fontFamily = fonts
            )

            Spacer(modifier = modifier.extraLargeHeight())

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                //Apple Button
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

                //Google Button
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

                // Third button
                Button(
                    modifier = modifier.devMusclesWideButton(),
                    onClick = { /* Handle third button click */ },
                    contentPadding = PaddingValues(0.dp),
                ) {
                    Text(text = "Third Button")
                }
            }


        }

    }

}


@Preview(showBackground = true)
@Composable
fun PreviewLoginContent() {
    LoginScreenContent()
}