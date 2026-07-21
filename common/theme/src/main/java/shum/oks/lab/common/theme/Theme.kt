/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.common.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import shum.oks.lab.entity.settings.theme.models.ThemeContrast
import shum.oks.lab.entity.settings.theme.models.ThemeMode

@Composable
fun AnimeTheme(
    themeMode: ThemeMode,
    themeContrast: ThemeContrast,
    //TODO SettingsScreen https://github.com/solneiiiko/anime/issues/27
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val darkTheme: Boolean = when (themeMode) {
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
    }

    val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      darkTheme -> darkColorSchemeByContrast(themeContrast)
      else -> lightColorSchemeByContrast(themeContrast)
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}

private fun darkColorSchemeByContrast(themeContrast: ThemeContrast): ColorScheme = when (themeContrast) {
    ThemeContrast.STANDARD -> darkScheme
    ThemeContrast.MEDIUM -> mediumContrastDarkColorScheme
    ThemeContrast.HIGH -> highContrastDarkColorScheme
}

private fun lightColorSchemeByContrast(themeContrast: ThemeContrast): ColorScheme = when (themeContrast) {
    ThemeContrast.STANDARD -> lightScheme
    ThemeContrast.MEDIUM -> mediumContrastLightColorScheme
    ThemeContrast.HIGH -> highContrastLightColorScheme
}

private val lightScheme by lazy {
    lightColorScheme(
        primary = primaryLight,
        onPrimary = onPrimaryLight,
        primaryContainer = primaryContainerLight,
        onPrimaryContainer = onPrimaryContainerLight,
        secondary = secondaryLight,
        onSecondary = onSecondaryLight,
        secondaryContainer = secondaryContainerLight,
        onSecondaryContainer = onSecondaryContainerLight,
        tertiary = tertiaryLight,
        onTertiary = onTertiaryLight,
        tertiaryContainer = tertiaryContainerLight,
        onTertiaryContainer = onTertiaryContainerLight,
        error = errorLight,
        onError = onErrorLight,
        errorContainer = errorContainerLight,
        onErrorContainer = onErrorContainerLight,
        background = backgroundLight,
        onBackground = onBackgroundLight,
        surface = surfaceLight,
        onSurface = onSurfaceLight,
        surfaceVariant = surfaceVariantLight,
        onSurfaceVariant = onSurfaceVariantLight,
        outline = outlineLight,
        outlineVariant = outlineVariantLight,
        scrim = scrimLight,
        inverseSurface = inverseSurfaceLight,
        inverseOnSurface = inverseOnSurfaceLight,
        inversePrimary = inversePrimaryLight,
        surfaceDim = surfaceDimLight,
        surfaceBright = surfaceBrightLight,
        surfaceContainerLowest = surfaceContainerLowestLight,
        surfaceContainerLow = surfaceContainerLowLight,
        surfaceContainer = surfaceContainerLight,
        surfaceContainerHigh = surfaceContainerHighLight,
        surfaceContainerHighest = surfaceContainerHighestLight,
    )
}

private val darkScheme by lazy {
    darkColorScheme(
        primary = primaryDark,
        onPrimary = onPrimaryDark,
        primaryContainer = primaryContainerDark,
        onPrimaryContainer = onPrimaryContainerDark,
        secondary = secondaryDark,
        onSecondary = onSecondaryDark,
        secondaryContainer = secondaryContainerDark,
        onSecondaryContainer = onSecondaryContainerDark,
        tertiary = tertiaryDark,
        onTertiary = onTertiaryDark,
        tertiaryContainer = tertiaryContainerDark,
        onTertiaryContainer = onTertiaryContainerDark,
        error = errorDark,
        onError = onErrorDark,
        errorContainer = errorContainerDark,
        onErrorContainer = onErrorContainerDark,
        background = backgroundDark,
        onBackground = onBackgroundDark,
        surface = surfaceDark,
        onSurface = onSurfaceDark,
        surfaceVariant = surfaceVariantDark,
        onSurfaceVariant = onSurfaceVariantDark,
        outline = outlineDark,
        outlineVariant = outlineVariantDark,
        scrim = scrimDark,
        inverseSurface = inverseSurfaceDark,
        inverseOnSurface = inverseOnSurfaceDark,
        inversePrimary = inversePrimaryDark,
        surfaceDim = surfaceDimDark,
        surfaceBright = surfaceBrightDark,
        surfaceContainerLowest = surfaceContainerLowestDark,
        surfaceContainerLow = surfaceContainerLowDark,
        surfaceContainer = surfaceContainerDark,
        surfaceContainerHigh = surfaceContainerHighDark,
        surfaceContainerHighest = surfaceContainerHighestDark,
    )
}

private val mediumContrastLightColorScheme by lazy {
    lightColorScheme(
        primary = primaryLightMediumContrast,
        onPrimary = onPrimaryLightMediumContrast,
        primaryContainer = primaryContainerLightMediumContrast,
        onPrimaryContainer = onPrimaryContainerLightMediumContrast,
        secondary = secondaryLightMediumContrast,
        onSecondary = onSecondaryLightMediumContrast,
        secondaryContainer = secondaryContainerLightMediumContrast,
        onSecondaryContainer = onSecondaryContainerLightMediumContrast,
        tertiary = tertiaryLightMediumContrast,
        onTertiary = onTertiaryLightMediumContrast,
        tertiaryContainer = tertiaryContainerLightMediumContrast,
        onTertiaryContainer = onTertiaryContainerLightMediumContrast,
        error = errorLightMediumContrast,
        onError = onErrorLightMediumContrast,
        errorContainer = errorContainerLightMediumContrast,
        onErrorContainer = onErrorContainerLightMediumContrast,
        background = backgroundLightMediumContrast,
        onBackground = onBackgroundLightMediumContrast,
        surface = surfaceLightMediumContrast,
        onSurface = onSurfaceLightMediumContrast,
        surfaceVariant = surfaceVariantLightMediumContrast,
        onSurfaceVariant = onSurfaceVariantLightMediumContrast,
        outline = outlineLightMediumContrast,
        outlineVariant = outlineVariantLightMediumContrast,
        scrim = scrimLightMediumContrast,
        inverseSurface = inverseSurfaceLightMediumContrast,
        inverseOnSurface = inverseOnSurfaceLightMediumContrast,
        inversePrimary = inversePrimaryLightMediumContrast,
        surfaceDim = surfaceDimLightMediumContrast,
        surfaceBright = surfaceBrightLightMediumContrast,
        surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
        surfaceContainerLow = surfaceContainerLowLightMediumContrast,
        surfaceContainer = surfaceContainerLightMediumContrast,
        surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
        surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
    )
}

private val highContrastLightColorScheme by lazy {
    lightColorScheme(
        primary = primaryLightHighContrast,
        onPrimary = onPrimaryLightHighContrast,
        primaryContainer = primaryContainerLightHighContrast,
        onPrimaryContainer = onPrimaryContainerLightHighContrast,
        secondary = secondaryLightHighContrast,
        onSecondary = onSecondaryLightHighContrast,
        secondaryContainer = secondaryContainerLightHighContrast,
        onSecondaryContainer = onSecondaryContainerLightHighContrast,
        tertiary = tertiaryLightHighContrast,
        onTertiary = onTertiaryLightHighContrast,
        tertiaryContainer = tertiaryContainerLightHighContrast,
        onTertiaryContainer = onTertiaryContainerLightHighContrast,
        error = errorLightHighContrast,
        onError = onErrorLightHighContrast,
        errorContainer = errorContainerLightHighContrast,
        onErrorContainer = onErrorContainerLightHighContrast,
        background = backgroundLightHighContrast,
        onBackground = onBackgroundLightHighContrast,
        surface = surfaceLightHighContrast,
        onSurface = onSurfaceLightHighContrast,
        surfaceVariant = surfaceVariantLightHighContrast,
        onSurfaceVariant = onSurfaceVariantLightHighContrast,
        outline = outlineLightHighContrast,
        outlineVariant = outlineVariantLightHighContrast,
        scrim = scrimLightHighContrast,
        inverseSurface = inverseSurfaceLightHighContrast,
        inverseOnSurface = inverseOnSurfaceLightHighContrast,
        inversePrimary = inversePrimaryLightHighContrast,
        surfaceDim = surfaceDimLightHighContrast,
        surfaceBright = surfaceBrightLightHighContrast,
        surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
        surfaceContainerLow = surfaceContainerLowLightHighContrast,
        surfaceContainer = surfaceContainerLightHighContrast,
        surfaceContainerHigh = surfaceContainerHighLightHighContrast,
        surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
    )
}

private val mediumContrastDarkColorScheme by lazy {
    darkColorScheme(
        primary = primaryDarkMediumContrast,
        onPrimary = onPrimaryDarkMediumContrast,
        primaryContainer = primaryContainerDarkMediumContrast,
        onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
        secondary = secondaryDarkMediumContrast,
        onSecondary = onSecondaryDarkMediumContrast,
        secondaryContainer = secondaryContainerDarkMediumContrast,
        onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
        tertiary = tertiaryDarkMediumContrast,
        onTertiary = onTertiaryDarkMediumContrast,
        tertiaryContainer = tertiaryContainerDarkMediumContrast,
        onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
        error = errorDarkMediumContrast,
        onError = onErrorDarkMediumContrast,
        errorContainer = errorContainerDarkMediumContrast,
        onErrorContainer = onErrorContainerDarkMediumContrast,
        background = backgroundDarkMediumContrast,
        onBackground = onBackgroundDarkMediumContrast,
        surface = surfaceDarkMediumContrast,
        onSurface = onSurfaceDarkMediumContrast,
        surfaceVariant = surfaceVariantDarkMediumContrast,
        onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
        outline = outlineDarkMediumContrast,
        outlineVariant = outlineVariantDarkMediumContrast,
        scrim = scrimDarkMediumContrast,
        inverseSurface = inverseSurfaceDarkMediumContrast,
        inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
        inversePrimary = inversePrimaryDarkMediumContrast,
        surfaceDim = surfaceDimDarkMediumContrast,
        surfaceBright = surfaceBrightDarkMediumContrast,
        surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
        surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
        surfaceContainer = surfaceContainerDarkMediumContrast,
        surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
        surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
    )
}

private val highContrastDarkColorScheme by lazy {
    darkColorScheme(
        primary = primaryDarkHighContrast,
        onPrimary = onPrimaryDarkHighContrast,
        primaryContainer = primaryContainerDarkHighContrast,
        onPrimaryContainer = onPrimaryContainerDarkHighContrast,
        secondary = secondaryDarkHighContrast,
        onSecondary = onSecondaryDarkHighContrast,
        secondaryContainer = secondaryContainerDarkHighContrast,
        onSecondaryContainer = onSecondaryContainerDarkHighContrast,
        tertiary = tertiaryDarkHighContrast,
        onTertiary = onTertiaryDarkHighContrast,
        tertiaryContainer = tertiaryContainerDarkHighContrast,
        onTertiaryContainer = onTertiaryContainerDarkHighContrast,
        error = errorDarkHighContrast,
        onError = onErrorDarkHighContrast,
        errorContainer = errorContainerDarkHighContrast,
        onErrorContainer = onErrorContainerDarkHighContrast,
        background = backgroundDarkHighContrast,
        onBackground = onBackgroundDarkHighContrast,
        surface = surfaceDarkHighContrast,
        onSurface = onSurfaceDarkHighContrast,
        surfaceVariant = surfaceVariantDarkHighContrast,
        onSurfaceVariant = onSurfaceVariantDarkHighContrast,
        outline = outlineDarkHighContrast,
        outlineVariant = outlineVariantDarkHighContrast,
        scrim = scrimDarkHighContrast,
        inverseSurface = inverseSurfaceDarkHighContrast,
        inverseOnSurface = inverseOnSurfaceDarkHighContrast,
        inversePrimary = inversePrimaryDarkHighContrast,
        surfaceDim = surfaceDimDarkHighContrast,
        surfaceBright = surfaceBrightDarkHighContrast,
        surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
        surfaceContainerLow = surfaceContainerLowDarkHighContrast,
        surfaceContainer = surfaceContainerDarkHighContrast,
        surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
        surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
    )
}
