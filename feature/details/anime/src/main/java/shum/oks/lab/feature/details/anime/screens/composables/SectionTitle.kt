/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import shum.oks.lab.core.ui.models.UiText

@Composable
internal fun SectionTitle(
    text: UiText,
) {
    Text(
        text = text.asString(),
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.fillMaxWidth()
    )
}
