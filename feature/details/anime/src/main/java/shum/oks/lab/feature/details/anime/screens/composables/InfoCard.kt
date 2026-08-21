/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import shum.oks.lab.common.ui.preview.AnimeThemePreview
import shum.oks.lab.core.ui.preview.ThemePreviews

@Composable
internal fun InfoCard(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = DefaultContentPadding,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
    ) {
        Box(modifier = Modifier.padding(contentPadding)) {
            content()
        }
    }
}

private val DefaultContentPadding = PaddingValues(
    start = 16.dp,
    end = 16.dp,
    top = 8.dp,
    bottom = 16.dp,
)

@ThemePreviews
@Composable
private fun InfoCardOneRowPreview() {
    AnimeThemePreview {
        InfoCard(modifier = Modifier.padding(16.dp)) {
            Text(text = "Details Card Content")
        }
    }
}

@ThemePreviews
@Composable
private fun InfoCardLongContentPreview() {
    AnimeThemePreview {
        InfoCard(modifier = Modifier.padding(16.dp)) {
            Text(text = "Details Card Content".repeat(100))
        }
    }
}
