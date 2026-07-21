/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.common.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

internal val AppTypography by lazy {
    Typography(
        displayLarge = baseline.displayLarge.copy(fontFamily = ebGaramondFontFamily),
        displayMedium = baseline.displayMedium.copy(fontFamily = ebGaramondFontFamily),
        displaySmall = baseline.displaySmall.copy(fontFamily = ebGaramondFontFamily),
        headlineLarge = baseline.headlineLarge.copy(fontFamily = ebGaramondFontFamily),
        headlineMedium = baseline.headlineMedium.copy(fontFamily = ebGaramondFontFamily),
        headlineSmall = baseline.headlineSmall.copy(fontFamily = ebGaramondFontFamily),
        titleLarge = baseline.titleLarge.copy(fontFamily = ebGaramondFontFamily),
        titleMedium = baseline.titleMedium.copy(fontFamily = ebGaramondFontFamily),
        titleSmall = baseline.titleSmall.copy(fontFamily = ebGaramondFontFamily),
        bodyLarge = baseline.bodyLarge.copy(fontFamily = ebGaramondFontFamily),
        bodyMedium = baseline.bodyMedium.copy(fontFamily = ebGaramondFontFamily),
        bodySmall = baseline.bodySmall.copy(fontFamily = ebGaramondFontFamily),
        labelLarge = baseline.labelLarge.copy(fontFamily = ebGaramondFontFamily),
        labelMedium = baseline.labelMedium.copy(fontFamily = ebGaramondFontFamily),
        labelSmall = baseline.labelSmall.copy(fontFamily = ebGaramondFontFamily),
    )
}

private val ebGaramondFontFamily by lazy {
    FontFamily(
        Font(R.font.eb_garamond_regular, weight = FontWeight.Normal, style = FontStyle.Normal),
        Font(R.font.eb_garamond_italic, weight = FontWeight.Normal, style = FontStyle.Italic),

        Font(R.font.eb_garamond_medium, weight = FontWeight.Medium, style = FontStyle.Normal),
        Font(R.font.eb_garamond_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),

        Font(R.font.eb_garamond_semi_bold, weight = FontWeight.SemiBold, style = FontStyle.Normal),
        Font(R.font.eb_garamond_semi_bold_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),

        Font(R.font.eb_garamond_bold, weight = FontWeight.Bold, style = FontStyle.Normal),
        Font(R.font.eb_garamond_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),

        Font(R.font.eb_garamond_extra_bold, weight = FontWeight.ExtraBold, style = FontStyle.Normal),
        Font(R.font.eb_garamond_extra_bold_italic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    )
}

private val baseline by lazy { Typography() }
