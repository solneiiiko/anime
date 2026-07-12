/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens.mappers

import shum.oks.lab.feature.catalog.screens.models.Anime as AnimeUiModel
import shum.oks.lab.entity.anime.domain.api.models.Anime

internal fun Anime.toUiModel(): AnimeUiModel = AnimeUiModel(
    id = id,
    title = title,
)
