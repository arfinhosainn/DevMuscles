package com.example.devmuscles.core.appdesignsystem.common.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.devmuscles.core.appdesignsystem.theme.DevMusclesShapes

fun Modifier.devMusclesWideButton(color: Color = Color.White): Modifier =
    fillMaxWidth()
        .extraLargeHeight()
        .clip(shape = DevMusclesShapes.WideButtonRoundedCorners)
        .background(color = color)

fun Modifier.devMusclesMediumButton(color: Color = Color.White): Modifier =
    extraLargeHeight()
        .extraLargeWidth()
        .clip(shape = DevMusclesShapes.MediumButtonRoundedCorners)
        .background(color = color)

fun Modifier.devMusclesSmallButton(): Modifier =
    XXLargeHeight()
        .XXLargeWidth()
        .clip(shape = DevMusclesShapes.SmallButtonRoundedCorners)


fun Modifier.devMusclesScreenTopCorners(color: Color = Color.White): Modifier =
    clip(shape = DevMusclesShapes.ScreenTopCorners)
        .background(color = color)


object DP {
    val nano = 1.dp
    val micro = 2.dp
    val tiny = 4.dp
    val xxSmall = 6.dp
    val extraSmall = 8.dp
    val small = 16.dp
    val medium = 24.dp
    val large = 32.dp
    val extraLarge = 50.dp
    val XXLarge = 52.dp
    val XXXLarge = 56.dp
    val huge = 64.dp
}

fun Modifier.tinyHeight(): Modifier = this.height(DP.tiny)
fun Modifier.xxSmallHeight(): Modifier = this.height(DP.xxSmall)
fun Modifier.extraSmallHeight(): Modifier = this.height(DP.extraSmall)
fun Modifier.smallHeight(): Modifier = this.height(DP.small)
fun Modifier.mediumHeight(): Modifier = this.height(DP.medium)
fun Modifier.largeHeight(): Modifier = this.height(DP.large)
fun Modifier.extraLargeHeight(): Modifier = this.height(DP.extraLarge)
fun Modifier.XXLargeHeight(): Modifier = this.height(DP.XXLarge)
fun Modifier.XXXLargeHeight(): Modifier = this.height(DP.XXXLarge)
fun Modifier.hugeHeight(): Modifier = this.height(DP.huge)

fun Modifier.tinyWidth(): Modifier = this.width(DP.tiny)
fun Modifier.xxSmallWidth(): Modifier = this.width(DP.xxSmall)
fun Modifier.extraSmallWidth(): Modifier = this.width(DP.extraSmall)
fun Modifier.smallWidth(): Modifier = this.width(DP.small)
fun Modifier.mediumWidth(): Modifier = this.width(DP.medium)
fun Modifier.largeWidth(): Modifier = this.width(DP.large)
fun Modifier.extraLargeWidth(): Modifier = this.width(DP.extraLarge)
fun Modifier.XXLargeWidth(): Modifier = this.width(DP.XXLarge)
fun Modifier.XXXLargeWidth(): Modifier = this.width(DP.XXXLarge)
fun Modifier.hugeWidth(): Modifier = this.width(DP.huge)