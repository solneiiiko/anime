/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.api

import retrofit2.http.GET
import retrofit2.http.Query
import shum.oks.lab.core.network.ApiResult
import shum.oks.lab.entity.anime.data.impl.models.JikanAnimeListResponse

internal interface JikanAnimeApi {

    @GET("anime")
    suspend fun getAnimeList(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): ApiResult<JikanAnimeListResponse>
}
