/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import kotlinx.collections.immutable.ImmutableList
import shum.oks.lab.core.ui.models.UiText
import shum.oks.lab.core.ui.models.joinToString
import shum.oks.lab.core.ui.preview.AnimeThemePreview
import shum.oks.lab.core.ui.preview.ThemePreviews
import shum.oks.lab.feature.details.anime.screens.models.AnimeDetailsUiPreviewData

@Composable
internal fun MetadataRow(
    metadata: ImmutableList<UiText>,
    modifier: Modifier = Modifier,
) {
    val metadataText = metadata.joinToString(separator = " • ")
    Text(
        text = metadataText,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center,
        modifier = modifier,
    )
}

@ThemePreviews
@Composable
private fun MetadataRowPreview() {
    AnimeThemePreview {
        MetadataRow(
            metadata = AnimeDetailsUiPreviewData.metadata()
        )
    }
}
