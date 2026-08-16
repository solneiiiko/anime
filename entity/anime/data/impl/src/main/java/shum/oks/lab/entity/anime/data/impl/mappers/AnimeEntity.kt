/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.mappers

import shum.oks.lab.entity.anime.data.api.dbmodels.AnimeDetailsDbModel
import shum.oks.lab.entity.anime.data.api.dbmodels.AnimeSummaryDbModel
import shum.oks.lab.entity.anime.data.api.entities.AnimeGenreEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeLicensorEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeProducerEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeStudioEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeThemeEntity
import shum.oks.lab.entity.anime.domain.api.models.AnimeDetails
import shum.oks.lab.entity.anime.domain.api.models.AnimeGenre
import shum.oks.lab.entity.anime.domain.api.models.AnimeLicensor
import shum.oks.lab.entity.anime.domain.api.models.AnimeProducer
import shum.oks.lab.entity.anime.domain.api.models.AnimeRating
import shum.oks.lab.entity.anime.domain.api.models.AnimeStudio
import shum.oks.lab.entity.anime.domain.api.models.AnimeSummary
import shum.oks.lab.entity.anime.domain.api.models.AnimeTheme
import shum.oks.lab.entity.anime.domain.api.models.AnimeType
import shum.oks.lab.entity.anime.data.api.entities.AnimeRating as AnimeRatingEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeType as AnimeTypeEntity

internal fun AnimeSummaryDbModel.toAnimeModel() =
    AnimeSummary(
        id = id,
        title = title,
        imageUrl = smallImageUrl,
        score = score,
        type = type.toAnimeModel(),
        episodes = episodes,
        members = members,
    )

internal fun AnimeDetailsDbModel.toAnimeDetailsModel() =
    AnimeDetails(
        id = anime.id,
        title = anime.title,
        imageUrl = anime.smallImageUrl,
        trailerUrl = anime.trailerEmbedUrl,
        source = anime.source,
        duration = anime.duration,
        rating = anime.rating.toAnimeModel(),
        score = anime.score,
        scoredBy = anime.scoredBy,
        rank = anime.rank,
        popularity = anime.popularity,
        favorites = anime.favorites,
        synopsis = anime.synopsis,
        background = anime.background,
        year = anime.year,
        type = anime.type.toAnimeModel(),
        episodes = anime.episodes,
        members = anime.members,
        producers = producers.toAnimeProducerList(),
        licensors = licensors.toAnimeLicensorList(),
        studios = studios.toAnimeStudioList(),
        genres = genres.toAnimeGenreList(),
        themes = themes.toAnimeThemeList()
    )

private fun AnimeTypeEntity?.toAnimeModel(): AnimeType =
    when (this) {
        AnimeTypeEntity.TV -> AnimeType.TV
        AnimeTypeEntity.OVA -> AnimeType.OVA
        AnimeTypeEntity.MOVIE -> AnimeType.MOVIE
        AnimeTypeEntity.SPECIAL -> AnimeType.SPECIAL
        AnimeTypeEntity.ONA -> AnimeType.ONA
        AnimeTypeEntity.MUSIC -> AnimeType.MUSIC
        AnimeTypeEntity.CM -> AnimeType.CM
        AnimeTypeEntity.PV -> AnimeType.PV
        AnimeTypeEntity.TV_SPECIAL -> AnimeType.TV_SPECIAL
        null -> AnimeType.UNKNOWN
    }

private fun AnimeRatingEntity?.toAnimeModel(): AnimeRating? =
    when (this) {
        AnimeRatingEntity.G -> AnimeRating.G
        AnimeRatingEntity.PG -> AnimeRating.PG
        AnimeRatingEntity.PG_13 -> AnimeRating.PG_13
        AnimeRatingEntity.R -> AnimeRating.R
        AnimeRatingEntity.R_PLUS -> AnimeRating.R_PLUS
        AnimeRatingEntity.RX -> AnimeRating.RX
        null -> null
    }

private fun List<AnimeProducerEntity>.toAnimeProducerList(): List<AnimeProducer> =
    map { item ->
        AnimeProducer(
            id = item.id,
            type = item.type,
            name = item.name,
            url = item.url,
        )
    }

private fun List<AnimeLicensorEntity>.toAnimeLicensorList(): List<AnimeLicensor> =
    map { item ->
        AnimeLicensor(
            id = item.id,
            type = item.type,
            name = item.name,
            url = item.url,
        )
    }

private fun List<AnimeStudioEntity>.toAnimeStudioList(): List<AnimeStudio> =
    map { item ->
        AnimeStudio(
            id = item.id,
            type = item.type,
            name = item.name,
            url = item.url,
        )
    }

private fun List<AnimeGenreEntity>.toAnimeGenreList(): List<AnimeGenre> =
    map { item ->
        AnimeGenre(
            id = item.id,
            type = item.type,
            name = item.name,
            url = item.url,
        )
    }

private fun List<AnimeThemeEntity>.toAnimeThemeList(): List<AnimeTheme> =
    map { item ->
        AnimeTheme(
            id = item.id,
            type = item.type,
            name = item.name,
            url = item.url,
        )
    }
