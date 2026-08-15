/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens.mappers

import shum.oks.lab.core.ui.models.UiText
import shum.oks.lab.feature.details.anime.R
import shum.oks.lab.feature.details.anime.screens.models.ExpandableTextInfoUi


internal fun String.toSynopsisExpandableInfoUi(): ExpandableTextInfoUi? =
    takeIf { it.isBlank().not() }?.let {
        ExpandableTextInfoUi(
            title = UiText.StringResource(R.string.anime_details_synopsis_title),
            text = this,
            collapsedMaxLines = SynopsisCollapsedMaxLine,
        )
    }

internal fun String.toBackgroundExpandableInfoUi(): ExpandableTextInfoUi? =
    takeIf { it.isBlank().not() }?.let {
        ExpandableTextInfoUi(
            title = UiText.StringResource(R.string.anime_details_background_title),
            text = this,
            collapsedMaxLines = BackgroundCollapsedMaxLine,
        )
    }

private const val SynopsisCollapsedMaxLine = 4
private const val BackgroundCollapsedMaxLine = 2
