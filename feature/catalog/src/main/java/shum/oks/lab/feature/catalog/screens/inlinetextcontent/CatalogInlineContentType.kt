/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens.inlinetextcontent

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Star
import androidx.compose.ui.graphics.vector.ImageVector

internal enum class CatalogInlineContentType(
    val id: String,
    val icon: ImageVector,
) {
    STAR_RATE(
        id = "star_rate",
        icon = Icons.Rounded.Star),
    MEMBERS(
        id = "members",
        icon = Icons.Rounded.Person
    ),
    ;
}
