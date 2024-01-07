package com.example.devmuscles.core.appdesignsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp
import com.example.devmuscles.core.appdesignsystem.common.modifiers.DP

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp),
)

object DevMusclesShapes {
    val ScreenTopCorners =
        RoundedCornerShape(topStart = DP.medium, topEnd = DP.medium)
    val WideButtonRoundedCorners =
        RoundedCornerShape(DP.medium)
    val MediumButtonRoundedCorners =
        RoundedCornerShape(DP.small)
}