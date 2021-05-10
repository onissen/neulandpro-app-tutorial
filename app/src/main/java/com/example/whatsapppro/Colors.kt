package com.example.whatsapppro

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val DarkBlue = Color (0xFF3771FF)
val LightBlue = Color (0xFF5CB5FF)

val LightColors = lightColors(
    primary = DarkBlue,
    secondary = LightBlue,
    surface = Color.White,
    onSurface = Color.Black,
    onSecondary = Color.Black,
    onPrimary = Color.White,
    background = Color.LightGray
)

val DarkColors = darkColors(
    primary = DarkBlue,
    secondary = LightBlue,
    surface = Color.DarkGray,
    onSurface = Color.White,
    onSecondary = Color.Black,
    onPrimary = Color.White,
    background = Color.Black
)
