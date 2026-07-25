/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens.models

import shum.oks.lab.entity.anime.domain.api.models.AnimeType

internal data class AnimeDetailsUi(
    val id: Int,
    val headerInfo: HeaderInfoUi,
    val trailerUrl: String?,
    val duration: String?,
    val rating: String?,
    val scoredBy: Int?,
    val rank: Int?,
    val popularity: Int?,
    val favorites: Int?,
    val synopsis: String?,
    val background: String?,
    val year: Int?,
    val type: AnimeType,
    val producers: List<ProducerUi>,
    val licensors: List<LicensorUi>,
    val studios: List<StudioUi>,
    val genres: List<GenreUi>,
    val themes: List<ThemeUi>,
)

internal data class HeaderInfoUi(
    val title: String,
    val imageUrl: String?,
    val type: AnimeType,
    val episodes: Int?,
    val members: Int?,
    val score: Double?,
)
