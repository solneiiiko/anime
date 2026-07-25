/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens.mappers

import shum.oks.lab.entity.anime.domain.api.models.AnimeDetails
import shum.oks.lab.entity.anime.domain.api.models.AnimeGenre
import shum.oks.lab.entity.anime.domain.api.models.AnimeLicensor
import shum.oks.lab.entity.anime.domain.api.models.AnimeProducer
import shum.oks.lab.entity.anime.domain.api.models.AnimeStudio
import shum.oks.lab.entity.anime.domain.api.models.AnimeTheme
import shum.oks.lab.feature.details.anime.screens.models.AnimeDetailsUi
import shum.oks.lab.feature.details.anime.screens.models.GenreUi
import shum.oks.lab.feature.details.anime.screens.models.HeaderInfoUi
import shum.oks.lab.feature.details.anime.screens.models.LicensorUi
import shum.oks.lab.feature.details.anime.screens.models.ProducerUi
import shum.oks.lab.feature.details.anime.screens.models.StudioUi
import shum.oks.lab.feature.details.anime.screens.models.ThemeUi

internal fun AnimeDetails.toUiModel() =
    AnimeDetailsUi(
        id = id,
        headerInfo = toHeaderInfoUi(),
        type = type,
        trailerUrl = trailerUrl,
        duration = duration,
        rating = rating,
        scoredBy = scoredBy,
        rank = rank,
        popularity = popularity,
        favorites = favorites,
        synopsis = synopsis,
        background = background,
        year = year,
        producers = producers.toProducerUiList(),
        licensors = licensors.toLicensorUiList(),
        studios = studios.toStudioUiList(),
        genres = genres.toGenreUiList(),
        themes = themes.toThemeUiList()
    )

private fun AnimeDetails.toHeaderInfoUi() =
    HeaderInfoUi(
        title = title,
        imageUrl = imageUrl,
        type = type,
        episodes = episodes,
        members = members,
        score = score
    )

private fun List<AnimeProducer>.toProducerUiList() =
    map { item ->
        ProducerUi(
            id = item.id,
            type = item.type,
            name = item.name,
        )
    }

private fun List<AnimeLicensor>.toLicensorUiList() =
    map { item ->
        LicensorUi(
            id = item.id,
            type = item.type,
            name = item.name,
        )
    }

private fun List<AnimeStudio>.toStudioUiList() =
    map { item ->
        StudioUi(
            id = item.id,
            type = item.type,
            name = item.name,
        )
    }

private fun List<AnimeGenre>.toGenreUiList() =
    map { item ->
        GenreUi(
            id = item.id,
            type = item.type,
            name = item.name,
        )
    }

private fun List<AnimeTheme>.toThemeUiList() =
    map { item ->
        ThemeUi(
            id = item.id,
            type = item.type,
            name = item.name,
        )
    }
