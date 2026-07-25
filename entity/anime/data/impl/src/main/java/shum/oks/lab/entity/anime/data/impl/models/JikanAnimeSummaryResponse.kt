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
internal data class JikanAnimeSummaryResponse(
    @SerialName("mal_id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("images") val jikanImagesResponse: JikanImagesResponse,
    @SerialName("trailer") val jikanTrailerResponse: JikanTrailerResponse?,
    @SerialName("type") val type: AnimeTypeResponse?,
    @SerialName("source") val source: String?,
    @SerialName("episodes") val episodes: Int?,
    @SerialName("duration") val duration: String?,
    @SerialName("rating") val rating: String?, //TODO AnimeRatingResponse?,
    @SerialName("score") val score: Double?,
    @SerialName("scored_by") val scoredBy: Int?,
    @SerialName("rank") val rank: Int?,
    @SerialName("popularity") val popularity: Int?,
    @SerialName("members") val members: Int?,
    @SerialName("favorites") val favorites: Int?,
    @SerialName("synopsis") val synopsis: String?,
    @SerialName("background") val background: String?,
    @SerialName("year") val year: Int?,
    @SerialName("producers") val producers: List<JikanProducerResponse>?,
    @SerialName("licensors") val licensors: List<JikanLicensorResponse>?,
    @SerialName("studios") val studios: List<JikanStudioResponse>?,
    @SerialName("genres") val genres: List<JikanGenreResponse>?,
    @SerialName("themes") val themes: List<JikanThemeResponse>?,
)

@Serializable
internal data class JikanImagesResponse(
    @SerialName("webp") val webp: JikanWebpResponse,
)

@Serializable
internal data class JikanWebpResponse(
    @SerialName("image_url") val smallImageUrl: String?,
    @SerialName("large_image_url") val largeImageUrl: String?,
)

@Serializable
internal data class JikanTrailerResponse(
    @SerialName("embed_url") val embedUrl: String?,
)

@Serializable
internal data class JikanProducerResponse(
    @SerialName("mal_id") val id: Int,
    @SerialName("type") val type: String?,
    @SerialName("name") val name: String,
    @SerialName("url") val url: String?,
)

@Serializable
internal data class JikanLicensorResponse(
    @SerialName("mal_id") val id: Int,
    @SerialName("type") val type: String?,
    @SerialName("name") val name: String,
    @SerialName("url") val url: String?,
)

@Serializable
internal data class JikanStudioResponse(
    @SerialName("mal_id") val id: Int,
    @SerialName("type") val type: String?,
    @SerialName("name") val name: String,
    @SerialName("url") val url: String?,
)

@Serializable
internal data class JikanGenreResponse(
    @SerialName("mal_id") val id: Int,
    @SerialName("type") val type: String?,
    @SerialName("name") val name: String,
    @SerialName("url") val url: String?,
)

@Serializable
internal data class JikanThemeResponse(
    @SerialName("mal_id") val id: Int,
    @SerialName("type") val type: String?,
    @SerialName("name") val name: String,
    @SerialName("url") val url: String?,
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
