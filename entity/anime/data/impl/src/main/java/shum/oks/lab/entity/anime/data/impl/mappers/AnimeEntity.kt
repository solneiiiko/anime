/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.mappers

import shum.oks.lab.entity.anime.data.api.entities.AnimeSummaryEntity
import shum.oks.lab.entity.anime.domain.api.models.Anime
import shum.oks.lab.entity.anime.domain.api.models.AnimeType
import shum.oks.lab.entity.anime.data.api.entities.AnimeType as AnimeTypeEntity

internal fun AnimeSummaryEntity.toDomainModel() =
    Anime(
        id = id,
        title = title,
        imageUrl = imageUrl,
        score = score,
        type = type.toDomainModel(),
        episodes = episodes,
        members = members,
    )

private fun AnimeTypeEntity?.toDomainModel(): AnimeType =
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
