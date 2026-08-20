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
import shum.oks.lab.entity.anime.domain.api.models.AnimeProducer
import shum.oks.lab.entity.anime.domain.api.models.AnimeRating
import shum.oks.lab.entity.anime.domain.api.models.AnimeTheme
import shum.oks.lab.entity.anime.domain.api.models.AnimeType
import shum.oks.lab.feature.details.anime.R
import shum.oks.lab.feature.details.anime.screens.models.AnimeDetailsUi
import shum.oks.lab.feature.details.anime.screens.models.HeaderBlockUi
import shum.oks.lab.feature.details.anime.screens.models.HeaderInfoUi
import shum.oks.lab.feature.details.anime.screens.models.LabeledRowUi
import shum.oks.lab.feature.details.anime.screens.models.LabeledValuesInfoUi
import shum.oks.lab.feature.details.anime.screens.models.TagInfoUi
import shum.oks.lab.feature.details.anime.screens.models.TagsInfoUi

internal fun AnimeDetails.toUiModel(
    numberFormatter: NumberFormatter
) =
    AnimeDetailsUi(
        id = id,
        headerInfo = toHeaderInfoUi(numberFormatter),
        metadata = toMetadataUi(numberFormatter),
        productionInfo = toProductionLabeledValuesInfoUi(),
        statisticsInfo = toStatisticsLabeledValuesInfoUi(numberFormatter),
        trailerUrl = trailerUrl,
        synopsisInfo = synopsis?.toSynopsisExpandableInfoUi(),
        backgroundInfo = background?.toBackgroundExpandableInfoUi(),
        producersInfo = producers.toProducersTagsInfoUi(),
        genresInfo = genres.toGenresTagsInfoUi(),
        themesInfo = themes.toThemesTagsInfoUi(),
    )

private fun AnimeDetails.toHeaderInfoUi(
    numberFormatter: NumberFormatter
) =
    HeaderInfoUi(
        title = title,
        imageUrl = imageUrl,
        headerBlockUis = listOf(
            HeaderBlockUi(
                title = UiText.StringResource(R.string.anime_details_score_title),
                subtitle = score.toSubtitle {
                    UiText.Plain(numberFormatter.formatScore(it))
                },
            ),
            HeaderBlockUi(
                title = UiText.StringResource(R.string.anime_details_rank_title),
                subtitle = rank.toSubtitle {
                    UiText.StringResource(
                        R.string.anime_details_rank_value_format,
                        numberFormatter.formatCommon(it)
                    )
                },
            ),
            HeaderBlockUi(
                title = UiText.StringResource(R.string.anime_details_members_title),
                subtitle = members.toSubtitle {
                    UiText.Plain(numberFormatter.formatCommon(it))
                },
            ),
            HeaderBlockUi(
                title = UiText.StringResource(R.string.anime_details_favorites_title),
                subtitle = favorites.toSubtitle {
                    UiText.Plain(numberFormatter.formatCommon(it))
                },
            ),
        ).toImmutableList(),
    )

private fun AnimeDetails.toMetadataUi(
    numberFormatter: NumberFormatter
) = listOfNotNull<UiText>(
    type.toUiString(),
    year?.let { UiText.Plain(it.toString()) },
    episodes?.let {
        UiText.PluralStringResource(
            resId = R.plurals.anime_details_episodes_value_format,
            quantity = it,
            numberFormatter.formatCommon(it)
        )
    },
    duration?.let { UiText.Plain(it) },
    rating?.toUiString(),
).toImmutableList()

private fun AnimeType.toUiString(): UiText? = when (this) {
    AnimeType.TV -> UiText.StringResource(R.string.anime_details_type_tv)
    AnimeType.OVA -> UiText.StringResource(R.string.anime_details_type_ova)
    AnimeType.MOVIE -> UiText.StringResource(R.string.anime_details_type_movie)
    AnimeType.SPECIAL -> UiText.StringResource(R.string.anime_details_type_special)
    AnimeType.ONA -> UiText.StringResource(R.string.anime_details_type_ona)
    AnimeType.MUSIC -> UiText.StringResource(R.string.anime_details_type_music)
    AnimeType.CM -> UiText.StringResource(R.string.anime_details_type_cm)
    AnimeType.PV -> UiText.StringResource(R.string.anime_details_type_pv)
    AnimeType.TV_SPECIAL -> UiText.StringResource(R.string.anime_details_type_tv_special)
    AnimeType.UNKNOWN -> null
}

private fun AnimeRating.toUiString(): UiText =
    UiText.StringResource(
        when (this) {
            AnimeRating.G -> R.string.anime_details_rating_g
            AnimeRating.PG -> R.string.anime_details_rating_pg
            AnimeRating.PG_13 -> R.string.anime_details_rating_pg_13
            AnimeRating.R -> R.string.anime_details_rating_r
            AnimeRating.R_PLUS -> R.string.anime_details_rating_r_plus
            AnimeRating.RX -> R.string.anime_details_rating_rx
        }
    )

private fun AnimeDetails.toProductionLabeledValuesInfoUi() = LabeledValuesInfoUi(
    title = UiText.StringResource(R.string.anime_details_production_title),
    rows = listOfNotNull(
        studios.takeIf { it.isNotEmpty() }?.let {
            LabeledRowUi(
                title = UiText.StringResource(R.string.anime_details_studios_title),
                text = UiText.Plain(it.joinToString(LABELED_VALUES_SEPARATOR) { studio -> studio.name }),
            )
        },
        licensors.takeIf { it.isNotEmpty() }?.let {
            LabeledRowUi(
                title = UiText.StringResource(R.string.anime_details_licensors_title),
                text = UiText.Plain(it.joinToString(LABELED_VALUES_SEPARATOR) { licensor -> licensor.name }),
            )
        },
    ).toImmutableList(),
)

private fun AnimeDetails.toStatisticsLabeledValuesInfoUi(
    numberFormatter: NumberFormatter
) = LabeledValuesInfoUi(
    title = UiText.StringResource(R.string.anime_details_statistics_title),
    rows = listOfNotNull(
        scoredBy?.let {
            LabeledRowUi(
                title = UiText.StringResource(R.string.anime_details_scored_by_title),
                text = UiText.PluralStringResource(
                    resId = R.plurals.anime_details_scored_by_value_format,
                    quantity = it,
                    numberFormatter.formatCommon(it)
                ),
            )
        },
        popularity?.let {
            LabeledRowUi(
                title = UiText.StringResource(R.string.anime_details_popularity_title),
                text = UiText.StringResource(
                    R.string.anime_details_popularity_value_format,
                    numberFormatter.formatCommon(it)
                ),
            )
        }
    ).toImmutableList()
)

private fun List<AnimeProducer>.toProducersTagsInfoUi() =
    takeIf { it.isNotEmpty() }?.let {
        TagsInfoUi(
            title = UiText.StringResource(R.string.anime_details_producers_title),
            tags = map { item ->
                TagInfoUi(
                    label = item.name,
                )
            }.toImmutableList(),
        )
    }

private fun List<AnimeGenre>.toGenresTagsInfoUi(): TagsInfoUi? =
    takeIf { it.isNotEmpty() }?.let {
        TagsInfoUi(
            title = UiText.StringResource(R.string.anime_details_genres_title),
            tags = map { item ->
                TagInfoUi(
                    label = item.name,
                )
            }.toImmutableList(),
        )
    }

private fun List<AnimeTheme>.toThemesTagsInfoUi(): TagsInfoUi? =
    takeIf { it.isNotEmpty() }?.let {
        TagsInfoUi(
            title = UiText.StringResource(R.string.anime_details_themes_title),
            tags = map { item ->
                TagInfoUi(
                    label = item.name,
                )
            }.toImmutableList(),
        )
    }

private fun <T> T?.toSubtitle(
    format: (T) -> UiText
): UiText = this?.let { format(it) } ?: UiText.StringResource(R.string.anime_details_not_available)

private const val LABELED_VALUES_SEPARATOR = ", "
