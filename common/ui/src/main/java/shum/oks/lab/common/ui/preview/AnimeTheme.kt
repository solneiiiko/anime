/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.common.ui.preview

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import shum.oks.lab.common.theme.AnimeTheme
import shum.oks.lab.entity.settings.theme.models.ThemeContrast
import shum.oks.lab.entity.settings.theme.models.ThemeMode

@Composable
fun AnimeThemePreview(
    darkTheme: Boolean = isSystemInDarkTheme(),
    themeContrast: ThemeContrast = ThemeContrast.STANDARD,
    content: @Composable () -> Unit,
) {
    AnimeTheme(
        themeMode = if (darkTheme) ThemeMode.DARK else ThemeMode.LIGHT,
        themeContrast = themeContrast,
        dynamicColor = false,
    ) {
        Surface {
            content()
        }
    }
}
