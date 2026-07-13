/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.datasources

import shum.oks.lab.core.network.ApiResult
import shum.oks.lab.core.network.BaseRemoteDataSource
import shum.oks.lab.entity.anime.data.impl.api.AnimeApi
import shum.oks.lab.entity.anime.data.impl.models.AnimeListResponse
import javax.inject.Inject

internal class AnimeRemoteDataSource @Inject constructor(
    private val api: AnimeApi,
) : BaseRemoteDataSource() {

    suspend fun getAnimeListResponse(
        page: Int,
        limit: Int,
    ): ApiResult<AnimeListResponse> = safeApiCall {
        api.getAnimeList(page = page, limit = limit)
    }
}
