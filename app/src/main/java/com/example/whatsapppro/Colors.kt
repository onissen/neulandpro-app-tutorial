package com.example.whatsapppro

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val DarkBlue = Color (0xFF3771FF)
val LightBlue = Color (0xFF5CB5FF)
val DarkWhite = Color (0xFFEAEAEA)

val LightColors = lightColors(
    primary = DarkBlue,
    secondary = LightBlue,
    background = DarkWhite,
    surface = Color.White,
    onSurface = Color.Black,
    onSecondary = Color.Black,
    onPrimary = Color.White
    )

val DarkColors = darkColors(
    primary = DarkBlue,
    secondary = LightBlue,
    background = Color.Black,
    surface = Color.DarkGray,
    onSurface = Color.White,
    onSecondary = Color.Black,
    onPrimary = Color.White
)
