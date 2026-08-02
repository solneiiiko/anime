/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.mvi.models

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import shum.oks.lab.core.ui.models.UiText

internal data class TopNavButtonUi(
    val navKey: NavKey,
    val label: UiText,
    val icon: ImageVector,
)
