package com.example.jfraustomusicapp.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Purple700 = Color(0xFF34185F)
val Purple500 = Color(0xFF6F44FF)
val Purple200 = Color(0xFFEDE7FF)

val lightColorScheme = lightColorScheme(
    primary = Purple500,
    secondary = Purple700,
    surface = Color.White,
    background = Color(0xFFF6F2FF),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onSurface = Color(0xFF101010),
    onBackground = Color(0xFF101010)
)

val darkColorScheme = darkColorScheme()
