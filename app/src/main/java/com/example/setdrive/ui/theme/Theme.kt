package com.example.setdrive.ui.theme

import android.app.Activity
import android.os.Build
import android.view.Window
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Definindo as cores para o tema claro
private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    tertiary = TertiaryColor,
    background = BackgroundColor,
    surface = SurfaceColor,
    onPrimary = OnPrimaryColor,
    onSecondary = OnSecondaryColor,
    onTertiary = OnTertiaryColor,
    onBackground = OnBackgroundColorLight,
    onSurface = OnSurfaceColorLight
)

// Definindo as cores para o tema escuro
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColorDark,
    secondary = SecondaryColorDark,
    tertiary = TertiaryColorDark,
    background = BackgroundColorDark,
    surface = SurfaceColorDark,
    onPrimary = OnPrimaryColorDark,
    onSecondary = OnSecondaryColorDark,
    onTertiary = OnTertiaryColorDark,
    onBackground = OnBackgroundColorDark,
    onSurface = OnSurfaceColorDark
)

@Composable
fun SetDriveTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    // Obtendo a atividade atual para modificar a barra de status e navegação
    val context = LocalContext.current
    val view = LocalView.current

    if (!view.isInEditMode) {
        val activity = context as? Activity
        activity?.updateSystemBars(colorScheme)
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

// Função para atualizar as cores da barra de status e navegação
fun Activity.updateSystemBars(colorScheme: ColorScheme) {
    window.statusBarColor = colorScheme.background.toArgb()
    window.navigationBarColor = colorScheme.background.toArgb()

    WindowCompat.getInsetsController(window, window.decorView).apply {
        isAppearanceLightStatusBars = colorScheme.background.luminance() > 0.5
        isAppearanceLightNavigationBars = colorScheme.background.luminance() > 0.5
    }
}

