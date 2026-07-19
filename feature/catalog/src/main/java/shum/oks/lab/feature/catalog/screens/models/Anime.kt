/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens.models

import androidx.compose.ui.text.AnnotatedString

internal data class CatalogElement(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val subtitle: AnnotatedString,
)
