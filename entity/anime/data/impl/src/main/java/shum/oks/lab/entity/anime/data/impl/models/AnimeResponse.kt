/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class AnimeResponse(
    @SerialName("mal_id") val id: Int,
    @Deprecated("title is deprecated API")
    @SerialName("title") val title: String,
    @SerialName("episodes") val episodes: Int?,
    @SerialName("score") val score: Double?,
    @SerialName("scored_by") val scoredBy: Double?,
    @SerialName("synopsis") val synopsis: String?,
    @SerialName("background") val background: String?,
)
