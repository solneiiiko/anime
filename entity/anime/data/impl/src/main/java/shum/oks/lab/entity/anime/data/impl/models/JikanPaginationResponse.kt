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
internal data class JikanPaginationResponse(
    @SerialName("last_visible_page") val lastVisiblePage: Int,
    @SerialName("has_next_page") override val hasNextPage: Boolean,
    @SerialName("current_page") val currentPage: Int,
    @SerialName("items") val items: JikanPaginationItemsResponse,
) : AnimePaginationResponse

@Serializable
internal data class JikanPaginationItemsResponse(
    @SerialName("count") val count: Int,
    @SerialName("total") val total: Int,
    @SerialName("per_page") val perPage: Int,
)
