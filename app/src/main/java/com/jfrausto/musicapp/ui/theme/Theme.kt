package com.jfrausto.musicapp.ui.theme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
private val Purple = Color(0xFF7C4DFF)
private val PurpleDark = Color(0xFF5E35B1)
private val Bg = Color(0xFFF4F1FF)
private val scheme = lightColorScheme(
    primary = Purple,
    secondary = PurpleDark,
    background = Bg,
    surface = Color.White,
    onPrimary = Color.White,
    onSurface = Color(0xFF101010)
)
@Composable fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = scheme, content = content)
}
