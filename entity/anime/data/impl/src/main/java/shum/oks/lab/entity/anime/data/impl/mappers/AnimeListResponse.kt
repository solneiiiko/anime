/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.mappers

import shum.oks.lab.entity.anime.data.api.entities.AnimeSummaryEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeType
import shum.oks.lab.entity.anime.data.impl.models.AnimeResponse
import shum.oks.lab.entity.anime.data.impl.models.AnimeTypeResponse

internal fun List<AnimeResponse>.toEntityModelList(): List<AnimeSummaryEntity> =
    map {
        AnimeSummaryEntity(
            id = it.id,
            title = it.title,
            imageUrl = it.imagesResponse.jpg.smallImageUrl,
            score = it.score ?: 0.0,
            type = it.type?.toAnimeType(),
            episodes = it.episodes,
            members = it.members
        )
    }

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
