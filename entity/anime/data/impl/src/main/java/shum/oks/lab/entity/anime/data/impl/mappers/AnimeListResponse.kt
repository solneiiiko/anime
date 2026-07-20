/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.mappers

import shum.oks.lab.entity.anime.data.api.entities.AnimePaginationEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeSummaryEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeType
import shum.oks.lab.entity.anime.data.impl.models.AnimeListResponse
import shum.oks.lab.entity.anime.data.impl.models.AnimeTypeResponse
import shum.oks.lab.entity.anime.data.impl.models.JikanAnimeListResponse
import shum.oks.lab.entity.anime.data.impl.models.JikanAnimeResponse
import shum.oks.lab.entity.anime.data.impl.models.MyAnimeListAnimeListResponse
import shum.oks.lab.entity.anime.data.impl.models.MyAnimeListNodeResponse


internal fun AnimeListResponse.toAnimePaginationEntityList(
    prevPage: Int?,
    nextPage: Int?,
): List<AnimePaginationEntity> =
    when (this) {
        is JikanAnimeListResponse -> list.map {
            AnimePaginationEntity(
                id = it.id,
                prevPage = prevPage,
                nextPage = nextPage,
            )
        }
        is MyAnimeListAnimeListResponse -> list.map {
            AnimePaginationEntity(
                id = it.anime.id,
                prevPage = prevPage,
                nextPage = nextPage,
            )
        }
    }

internal fun AnimeListResponse.toEntityModelList(): List<AnimeSummaryEntity> =
    when (this) {
        is JikanAnimeListResponse -> list.map { it.toAnimeSummaryEntity() }
        is MyAnimeListAnimeListResponse -> list.map { it.toAnimeSummaryEntity() }
    }

private fun JikanAnimeResponse.toAnimeSummaryEntity(): AnimeSummaryEntity =
    AnimeSummaryEntity(
        id = id,
        title = title,
        imageUrl = jikanImagesResponse.jpg.smallImageUrl,
        score = score ?: SCORE_DEFAULT_VALUE,
        type = type?.toAnimeType(),
        episodes = episodes,
        members = members
    )

private fun MyAnimeListNodeResponse.toAnimeSummaryEntity(): AnimeSummaryEntity =
    AnimeSummaryEntity(
        id = anime.id,
        title = anime.title,
        imageUrl = anime.mainPicture?.medium,
        score = anime.score ?: SCORE_DEFAULT_VALUE,
        type = anime.mediaType?.toAnimeType(),
        episodes = anime.episodes,
        members = anime.members
    )

private fun AnimeTypeResponse.toAnimeType(): AnimeType =
    when (this) {
        AnimeTypeResponse.TV -> AnimeType.TV
        AnimeTypeResponse.OVA -> AnimeType.OVA
        AnimeTypeResponse.MOVIE -> AnimeType.MOVIE
        AnimeTypeResponse.SPECIAL -> AnimeType.SPECIAL
        AnimeTypeResponse.ONA -> AnimeType.ONA
        AnimeTypeResponse.MUSIC -> AnimeType.MUSIC
        AnimeTypeResponse.CM -> AnimeType.CM
        AnimeTypeResponse.PV -> AnimeType.PV
        AnimeTypeResponse.TV_SPECIAL -> AnimeType.TV_SPECIAL
    }

private const val SCORE_DEFAULT_VALUE = 0.0
