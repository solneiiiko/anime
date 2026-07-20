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
import shum.oks.lab.entity.anime.data.api.entities.AnimeCatalog
import shum.oks.lab.entity.anime.data.impl.api.JikanAnimeApi
import shum.oks.lab.entity.anime.data.impl.api.MyAnimeListAnimeApi
import shum.oks.lab.entity.anime.data.impl.models.AnimeListResponse
import shum.oks.lab.entity.anime.data.impl.qualifiers.JikanNetwork
import shum.oks.lab.entity.anime.data.impl.qualifiers.MyAnimeListNetwork
import javax.inject.Inject

internal class AnimeRemoteDataSource @Inject constructor(
    @JikanNetwork private val jikanAnimeApi: JikanAnimeApi,
    @MyAnimeListNetwork private val myAnimeListAnimeApi: MyAnimeListAnimeApi,
) : BaseRemoteDataSource() {

    suspend fun getAnimeListResponse(
        page: Int,
        limit: Int,
        catalog: AnimeCatalog,
    ): ApiResult<AnimeListResponse> = safeApiCall {
//  TODO https://github.com/solneiiiko/anime/issues/31    jikanAnimeApi.getAnimeList(page = page, limit = limit)
        myAnimeListAnimeApi.getAnimeRanking(
            rankingType = catalog.toRankingType(),
            limit = limit,
            offset = (page - 1) * limit,
        )
    }
}

private fun AnimeCatalog.toRankingType(): String = when (this) {
    AnimeCatalog.ALL -> "all"
}
