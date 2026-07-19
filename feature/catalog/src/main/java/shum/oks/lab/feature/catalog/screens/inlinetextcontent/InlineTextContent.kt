/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens.inlinetextcontent

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.unit.sp

internal val catalogInlineContent by lazy {
    CatalogInlineContentType.entries.associate { contentType ->
        contentType.id to InlineTextContent(
            Placeholder(
                width = 16.sp, // TODO https://github.com/solneiiiko/anime/issues/31
                height = 16.sp, // TODO https://github.com/solneiiiko/anime/issues/31
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) {
            Icon(
                imageVector = contentType.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}
