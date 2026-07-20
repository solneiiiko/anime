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
internal data class MyAnimeListAnimeListResponse(
    @SerialName("data") override val list: List<MyAnimeListNodeResponse>,
    @SerialName("paging") override val pagination: MyAnimeListAnimePaginationResponse,
) : AnimeListResponse
