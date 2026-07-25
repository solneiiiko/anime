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
import shum.oks.lab.entity.anime.data.api.entities.AnimeProducerEntity
import shum.oks.lab.entity.anime.domain.api.models.AnimeSummary
import shum.oks.lab.entity.anime.domain.api.models.AnimeDetails
import shum.oks.lab.entity.anime.domain.api.models.AnimeProducer
import shum.oks.lab.entity.anime.domain.api.models.AnimeType
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
        rating = anime.rating?.name,
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
        producers = producers.toAnimeProducerList()
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

private fun List<AnimeProducerEntity>.toAnimeProducerList(): List<AnimeProducer> =
    map { producerEntity ->
        AnimeProducer(
            id = producerEntity.id,
            type = producerEntity.type,
            name = producerEntity.name,
            url = producerEntity.url,
        )
    }
