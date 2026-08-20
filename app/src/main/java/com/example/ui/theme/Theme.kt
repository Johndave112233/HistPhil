package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = PhBlue,
    onPrimary = PureWhite,
    primaryContainer = PhBlueContainer,
    onPrimaryContainer = PhBlueDark,
    secondary = PhRed,
    onSecondary = PureWhite,
    secondaryContainer = PhRedContainer,
    onSecondaryContainer = PhRedDark,
    tertiary = PhGold,
    onTertiary = Slate900,
    tertiaryContainer = PhYellowContainer,
    onTertiaryContainer = Slate900,
    background = Slate50,
    onBackground = Slate900,
    surface = PureWhite,
    onSurface = Slate900,
    surfaceVariant = Slate100,
    onSurfaceVariant = Slate600,
    outline = Slate200,
    outlineVariant = Slate300
)

private val DarkColorScheme = darkColorScheme(
    primary = PhBlueLight,
    onPrimary = PureWhite,
    primaryContainer = PhBlueDark,
    onPrimaryContainer = PhBlueContainer,
    secondary = PhRed,
    onSecondary = PureWhite,
    secondaryContainer = PhRedDark,
    onSecondaryContainer = PhRedContainer,
    tertiary = PhYellow,
    onTertiary = Slate900,
    tertiaryContainer = PhGold,
    onTertiaryContainer = Slate900,
    background = Slate900,
    onBackground = Slate50,
    surface = Slate800,
    onSurface = Slate50,
    surfaceVariant = Color(0xFF1B2433),
    onSurfaceVariant = Slate400,
    outline = Color(0xFF334155),
    outlineVariant = Color(0xFF475569)
)

@Composable
fun KasaysayanTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
