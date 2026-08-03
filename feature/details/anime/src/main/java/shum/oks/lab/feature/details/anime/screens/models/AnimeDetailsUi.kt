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
import shum.oks.lab.entity.anime.domain.api.models.AnimeType

internal data class AnimeDetailsUi(
    val id: Int,
    val headerInfo: HeaderInfoUi,
    val metadata: ImmutableList<UiText>,
    val synopsis: String?,
    val background: String?,
    val trailerUrl: String?,
    val scoredBy: Int?,
    val popularity: Int?,
    val type: AnimeType,
    val producers: ImmutableList<ProducerUi>,
    val licensors: ImmutableList<LicensorUi>,
    val studios: ImmutableList<StudioUi>,
    val genres: ImmutableList<GenreUi>,
    val themes: ImmutableList<ThemeUi>,
)

internal data class HeaderInfoUi(
    val title: String,
    val imageUrl: String?,
    val headerBlocks: ImmutableList<HeaderBlock>,
)

internal data class HeaderBlock(
    val title: UiText,
    val subtitle: UiText,
)
