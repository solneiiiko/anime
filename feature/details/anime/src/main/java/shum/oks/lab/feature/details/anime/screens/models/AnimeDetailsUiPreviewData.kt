/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens.models

import kotlinx.collections.immutable.toImmutableList
import shum.oks.lab.core.ui.models.UiText
import shum.oks.lab.feature.details.anime.R

internal object AnimeDetailsUiPreviewData {

    fun headerInfo() = HeaderInfoUi(
        title = "Full Anime Title",
        imageUrl = null,
        headerBlockUis = listOf(
            HeaderBlockUi(
                title = UiText.StringResource(R.string.anime_details_score_title),
                subtitle = UiText.Plain("8.50")
            ),
            HeaderBlockUi(
                title = UiText.StringResource(R.string.anime_details_rank_title),
                subtitle = UiText.Plain("#1")
            ),
            HeaderBlockUi(
                title = UiText.StringResource(R.string.anime_details_members_title),
                subtitle = UiText.Plain("1.2M")
            ),
            HeaderBlockUi(
                title = UiText.StringResource(R.string.anime_details_favorites_title),
                subtitle = UiText.Plain("50K")
            ),
        ).toImmutableList()
    )

    fun metadata() = listOf(
        UiText.StringResource(R.string.anime_details_type_special),
        UiText.Plain("2026"),
        UiText.Plain("12 episodes"),
        UiText.Plain("22 min"),
        UiText.StringResource(R.string.anime_details_rating_pg)
    ).toImmutableList()

    fun synopsisInfo() = ExpandableTextInfoUi(
        title = UiText.StringResource(R.string.anime_details_synopsis_title),
        text = "A very long synopsis text that should be expandable. ".repeat(10),
        collapsedMaxLines = 3
    )

    fun backgroundInfo() = ExpandableTextInfoUi(
        title = UiText.StringResource(R.string.anime_details_background_title),
        text = "Some background info about the anime.",
        collapsedMaxLines = 3
    )

    fun productionInfo() = LabeledValuesInfoUi(
        title = UiText.StringResource(R.string.anime_details_production_title),
        rows = listOf(
            LabeledRowUi(
                title = UiText.StringResource(R.string.anime_details_studios_title),
                text = UiText.Plain("Studio A, Studio B")
            ),
            LabeledRowUi(
                title = UiText.StringResource(R.string.anime_details_licensors_title),
                text = UiText.Plain("Licensor X")
            ),
        ).toImmutableList()
    )

    fun statisticsInfo() = LabeledValuesInfoUi(
        title = UiText.StringResource(R.string.anime_details_statistics_title),
        rows = listOf(
            LabeledRowUi(
                title = UiText.StringResource(R.string.anime_details_score_title),
                text = UiText.Plain("8.57")
            ),
            LabeledRowUi(
                title = UiText.StringResource(R.string.anime_details_rank_title),
                text = UiText.Plain("#1")
            ),
            LabeledRowUi(
                title = UiText.StringResource(R.string.anime_details_popularity_title),
                text = UiText.Plain("#100")
            ),
            LabeledRowUi(
                title = UiText.StringResource(R.string.anime_details_scored_by_title),
                text = UiText.Plain("912,456 users")
            ),
        ).toImmutableList()
    )

    fun producersInfo() = TagsInfoUi(
        title = UiText.StringResource(R.string.anime_details_producers_title),
        tags = listOf(
            TagInfoUi(label = "Producer 1"),
            TagInfoUi(label = "Producer 2"),
        ).toImmutableList()
    )

    fun genresInfo() = TagsInfoUi(
        title = UiText.StringResource(R.string.anime_details_genres_title),
        tags = listOf(
            TagInfoUi(label = "Action"),
            TagInfoUi(label = "Adventure"),
        ).toImmutableList()
    )

    fun themesInfo() = TagsInfoUi(
        title = UiText.StringResource(R.string.anime_details_themes_title),
        tags = listOf(
            TagInfoUi(label = "Supernatural"),
            TagInfoUi(label = "Military"),
        ).toImmutableList()
    )

    fun animeDetailsUi() = AnimeDetailsUi(
        id = 1,
        headerInfo = headerInfo(),
        metadata = metadata(),
        synopsisInfo = synopsisInfo(),
        backgroundInfo = backgroundInfo(),
        productionInfo = productionInfo(),
        statisticsInfo = statisticsInfo(),
        trailerUrl = null,
        producersInfo = producersInfo(),
        genresInfo = genresInfo(),
        themesInfo = themesInfo(),
    )
}
