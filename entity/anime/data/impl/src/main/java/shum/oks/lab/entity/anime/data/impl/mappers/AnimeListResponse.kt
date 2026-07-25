/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.mappers

import shum.oks.lab.entity.anime.data.api.entities.AnimeEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeGenreCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeGenreEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeLicensorCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeLicensorEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeProducerCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeProducerEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeRating
import shum.oks.lab.entity.anime.data.api.entities.AnimeStudioCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeStudioEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeThemeCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeThemeEntity
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

internal data class RelatedEntities(
    val producers: List<AnimeProducerEntity> = emptyList(),
    val producerCrossRefs: List<AnimeProducerCrossRef> = emptyList(),
    val licensors: List<AnimeLicensorEntity> = emptyList(),
    val licensorCrossRefs: List<AnimeLicensorCrossRef> = emptyList(),
    val studios: List<AnimeStudioEntity> = emptyList(),
    val studioCrossRefs: List<AnimeStudioCrossRef> = emptyList(),
    val genres: List<AnimeGenreEntity> = emptyList(),
    val genresCrossRefs: List<AnimeGenreCrossRef> = emptyList(),
    val themes: List<AnimeThemeEntity> = emptyList(),
    val themesCrossRefs: List<AnimeThemeCrossRef> = emptyList(),
)

internal fun AnimeListResponse.toRelatedEntities(): RelatedEntities =
    when (this) {
        is JikanAnimeSummaryListResponse -> {
            val producers = mutableMapOf<Int, AnimeProducerEntity>()
            val producerCrossRefs = mutableListOf<AnimeProducerCrossRef>()
            val licensors = mutableMapOf<Int, AnimeLicensorEntity>()
            val licensorCrossRefs = mutableListOf<AnimeLicensorCrossRef>()
            val studios = mutableMapOf<Int, AnimeStudioEntity>()
            val studioCrossRefs = mutableListOf<AnimeStudioCrossRef>()
            val genres = mutableMapOf<Int, AnimeGenreEntity>()
            val genresCrossRefs = mutableListOf<AnimeGenreCrossRef>()
            val themes = mutableMapOf<Int, AnimeThemeEntity>()
            val themesCrossRefs = mutableListOf<AnimeThemeCrossRef>()

            list.forEach { response ->
                response.producers?.forEach { item ->
                    producerCrossRefs.add(AnimeProducerCrossRef(response.id, item.id))
                    if (!producers.containsKey(item.id)) {
                        producers[item.id] = AnimeProducerEntity(item.id, item.type ?: "", item.name, item.url ?: "")
                    }
                }
                response.licensors?.forEach { item ->
                    licensorCrossRefs.add(AnimeLicensorCrossRef(response.id, item.id))
                    if (!licensors.containsKey(item.id)) {
                        licensors[item.id] = AnimeLicensorEntity(item.id, item.type ?: "", item.name, item.url ?: "")
                    }
                }
                response.studios?.forEach { item ->
                    studioCrossRefs.add(AnimeStudioCrossRef(response.id, item.id))
                    if (!studios.containsKey(item.id)) {
                        studios[item.id] = AnimeStudioEntity(item.id, item.type ?: "", item.name, item.url ?: "")
                    }
                }
                response.genres?.forEach { item ->
                    genresCrossRefs.add(AnimeGenreCrossRef(response.id, item.id))
                    if (!genres.containsKey(item.id)) {
                        genres[item.id] = AnimeGenreEntity(item.id, item.type ?: "", item.name, item.url ?: "")
                    }
                }
                response.themes?.forEach { item ->
                    themesCrossRefs.add(AnimeThemeCrossRef(response.id, item.id))
                    if (!themes.containsKey(item.id)) {
                        themes[item.id] = AnimeThemeEntity(item.id, item.type ?: "", item.name, item.url ?: "")
                    }
                }
            }
            RelatedEntities(
                producers = producers.values.toList(),
                producerCrossRefs = producerCrossRefs,
                licensors = licensors.values.toList(),
                licensorCrossRefs = licensorCrossRefs,
                studios = studios.values.toList(),
                studioCrossRefs = studioCrossRefs,
                genres = genres.values.toList(),
                genresCrossRefs = genresCrossRefs,
                themes = themes.values.toList(),
                themesCrossRefs = themesCrossRefs,
            )
        }
        is MyAnimeListAnimeListResponse -> RelatedEntities()
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
