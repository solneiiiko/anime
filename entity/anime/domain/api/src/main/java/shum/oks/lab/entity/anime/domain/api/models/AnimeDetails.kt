/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.domain.api.models

data class AnimeDetails(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val trailerUrl: String?,
    val source: String?,
    val duration: String?,
    val rating: String?,
    val score: Double?,
    val scoredBy: Int?,
    val rank: Int?,
    val popularity: Int?,
    val favorites: Int?,
    val synopsis: String?,
    val background: String?,
    val year: Int?,
    val type: AnimeType,
    val episodes: Int?,
    val members: Int?,
    val producers: List<AnimeProducer>,
)
