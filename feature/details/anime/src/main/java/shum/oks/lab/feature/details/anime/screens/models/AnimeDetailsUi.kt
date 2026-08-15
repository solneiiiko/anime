/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens.models

import kotlinx.collections.immutable.ImmutableList
import shum.oks.lab.core.ui.models.UiText

internal data class AnimeDetailsUi(
    val id: Int,
    val headerInfo: HeaderInfoUi,
    val metadata: ImmutableList<UiText>,
    val synopsisInfo: ExpandableTextInfoUi?,
    val backgroundInfo: ExpandableTextInfoUi?,
    val productionInfo: LabeledValuesInfoUi?,
    val statisticsInfo: LabeledValuesInfoUi?,
    val trailerUrl: String?,
    val producersInfo: TagsInfoUi?,
    val genresInfo: TagsInfoUi?,
    val themesInfo: TagsInfoUi?,
)

internal data class HeaderInfoUi(
    val title: String,
    val imageUrl: String?,
    val headerBlockUis: ImmutableList<HeaderBlockUi>,
)

internal data class HeaderBlockUi(
    val title: UiText,
    val subtitle: UiText,
)

internal data class ExpandableTextInfoUi(
    val title: UiText,
    val text: String,
    val collapsedMaxLines: Int,
)

internal data class LabeledValuesInfoUi(
    val title: UiText,
    val rows: ImmutableList<LabeledRowUi>,
)

internal data class LabeledRowUi(
    val title: UiText,
    val text: UiText,
)

internal data class TagsInfoUi(
    val title: UiText,
    val tags: ImmutableList<TagInfoUi>,
)

internal data class TagInfoUi(
    val label: String,
)
