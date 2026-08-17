/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import shum.oks.lab.common.ui.preview.AnimeThemePreview
import shum.oks.lab.common.ui.preview.ThemePreviews
import shum.oks.lab.feature.details.anime.screens.models.AnimeDetailsUiPreviewData
import shum.oks.lab.feature.details.anime.screens.models.TagsInfoUi

@Composable
internal fun TagsCard(
    tagsInfoUi: TagsInfoUi,
    modifier: Modifier = Modifier,
) {
    InfoCard(modifier = modifier) {
        Column {
            SectionTitle(text = tagsInfoUi.title)
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 8.dp),
            ) {
                tagsInfoUi.tags.forEach { tag ->
                    Surface(
                        shape = MaterialTheme.shapes.extraLarge,
                        color = MaterialTheme.colorScheme.surfaceContainerHighest,
                    ) {
                        Text(
                            text = tag.label,
                            modifier = Modifier.padding(
                                horizontal = 12.dp,
                                vertical = 4.dp,
                            ),
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
}

@ThemePreviews
@Composable
private fun TagsCardPreview() {
    AnimeThemePreview {
        TagsCard(
            tagsInfoUi = AnimeDetailsUiPreviewData.genresInfo(),
        )
    }
}
