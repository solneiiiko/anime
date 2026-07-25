/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.domain.api.models

data class AnimeSummary(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val score: Double?,
    val type: AnimeType,
    val episodes: Int?,
    val members: Int?,
)
