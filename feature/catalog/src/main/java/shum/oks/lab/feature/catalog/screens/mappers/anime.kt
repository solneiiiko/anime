/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens.mappers

import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import shum.oks.lab.core.ui.formatters.NumberFormatter
import shum.oks.lab.entity.anime.domain.api.models.AnimeSummary
import shum.oks.lab.entity.anime.domain.api.models.AnimeType
import shum.oks.lab.feature.catalog.navigation.CatalogItemKey
import shum.oks.lab.feature.catalog.screens.inlinetextcontent.CatalogInlineContentType
import shum.oks.lab.feature.catalog.screens.models.CatalogElement

internal fun AnimeSummary.toUiModel(
    numberFormatter: NumberFormatter,
) = CatalogElement(
    catalogItemKey = CatalogItemKey.AnimeKey(animeId = id),
    title = title,
    imageUrl = imageUrl,
    subtitle = getSubtitle(numberFormatter)
)

private fun AnimeSummary.getSubtitle(
    numberFormatter: NumberFormatter
): AnnotatedString =
    buildAnnotatedString {
        if (type == AnimeType.UNKNOWN) {
            episodes?.let { append("$it ") }
        } else {
            append("${type.title}(${episodes ?: UNKNOWN_EPISODES_COUNT}) ")
        }

        score?.let { score ->
            if (score >= 1) {
                appendInlineContent(CatalogInlineContentType.STAR_RATE.id)
                append(numberFormatter.formatScore(score))
            }
        }

        members?.let {
            appendInlineContent(CatalogInlineContentType.MEMBERS.id)
            append(numberFormatter.formatCommon(it))
        }
    }

private val AnimeType.title: String
    get() = when (this) {
        AnimeType.TV -> "TV"
        AnimeType.OVA -> "OVA"
        AnimeType.MOVIE -> "Movie"
        AnimeType.SPECIAL -> "Special"
        AnimeType.ONA -> "ONA"
        AnimeType.MUSIC -> "Music"
        AnimeType.CM -> "CM"
        AnimeType.PV -> "PV"
        AnimeType.TV_SPECIAL -> "TV Special"
        AnimeType.UNKNOWN -> "Unknown"
    }

private const val UNKNOWN_EPISODES_COUNT = "?"
