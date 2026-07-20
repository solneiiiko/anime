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
internal data class MyAnimeListAnimeResponse(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("main_picture") val mainPicture: MyAnimeListImageResponse?,
    @SerialName("mean") val score: Double?,
    @SerialName("num_list_users") val members: Int,
    @SerialName("media_type") val mediaType: AnimeTypeResponse?,
    @SerialName("num_episodes") val episodes: Int,
)

@Serializable
internal data class MyAnimeListImageResponse(
    @SerialName("medium") val medium: String?,
    @SerialName("large") val large: String?
)
