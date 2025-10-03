package com.example.financialliteracyapp.presentation

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFF4CAF50),
    onPrimary = androidx.compose.ui.graphics.Color(0xFFFFFFFF),
    primaryContainer = androidx.compose.ui.graphics.Color(0xFF388E3C),
    onPrimaryContainer = androidx.compose.ui.graphics.Color(0xFFFFFFFF),
    secondary = androidx.compose.ui.graphics.Color(0xFFFFC107),
    onSecondary = androidx.compose.ui.graphics.Color(0xFF000000),
    secondaryContainer = androidx.compose.ui.graphics.Color(0xFFFF8F00),
    onSecondaryContainer = androidx.compose.ui.graphics.Color(0xFF000000),
    tertiary = androidx.compose.ui.graphics.Color(0xFF81C784),
    onTertiary = androidx.compose.ui.graphics.Color(0xFF000000),
    error = androidx.compose.ui.graphics.Color(0xFFF44336),
    onError = androidx.compose.ui.graphics.Color(0xFFFFFFFF),
    background = androidx.compose.ui.graphics.Color(0xFF121212),
    onBackground = androidx.compose.ui.graphics.Color(0xFFFFFFFF),
    surface = androidx.compose.ui.graphics.Color(0xFF1E1E1E),
    onSurface = androidx.compose.ui.graphics.Color(0xFFFFFFFF),
)

private val LightColorScheme = lightColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFF4CAF50),
    onPrimary = androidx.compose.ui.graphics.Color(0xFFFFFFFF),
    primaryContainer = androidx.compose.ui.graphics.Color(0xFF81C784),
    onPrimaryContainer = androidx.compose.ui.graphics.Color(0xFF000000),
    secondary = androidx.compose.ui.graphics.Color(0xFFFFC107),
    onSecondary = androidx.compose.ui.graphics.Color(0xFF000000),
    secondaryContainer = androidx.compose.ui.graphics.Color(0xFFFFECB3),
    onSecondaryContainer = androidx.compose.ui.graphics.Color(0xFF000000),
    tertiary = androidx.compose.ui.graphics.Color(0xFF388E3C),
    onTertiary = androidx.compose.ui.graphics.Color(0xFFFFFFFF),
    error = androidx.compose.ui.graphics.Color(0xFFF44336),
    onError = androidx.compose.ui.graphics.Color(0xFFFFFFFF),
    background = androidx.compose.ui.graphics.Color(0xFFFFFFFF),
    onBackground = androidx.compose.ui.graphics.Color(0xFF000000),
    surface = androidx.compose.ui.graphics.Color(0xFFF5F5F5),
    onSurface = androidx.compose.ui.graphics.Color(0xFF000000),
)

@Composable
fun FinancialLiteracyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = androidx.compose.material3.Typography(),
        content = content
    )
}