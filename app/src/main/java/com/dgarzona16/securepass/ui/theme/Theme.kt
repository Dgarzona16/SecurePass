package com.dgarzona16.securepass.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.dgarzona16.securepass.enums.ThemeSelection

val LocalThemeState = compositionLocalOf<ThemeState> { error("No theme state provided") }

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    tertiary = TertiaryColor,
    background = BackgroundColor,
    surface = SurfaceColor,
    error = ErrorColor,
    onPrimary = OnPrimaryColor,
    onSecondary = OnSecondaryColor,
    onBackground = OnBackgroundColor,
    onSurface = OnSurfaceColor,
    onError = OnErrorColor,
    primaryContainer = PrimaryVariantColor,
    secondaryContainer = SecondaryVariantColor,
)

private val LightColorScheme = lightColorScheme(
    primary = DarkPrimaryColor,
    secondary = DarkSecondaryColor,
    tertiary = DarkTertiaryColor,
    background = DarkBackgroundColor,
    surface = DarkSurfaceColor,
    error = DarkErrorColor,
    onPrimary = DarkOnPrimaryColor,
    onSecondary = DarkOnSecondaryColor,
    onBackground = DarkOnBackgroundColor,
    onSurface = DarkOnSurfaceColor,
    onError = DarkOnErrorColor,
    primaryContainer = DarkPrimaryVariantColor,
    secondaryContainer = DarkSecondaryVariantColor,
)

@Composable
fun SecurePassTheme(
    themeState: ThemeState,
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    themeState.setThemeSelection(ThemeSelection.SYSTEM) // Establecer el tema por defecto

    CompositionLocalProvider(LocalThemeState provides themeState) {
        val isDarkTheme = if (themeState.useSystemTheme.value) {
            isSystemInDarkTheme()
        } else {
            themeState.isDarkTheme.value
        }

        val colorScheme = when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (isDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }
            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }

        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                val window = (view.context as Activity).window
                window.statusBarColor = colorScheme.primary.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isDarkTheme
            }
        }

        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
