package com.example.devmuscles.core.appdesignsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.devmuscles.R


@Composable
private fun darkColorPalette(): ColorScheme {
    return darkColorScheme(
        primary = Color.Black,
        onPrimary = Color.White,
        onSecondary = DevMusclesLightGreen,
        background = Color.Black,
        surface = Color.Black,
        onSurface = Color.White,
        onBackground = Color.White,
        secondary = Color.White

    )
}



@Composable
private fun lightColorPalette(): ColorScheme {
    return lightColorScheme(
        primary = Color.Black,
        onPrimary = Color.White,
        primaryContainer = DevMusclesLightGreen,
        secondary = colorResource(id = R.color.dev_muscles_green),
        onSecondary = Color.White,
        background = Color.White,
        surface = Color.White,
        onSurface = DevMusclesLightBlack,
        onBackground = Color.Black,
    )
}


@Composable
fun DevMusclesTheme(darkTheme: Boolean = isSystemInDarkTheme(),content: @Composable () -> Unit)
{
    val colors = if(darkTheme){
        darkColorPalette()
    }else{
        lightColorPalette()
    }
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun textEntryFieldTextStyle() = Typography.bodyMedium.copy(
    fontFamily = fonts,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    color = MaterialTheme.colorScheme.primary,
)

