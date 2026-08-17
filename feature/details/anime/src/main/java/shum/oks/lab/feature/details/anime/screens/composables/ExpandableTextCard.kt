/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import shum.oks.lab.core.ui.composable.ExpandableText
import shum.oks.lab.core.ui.models.UiText
import shum.oks.lab.common.ui.preview.AnimeThemePreview
import shum.oks.lab.common.ui.preview.ThemePreviews
import shum.oks.lab.feature.details.anime.R
import shum.oks.lab.feature.details.anime.screens.models.ExpandableTextInfoUi

@Composable
internal fun ExpandableTextCard(
    expandableTextInfoUi: ExpandableTextInfoUi,
    modifier: Modifier = Modifier,
) {
    InfoCard(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        ExpandableContent(
            sectionTitle = expandableTextInfoUi.title,
            expandableText = expandableTextInfoUi.text,
            collapsedMaxLines = expandableTextInfoUi.collapsedMaxLines,
        )
    }
}

@Composable
private fun ExpandableContent(
    sectionTitle: UiText,
    expandableText: String,
    modifier: Modifier = Modifier,
    collapsedMaxLines: Int,
) {
    Column(modifier = modifier) {
        SectionTitle(text = sectionTitle)
        ExpandableText(
            text = expandableText,
            textStyle = MaterialTheme.typography.bodyLarge,
            collapsedMaxLines = collapsedMaxLines,
        )
    }
}

@ThemePreviews
@Composable
private fun ExpandableTextCardPreview() {
    AnimeThemePreview {
        ExpandableTextCard(
            expandableTextInfoUi = ExpandableTextInfoUi(
                title = UiText.StringResource(R.string.anime_details_synopsis_title),
                text = "This is a long synopsis text that should be expandable. " +
                        "It contains multiple sentences to demonstrate the collapsing behavior. ".repeat(5),
                collapsedMaxLines = 3
            )
        )
    }
}
