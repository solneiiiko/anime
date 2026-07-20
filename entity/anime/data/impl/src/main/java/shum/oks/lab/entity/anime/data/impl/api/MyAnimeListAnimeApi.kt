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
import shum.oks.lab.entity.anime.data.impl.models.MyAnimeListAnimeListResponse

internal interface MyAnimeListAnimeApi {

    @GET("anime/ranking")
    suspend fun getAnimeRanking(
        @Query("ranking_type") rankingType: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("fields") fields: String = FIELDS,
    ): MyAnimeListAnimeListResponse

    companion object {
        private const val FIELDS = "id,title,main_picture,mean,num_list_users,media_type,num_episodes"
    }
}
