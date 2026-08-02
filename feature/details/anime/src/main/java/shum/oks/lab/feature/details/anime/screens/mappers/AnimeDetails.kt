/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens.mappers

import kotlinx.collections.immutable.toImmutableList
import shum.oks.lab.core.ui.formatters.NumberFormatter
import shum.oks.lab.core.ui.models.UiText
import shum.oks.lab.entity.anime.domain.api.models.AnimeDetails
import shum.oks.lab.entity.anime.domain.api.models.AnimeGenre
import shum.oks.lab.entity.anime.domain.api.models.AnimeLicensor
import shum.oks.lab.entity.anime.domain.api.models.AnimeProducer
import shum.oks.lab.entity.anime.domain.api.models.AnimeStudio
import shum.oks.lab.entity.anime.domain.api.models.AnimeTheme
import shum.oks.lab.feature.details.anime.R
import shum.oks.lab.feature.details.anime.screens.models.AnimeDetailsUi
import shum.oks.lab.feature.details.anime.screens.models.GenreUi
import shum.oks.lab.feature.details.anime.screens.models.HeaderBlock
import shum.oks.lab.feature.details.anime.screens.models.HeaderInfoUi
import shum.oks.lab.feature.details.anime.screens.models.LicensorUi
import shum.oks.lab.feature.details.anime.screens.models.ProducerUi
import shum.oks.lab.feature.details.anime.screens.models.StudioUi
import shum.oks.lab.feature.details.anime.screens.models.ThemeUi

internal fun AnimeDetails.toUiModel(
    numberFormatter: NumberFormatter
) =
    AnimeDetailsUi(
        id = id,
        headerInfo = toHeaderInfoUi(numberFormatter),
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

private fun AnimeDetails.toHeaderInfoUi(
    numberFormatter: NumberFormatter
) =
    HeaderInfoUi(
        title = title,
        imageUrl = imageUrl,
        headerBlocks = listOf(
            HeaderBlock(
                title = UiText.StringResource(R.string.anime_details_score_label),
                subtitle = score.toSubtitle {
                    UiText.Plain(numberFormatter.formatScore(it))
                },
            ),
            HeaderBlock(
                title = UiText.StringResource(R.string.anime_details_rank_label),
                subtitle = rank.toSubtitle {
                    UiText.StringResource(
                        R.string.anime_details_rank_value_format,
                        numberFormatter.formatCommon(it)
                    )
                },
            ),
            HeaderBlock(
                title = UiText.StringResource(R.string.anime_details_members_label),
                subtitle = members.toSubtitle {
                    UiText.Plain(numberFormatter.formatCommon(it))
                },
            ),
            HeaderBlock(
                title = UiText.StringResource(R.string.anime_details_favorites_label),
                subtitle = favorites.toSubtitle {
                    UiText.Plain(numberFormatter.formatCommon(it))
                },
            ),
        ).toImmutableList(),
    )

private fun List<AnimeProducer>.toProducerUiList() =
    map { item ->
        ProducerUi(
            id = item.id,
            type = item.type,
            name = item.name,
        )
    }.toImmutableList()

private fun List<AnimeLicensor>.toLicensorUiList() =
    map { item ->
        LicensorUi(
            id = item.id,
            type = item.type,
            name = item.name,
        )
    }.toImmutableList()

private fun List<AnimeStudio>.toStudioUiList() =
    map { item ->
        StudioUi(
            id = item.id,
            type = item.type,
            name = item.name,
        )
    }.toImmutableList()

private fun List<AnimeGenre>.toGenreUiList() =
    map { item ->
        GenreUi(
            id = item.id,
            type = item.type,
            name = item.name,
        )
    }.toImmutableList()

private fun List<AnimeTheme>.toThemeUiList() =
    map { item ->
        ThemeUi(
            id = item.id,
            type = item.type,
            name = item.name,
        )
    }.toImmutableList()

private fun <T> T?.toSubtitle(
    format: (T) -> UiText
): UiText = this?.let { format(it) } ?: UiText.StringResource(R.string.anime_details_not_available)
