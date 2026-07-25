/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.mappers

import shum.oks.lab.entity.anime.data.api.entities.AnimeEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeProducerCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeProducerEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeRating
import shum.oks.lab.entity.anime.data.api.entities.AnimeType
import shum.oks.lab.entity.anime.data.impl.models.AnimeListResponse
import shum.oks.lab.entity.anime.data.impl.models.AnimeTypeResponse
import shum.oks.lab.entity.anime.data.impl.models.JikanAnimeSummaryListResponse
import shum.oks.lab.entity.anime.data.impl.models.JikanAnimeSummaryResponse
import shum.oks.lab.entity.anime.data.impl.models.MyAnimeListAnimeListResponse
import shum.oks.lab.entity.anime.data.impl.models.MyAnimeListNodeResponse


internal fun AnimeListResponse.toAnimeEntityList(): List<AnimeEntity> =
    when (this) {
        is JikanAnimeSummaryListResponse -> list.map { it.toAnimeEntity() }
        is MyAnimeListAnimeListResponse -> list.map { it.toAnimeEntity() }
    }

internal fun AnimeListResponse.toProducerEntityList(): Pair<List<AnimeProducerEntity>, List<AnimeProducerCrossRef>> =
    when (this) {
        is JikanAnimeSummaryListResponse -> {
            val producers = mutableMapOf<Int, AnimeProducerEntity>()
            val producerCrossRefs = mutableListOf<AnimeProducerCrossRef>()

            list.forEach { response ->
                response.producers?.forEach { producer ->
                    producerCrossRefs.add(
                        AnimeProducerCrossRef(
                            animeId = response.id,
                            producerId = producer.id
                        )
                    )
                    if (producers.contains(producer.id).not()) {
                        producers[producer.id] = AnimeProducerEntity(
                            id = producer.id,
                            type = producer.type ?: "",
                            name = producer.name,
                            url = producer.url ?: "",
                        )
                    }
                }
            }
            Pair(
                producers.values.toList(),
                producerCrossRefs
            )
        }
        is MyAnimeListAnimeListResponse -> Pair(emptyList(), emptyList())
    }

private fun JikanAnimeSummaryResponse.toAnimeEntity(): AnimeEntity =
    AnimeEntity(
        id = id,
        title = title,
        smallImageUrl = jikanImagesResponse.webp.smallImageUrl,
        largeImageUrl = jikanImagesResponse.webp.largeImageUrl,
        trailerEmbedUrl = jikanTrailerResponse?.embedUrl,
        source = source,
        duration = duration,
        rating = rating?.toAnimeRating(),
        score = score ?: SCORE_DEFAULT_VALUE,
        scoredBy = scoredBy,
        rank = rank,
        popularity = popularity,
        favorites = favorites,
        synopsis = synopsis,
        background = background,
        year = year,
        type = type?.toAnimeType(),
        episodes = episodes,
        members = members,
    )

private fun String.toAnimeRating(): AnimeRating =
    AnimeRating.G
    // TODO AnimeRating

private fun MyAnimeListNodeResponse.toAnimeEntity(): AnimeEntity =
    AnimeEntity(
        id = anime.id,
        title = anime.title,
        smallImageUrl = anime.mainPicture?.medium,
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
