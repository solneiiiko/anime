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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import shum.oks.lab.core.ui.preview.AnimeThemePreview
import shum.oks.lab.core.ui.preview.ThemePreviews
import shum.oks.lab.feature.details.anime.screens.models.AnimeDetailsUiPreviewData
import shum.oks.lab.feature.details.anime.screens.models.LabeledValuesInfoUi

@Composable
internal fun LabeledValuesCard(
    labeledValuesInfoUi: LabeledValuesInfoUi,
    modifier: Modifier = Modifier,
) {
    InfoCard(modifier = modifier) {
        Column {
            SectionTitle(text = labeledValuesInfoUi.title)
            labeledValuesInfoUi.rows.forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Text(
                        text = row.title.asString(),
                        modifier = Modifier.width(80.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = row.text.asString(),
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        }
    }
}

@ThemePreviews
@Composable
private fun LabeledValuesCardPreview() {
    AnimeThemePreview {
        LabeledValuesCard(
            labeledValuesInfoUi = AnimeDetailsUiPreviewData.statisticsInfo()
        )
    }
}
