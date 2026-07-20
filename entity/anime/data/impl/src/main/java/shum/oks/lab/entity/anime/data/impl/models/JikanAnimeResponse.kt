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
internal data class JikanAnimeResponse(
    @SerialName("mal_id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("images") val jikanImagesResponse: JikanImagesResponse,
    @SerialName("score") val score: Double?,
    @SerialName("type") val type: AnimeTypeResponse?,
    @SerialName("episodes") val episodes: Int?,
    @SerialName("members") val members: Int?,
)

@Serializable
internal data class JikanImagesResponse(
    @SerialName("jpg") val jpg: JikanJpgResponse,
)

@Serializable
internal data class JikanJpgResponse(
    @SerialName("image_url") val smallImageUrl: String?,
)

@Serializable(with = AnimeTypeResponseSerializer::class)
internal enum class AnimeTypeResponse {
    TV,
    OVA,
    MOVIE,
    SPECIAL,
    ONA,
    MUSIC,
    CM,
    PV,
    TV_SPECIAL,

    ;
}
