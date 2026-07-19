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
    @SerialName("title") val title: String,
    @SerialName("images") val imagesResponse: ImagesResponse,
    @SerialName("score") val score: Double?,
    @SerialName("type") val type: AnimeTypeResponse?,
    @SerialName("episodes") val episodes: Int?,
    @SerialName("members") val members: Int?,
)

@Serializable
internal data class ImagesResponse(
    @SerialName("jpg") val jpg: JpgResponse,
)

@Serializable
internal data class JpgResponse(
    @SerialName("image_url") val smallImageUrl: String?,
)

@Serializable
internal enum class AnimeTypeResponse {
    @SerialName("TV")
    TV,
    @SerialName("OVA")
    OVA,
    @SerialName("Movie")
    MOVIE,
    @SerialName("Special")
    SPECIAL,
    @SerialName("ONA")
    ONA,
    @SerialName("Music")
    MUSIC,
    @SerialName("CM")
    CM,
    @SerialName("PV")
    PV,
    @SerialName("TV Special")
    TV_SPECIAL,
    ;
}
